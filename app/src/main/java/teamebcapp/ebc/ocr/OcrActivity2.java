package teamebcapp.ebc.ocr;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import teamebcapp.ebc.R;

public class OcrActivity2 extends AppCompatActivity {
    // true  : Camera On  : 카메라로 직접 찍어 문자 인식
    // false : Camera Off : 샘플이미지를 로드하여 문자 인식
    private boolean CameraOnOffFlag = true;

    private TessBaseAPI m_Tess; //Tess API reference
    private ProgressCircleDialog m_objProgressCircle = null; // 원형 프로그레스바
    private MessageHandler m_messageHandler;

    private String mDataPath = ""; //언어데이터가 있는 경로
    private String mCurrentPhotoPath; // 사진 경로
    private final String[] mLanguageList = {"eng","kor"}; // 언어
    // View
    private Context mContext;
    private Bitmap image; //사용되는 이미지

    private boolean ProgressFlag = false; // 프로그레스바 상태 플래그


    ArrayAdapter<String> arrayAdapter;
    ListView lvOcrResultList;
    ImageView m_orcResultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr2);
        mContext = this;

        m_orcResultImage=findViewById(R.id.iv_ocrResult);

        Button CopyCompleteButton =(Button) findViewById(R.id.CopyCompleteButton);

        CopyCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //액티비티 넘어오면 바로 사진찍기
        if (CameraOnOffFlag) {
            dispatchTakePictureIntent();
        } else {
            processImage();
        }

        lvOcrResultList =(ListView) findViewById(R.id.OcrResultList);

        m_objProgressCircle = new ProgressCircleDialog(this);
        m_messageHandler = new MessageHandler();

        if(CameraOnOffFlag)
        {
            PermissionCheck();
            Tesseract();
        }
        else
        {
            //이미지 디코딩을 위한 초기화
            image = BitmapFactory.decodeResource(getResources(), R.drawable.sampledata); //샘플이미지파일
            Test();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantDefine.PERMISSION_CODE:
                //ToastmakeText(this, "권한이 허용되었습니다.", //ToastLENGTH_SHORT).show();
                break;
            case ConstantDefine.ACT_TAKE_PIC:
                //카메라로 찍은 사진을 받는다.
                if ((resultCode == RESULT_OK) ) {

                    try {
                        File file = new File(mCurrentPhotoPath);
                        Bitmap rotatedBitmap = null;
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                                FileProvider.getUriForFile(OcrActivity2.this,
                                        getApplicationContext().getPackageName() + ".fileprovider", file));

                        bitmap=resizeBitmap(bitmap);
                        // 회전된 사진을 원래대로 돌려 표시한다.
                        if (bitmap != null) {
                            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_UNDEFINED);
                            switch (orientation) {

                                case ExifInterface.ORIENTATION_ROTATE_90:
                                    rotatedBitmap = rotateImage(bitmap, 90);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_180:
                                    rotatedBitmap = rotateImage(bitmap, 180);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_270:
                                    rotatedBitmap = rotateImage(bitmap, 270);
                                    break;

                                case ExifInterface.ORIENTATION_NORMAL:
                                default:
                                    rotatedBitmap = bitmap;
                            }
                            OCRThread ocrThread = new OCRThread(rotatedBitmap);
                            ocrThread.setDaemon(true);
                            ocrThread.start();
                            m_orcResultImage.setImageBitmap(rotatedBitmap);
                        }
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        if (requestCode == 0) {

        } else {

        }
    }

    // 이미지를 원본과 같게 회전시킨다.
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static Bitmap resizeBitmap(Bitmap bitmap){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        int width = 1280; // 축소시킬 너비
        int height = 720; // 축소시킬 높이
        float bmpWidth = bitmap.getWidth();
        float bmpHeight = bitmap.getHeight();

        if (bmpWidth > width) {
            // 원하는 너비보다 클 경우의 설정
            float mWidth = bmpWidth / 100;
            float scale = width/ mWidth;
            bmpWidth *= (scale / 100);
            bmpHeight *= (scale / 100);
        } else if (bmpHeight > height) {
            // 원하는 높이보다 클 경우의 설정
            float mHeight = bmpHeight / 100;
            float scale = height/ mHeight;
            bmpWidth *= (scale / 100);
            bmpHeight *= (scale / 100);
        }

        return Bitmap.createScaledBitmap(bitmap, (int) bmpWidth, (int) bmpHeight, true);
    }

    public void PermissionCheck() {
        /**
         * 6.0 마시멜로우 이상일 경우에는 권한 체크후 권한을 요청한다.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                // 권한 없음
                ActivityCompat.requestPermissions(OcrActivity2.this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        ConstantDefine.PERMISSION_CODE);
            } else {
                // 권한 있음
            }
        }
    }


    public void Tesseract() {
        //언어파일 경로
        mDataPath = getFilesDir() + "/tesseract/";

        //트레이닝데이터가 카피되어 있는지 체크
        String lang = "";
        for (String Language : mLanguageList) {
            checkFile(new File(mDataPath + "tessdata/"), Language);
            lang += Language + "+";
        }
        m_Tess = new TessBaseAPI();
        m_Tess.init(mDataPath, lang);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * 기본카메라앱을 실행 시킨다.
     */
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //카메라 어플 체크
        if (isIntentAvailable(getApplicationContext(), MediaStore.ACTION_IMAGE_CAPTURE)) {

            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // 사진파일을 생성한다.
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // 사진파일이 정상적으로 생성되었을때
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            this.getApplicationContext().getPackageName() + ".fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, ConstantDefine.ACT_TAKE_PIC);
                }
            }
        }
    }


    public static boolean isIntentAvailable( Context context, String action){
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent( action);
        List<ResolveInfo> list = packageManager.queryIntentActivities( intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;

    }

    //copy file to device
    private void copyFiles(String Language) {
        try {
            String filepath = mDataPath + "/tessdata/" + Language + ".traineddata";
            AssetManager assetManager = getAssets();
            InputStream instream = assetManager.open("tessdata/"+Language+".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //check file on the device
    private void checkFile(File dir, String Language) {
        //디렉토리가 없으면 디렉토리를 만들고 그후에 파일을 카피
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles(Language);
        }
        //디렉토리가 있지만 파일이 없으면 파일카피 진행
        if (dir.exists()) {
            String datafilepath = mDataPath + "tessdata/" + Language + ".traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles(Language);
            }
        }
    }

    //region Thread
    public class OCRThread extends Thread
    {
        private Bitmap rotatedImage;
        OCRThread(Bitmap rotatedImage)
        {
            this.rotatedImage = rotatedImage;
            if(!ProgressFlag)
                m_objProgressCircle = ProgressCircleDialog.show(mContext, "", "", true);
            ProgressFlag = true;
        }

        @Override
        public void run() {
            super.run();
            // 사진의 글자를 인식해서 옮긴다
            String OCRresult = null;
            m_Tess.setImage(rotatedImage);
            OCRresult = m_Tess.getUTF8Text();

            Message message = Message.obtain();
            message.what = ConstantDefine.RESULT_OCR;
            message.obj = OCRresult;
            m_messageHandler.sendMessage(message);

        }
    }
    //endregion

    //region Handler
    public class MessageHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case ConstantDefine.RESULT_OCR:
                    String OCRresult=String.valueOf(msg.obj);
                     //텍스트 변경

                    // 원형 프로그레스바 종료
                    if(m_objProgressCircle.isShowing() && m_objProgressCircle !=null)
                        m_objProgressCircle.dismiss();
                    ProgressFlag = false;

                    ////ToastmakeText(mContext,getResources().getString(R.string.CompleteMessage),//ToastLENGTH_SHORT).show();

                    ArrayList<String> ocrResultList = new ArrayList<String>(Arrays.asList(OCRresult.split("\\n")));
                    getOcrResultList(ocrResultList);
                    break;
            }
        }
    }
    //endregion

    public void Test()
    {
//        String lang = "eng";
        image = BitmapFactory.decodeResource(getResources(), R.drawable.sampledata);
        mDataPath = getFilesDir()+ "/tesseract/";

//        checkFile2(new File(mDataPath + "tessdata/"),lang);

        String lang = "";
        for (String Language : mLanguageList) {
            checkFile(new File(mDataPath + "tessdata/"), Language);
            lang += Language + "+";
        }
        lang = lang.substring(0,lang.length()-1);
        m_Tess = new TessBaseAPI();
        m_Tess.init(mDataPath, lang);
    }

    //Process an Image
    public void processImage() {
        OCRThread ocrThread = new OCRThread(image);
        ocrThread.setDaemon(true);
        ocrThread.start();
    }

    private void getOcrResultList(ArrayList<String> list){

        // 어댑터 생성
        arrayAdapter = new ArrayAdapter<String>(OcrActivity2.this,
                android.R.layout.simple_list_item_1, list);

        // 어댑터 설정
        lvOcrResultList = (ListView) findViewById(R.id.OcrResultList);
        lvOcrResultList.setAdapter(arrayAdapter);


        //OcrResultList.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 하나의 항목만 선택할 수 있도록 설정
        arrayAdapter.notifyDataSetChanged();

        lvOcrResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String) parent.getItemAtPosition(position);
                //클립보드 사용 코드
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("COPY" + position, selected_item); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
                clipboardManager.setPrimaryClip(clipData);

                //복사가 되었다면 토스트메시지 노출
                //ToastmakeText(OcrActivity2.this, "ID가 복사되었습니다.", //ToastLENGTH_SHORT).show();

                String TAG = "MYACTIVITY";
                Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~복사됨~~~~~~~~~~~~~~~~~~~~~~~~~`");

            }
        });
    }
}
