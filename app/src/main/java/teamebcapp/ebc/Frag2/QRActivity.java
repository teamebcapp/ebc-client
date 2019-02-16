package teamebcapp.ebc.Frag2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;

public class QRActivity extends AppCompatActivity {
    String resultBcSeqStr;
    int resultBcSeq;

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
                Toast.makeText(this, "취소되었습니다", Toast.LENGTH_LONG).show();
            } else {
                resultBcSeqStr = result.getContents();
                resultBcSeqStr=resultBcSeqStr.replace("EBCAppBcSeq", "");
                resultBcSeq = Integer.parseInt(resultBcSeqStr);

                BusinessCardService userServiceGetBC = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                BusinessCard businesscard = new BusinessCard(InfoUser.transuserID, resultBcSeq,InfoUser.MyBcSeq);
                Call<BusinessCard> call = userServiceGetBC.PostBC(businesscard,InfoUser.access_token);
                call.enqueue(new Callback<BusinessCard>() {
                    @Override
                    public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {
                        try {

                            Toast.makeText(getApplicationContext(), "명함교환이 완료되었습니다", Toast.LENGTH_LONG).show();
                            Intent loginIntent = new Intent(QRActivity.this, MainActivity.class);
                            QRActivity.this.startActivity(loginIntent);
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
}
