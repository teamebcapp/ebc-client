package teamebcapp.ebc.Frag2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class registerBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bcactivity);


        final Button btn_start_qrcode_reader = (Button) findViewById(R.id.btn_start_qrcode_reader);

        btn_start_qrcode_reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRCode();
            }
        });


        final EditText idText= findViewById(R.id.idText);
        final EditText nameText = findViewById(R.id.nameText);
        final EditText posiText = findViewById(R.id.posiText);
        final EditText comText = findViewById(R.id.comText);
        final EditText dutyText = findViewById(R.id.dutyText);
        final EditText phoneText = findViewById(R.id.phoneText);
        final EditText mailText = findViewById(R.id.mailText);
        final EditText departText = findViewById(R.id.departText);
        final EditText teamText = findViewById(R.id.teamText);
        final EditText telText = findViewById(R.id.telText);
        final EditText faxText = findViewById(R.id.faxText);
        final EditText addText = findViewById(R.id.addText);

        final Button registerButton = findViewById(R.id.registerButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        //다른사람 명함 등록하기
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userName = nameText.getText().toString();
                final String userCom = comText.getText().toString();
                final String userPos = posiText.getText().toString();
                final String userPhone = phoneText.getText().toString();
                final String userDuty = dutyText.getText().toString();
                final String userMail = mailText.getText().toString();
                final String userDepart = departText.getText().toString();
                final String userTeam = teamText.getText().toString();
                final String userTel = telText.getText().toString();
                final String userFax = faxText.getText().toString();
                final String userAdd = addText.getText().toString();

                UserService userServiceMyBC = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                User UserCall = new User(userID, userName, userCom, userPos, userDuty, userPhone, userMail, userDepart, userTeam, userTel, userFax, userAdd);
                Call<User> call = userServiceMyBC.PostBC(UserCall);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        try {
                            Toast.makeText(getApplicationContext(), "명함을 등록했습니다", Toast.LENGTH_SHORT).show();
                            Intent registerMyBCIntent = new Intent(registerBCactivity.this, MainActivity.class);
                            registerBCactivity.this.startActivity(registerMyBCIntent);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // handle failure
                        call.cancel();
                    }
                });

            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //임시방편, 프래그먼트로 돌아가기로 바꿔야함
                Toast.makeText(getApplicationContext(), "명함등록을 취소했습니다.", Toast.LENGTH_SHORT).show();
                Intent cancelMyBCIntent = new Intent(registerBCactivity.this, MainActivity.class);
                registerBCactivity.this.startActivity(cancelMyBCIntent);
            }
        });
    }

    public void startQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}