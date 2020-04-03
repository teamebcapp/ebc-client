package teamebcapp.ebc.Frag2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;
import teamebcapp.ebc.UserInfo;

public class QRActivity extends AppCompatActivity {

    int resultBcSeq;


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startQRCode();
    }

    public void startQRCode() {new IntentIntegrator(this).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                //ToastmakeText(this, "취소되었습니다", //ToastLENGTH_LONG).show();
            } else {
                String resultBcSeqStr = result.getContents();
                if(!checkValidQRcode(resultBcSeqStr)) {
                    //ToastmakeText(getApplicationContext(), "유효하지 않은 QR 코드입니다. 다시 시도해 주십시오.", //ToastLENGTH_LONG).show();
                }
                resultBcSeqStr = resultBcSeqStr.replace("EBCAppBcSeq", "");
                resultBcSeq = Integer.parseInt(resultBcSeqStr);

                BusinessCardService userServiceGetBC = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                BusinessCard businesscard = new BusinessCard(UserInfo.transuserID, resultBcSeq, UserInfo.MyBcSeq);
                Call<BusinessCard> call = userServiceGetBC.PostBC(businesscard, UserInfo.access_token);
                call.enqueue(new Callback<BusinessCard>() {
                    @Override
                    public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {
                        try {
                            //ToastmakeText(getApplicationContext(), "명함교환이 완료되었습니다", //ToastLENGTH_LONG).show();
                            finish();
                        } catch (Exception e)
                        {
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

    private boolean checkValidQRcode(String result){
        String compareString="EBCAppBcSeq";
        if(result.substring(0, compareString.length()).equals(compareString)){
            return true;
        }
        return false;
    }
}
