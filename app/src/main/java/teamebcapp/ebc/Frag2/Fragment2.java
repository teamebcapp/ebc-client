package teamebcapp.ebc.Frag2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;

import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.MyBCFrag.BCPageAdapter;
import teamebcapp.ebc.R;
import teamebcapp.ebc.Frag2.QRActivity;
/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
  /*  String resultBcSeqStr;
    int resultBcSeq, MyBcSeq;
*/
    private static List<BusinessCard> bcs = null;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, null);
        // 명함 로딩
        //   this.LoadingBusinessCards()
        final ImageView img_qrcode_result = view.findViewById(R.id.iv_generated_qrcode);
        final ViewPager bcsViewPager = view.findViewById(R.id.businessCardsPager);
        //final BCPageAdapter bcPageAdapter = new BCPageAdapter(getFragmentManager());
        final BCPageAdapter bcPageAdapter = new BCPageAdapter(inflater.getContext());

        final Button btn_start_qrcode_reader = view.findViewById(R.id.btn_start_qrcode_reader);



        btn_start_qrcode_reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*startQRCode();*/

                Intent QRIntent = new Intent(getActivity(), QRActivity.class);
                getActivity().startActivity(QRIntent);
            }
        });

        //show my BCs to QRcode and String
        BusinessCardService bcService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
        String user = InfoUser.transuserID;
        Call<List<BusinessCard>> call = bcService.GetUserBCs(user,InfoUser.access_token);
        call.enqueue(new Callback<List<BusinessCard>>() {
            @Override
            public void onResponse(Call<List<BusinessCard>> call, Response<List<BusinessCard>> response) {

                try {
                    bcs = response.body();
                    // 명함 슬라이드
                    bcPageAdapter.setBusinessCards(bcs);
                    bcsViewPager.setAdapter(bcPageAdapter);


                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    Bitmap bitmap = toBitmap(qrCodeWriter.encode("EBCAppBcSeq" + String.valueOf(bcs.get(0).BcSeq), BarcodeFormat.QR_CODE, 250, 250));
                    img_qrcode_result.setImageBitmap(bitmap);
                    InfoUser.MyBcSeq=bcs.get(0).BcSeq;
                    bcsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int i) {
                            QRCodeWriter qrCodeWriter = new QRCodeWriter();
                            try {
                                Bitmap bitmap = toBitmap(qrCodeWriter.encode("EBCAppBcSeq" + String.valueOf(bcs.get(i).BcSeq), BarcodeFormat.QR_CODE, 250, 250));
                                InfoUser.MyBcSeq=bcs.get(i).BcSeq;
                                img_qrcode_result.setImageBitmap(bitmap);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {

                        }
                    });
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<List<BusinessCard>> call, Throwable t) {
                call.cancel();
            }
        });

        return view;
    }/*

    public void startQRCode() {new IntentIntegrator(MainActivity.this).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(getActivity(), "취소되었습니다", Toast.LENGTH_LONG).show();
            } else {
                resultBcSeqStr = result.getContents();
                resultBcSeqStr.replace("EBCAppBcSeq", "");
                resultBcSeq = Integer.parseInt(resultBcSeqStr);

                BusinessCardService userServiceGetBC = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                BusinessCard businesscard = new BusinessCard(InfoUser.transuserID, resultBcSeq,MyBcSeq);
                Call<BusinessCard> call = userServiceGetBC.PostBC(businesscard,InfoUser.access_token);
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
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
*/
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
        String user = InfoUser.transuserID;
        Call<List<BusinessCard>> call = bcService.GetUserBCs(user,InfoUser.access_token);
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


}