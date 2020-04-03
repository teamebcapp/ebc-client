package teamebcapp.ebc.Frag2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;

import teamebcapp.ebc.UserInfo;
import teamebcapp.ebc.R;
import teamebcapp.ebc.Retrofit;
import teamebcapp.ebc.common.global.varForDeleteMyBC;

/**
 * A simple {@link Fragment} subclass.
 */
//내 명함 보기 및 다른사람 명함 등록
public class Fragment2 extends Fragment {

    private static List<BusinessCard> bcs = null;
    public Fragment2() { }

    MyBCPageAdapter myBCPageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, null);
        // 명함 로딩
        //   this.LoadingBusinessCards()
        final ImageView img_qrcode_result = view.findViewById(R.id.iv_generated_qrcode);
        final ViewPager bcsViewPager = view.findViewById(R.id.businessCardsPager);
        //final BCPageAdapter bcPageAdapter = new BCPageAdapter(getFragmentManager());
        myBCPageAdapter = new MyBCPageAdapter(inflater.getContext());

        final Button readQrcodeButton = view.findViewById(R.id.StartQrcodeReaderButton);
        //final Button putBcButton = view.findViewById(R.id.putBCButton);
        final Button deleteBcButton = view.findViewById(R.id.deleteBCButton);


        readQrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent QRIntent = new Intent(getActivity(), QRActivity.class);
                startActivity(QRIntent);
            }
        });

//        putBcButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        deleteBcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("내 명함을 삭제하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteMyBc();
                    }
                });

                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
            }
        });

        //show my BCs to QRcode and String
        BusinessCardService bcService = Retrofit.retrofit.create(BusinessCardService.class);
        String userID = UserInfo.transuserID;
        Call<List<BusinessCard>> call = bcService.GetUserBCList(userID, UserInfo.access_token);
        call.enqueue(new Callback<List<BusinessCard>>() {
            @Override
            public void onResponse(Call<List<BusinessCard>> call, Response<List<BusinessCard>> response) {

                if(response.isSuccessful()) {
                    bcs = response.body();
                    // 명함 슬라이드
                    myBCPageAdapter.setBusinessCards(bcs);
                    bcsViewPager.setAdapter(myBCPageAdapter);

                    if(bcs.isEmpty()) return;
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    Bitmap bitmap = null;
                    try {
                        bitmap = toBitmap(qrCodeWriter.encode("EBCAppBcSeq" + String.valueOf(bcs.get(0).BcSeq), BarcodeFormat.QR_CODE, 250, 250));
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    img_qrcode_result.setImageBitmap(bitmap);
                    UserInfo.MyBcSeq=bcs.get(0).BcSeq;
                    bcsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int i) {
                            QRCodeWriter qrCodeWriter = new QRCodeWriter();
                            try {
                                Bitmap bitmap = toBitmap(qrCodeWriter.encode("EBCAppBcSeq" + String.valueOf(bcs.get(i).BcSeq), BarcodeFormat.QR_CODE, 250, 250));
                                UserInfo.MyBcSeq=bcs.get(i).BcSeq;
                                ////ToastmakeText(container.getContext(), "카드 seq는 " +InfoUser.MyBcSeq, //ToastLENGTH_SHORT).show();
                                img_qrcode_result.setImageBitmap(bitmap);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {
                        }
                    });
                }else{
                }
            }

            @Override
            public void onFailure(Call<List<BusinessCard>> call, Throwable t) {
                call.cancel();
            }
        });

        return view;
    }


    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

    private void setBusinessCardViewPager() {

    }

    private void LoadingBusinessCards() {
        BusinessCardService bcService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
        String user = UserInfo.transuserID;
        Call<List<BusinessCard>> call = bcService.GetUserBCList(user, UserInfo.access_token);
        call.enqueue(new Callback<List<BusinessCard>>() {
            @Override
            public void onResponse(Call<List<BusinessCard>> call, Response<List<BusinessCard>> response) {
                try {
                    bcs = response.body();
                    setBusinessCardViewPager();
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<List<BusinessCard>> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });
    }

    private void putMyBc(){
        BusinessCardService bcService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
        Call<BusinessCard> call = bcService.PutBC(UserInfo.MyBcSeq, UserInfo.access_token);
        call.enqueue(new Callback<BusinessCard>() {
            @Override
            public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {

                try {
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<BusinessCard> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });
    }
    private void deleteMyBc(){
        int myBcSeq= UserInfo.MyBcSeq;
        //ToastmakeText(getContext(), "내 명함 bcseq는 "+myBcSeq, //ToastLENGTH_SHORT).show();
        BusinessCardService businessCardService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
        varForDeleteMyBC var=new varForDeleteMyBC(myBcSeq);
        Call<Map<String,Object>> call = businessCardService.DeleteMyBC(var, UserInfo.access_token);
        call.enqueue(new Callback<Map<String,Object>>() {
            @Override
            public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "내 명함이 삭제되었습니다", Toast.LENGTH_SHORT).show();
                    myBCPageAdapter.notifyDataSetChanged();

                }
                else {
                    Toast.makeText(getContext(), "내 명함 삭제에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String,Object>> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });
    }
    
//    private void getMyBc(){
//        Integer myBcSeq=InfoUser.MyBcSeq;
//        BusinessCardService bcService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
//
//        Call<BusinessCard> call = bcService.GetMyBC(myBcSeq,InfoUser.access_token);
//        call.enqueue(new Callback<BusinessCard>() {
//            @Override
//            public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {
//                if(response.isSuccessful()){
//                    myBc =response.body();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BusinessCard> call, Throwable t) {
//                // handle failure
//                call.cancel();
//            }
//        });
//    }

}