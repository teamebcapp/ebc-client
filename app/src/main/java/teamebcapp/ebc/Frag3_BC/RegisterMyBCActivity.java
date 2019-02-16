package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;


public class RegisterMyBCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_my_bcactivity);

        final TextView idText = findViewById(R.id.idText);
        idText.setText(InfoUser.transuserID);
        final EditText nameText = findViewById(R.id.nameText);
        final EditText posiText = findViewById(R.id.posiText);
        final EditText comText = findViewById(R.id.comText);
        final EditText dutyText = findViewById(R.id.dutyText);
        final EditText phoneText =  findViewById(R.id.phoneText);
        final EditText mailText = findViewById(R.id.mailText);
        final EditText departText = findViewById(R.id.departText);
        final EditText teamText = findViewById(R.id.teamText);
        final EditText telText = findViewById(R.id.telText);
        final EditText faxText = findViewById(R.id.faxText);
        final EditText addText = findViewById(R.id.addText);

        final Button registerButton = findViewById(R.id.registerButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        //taking your info that you registered the ID
        UserService userServiceGet = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        Call<User> call = userServiceGet.GetUserInfo(InfoUser.transuserID,InfoUser.access_token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.isSuccessful() == true) {
                        String userName1 = (String) response.body().Name;
                        nameText.setText(userName1);
                        String userCom1 = (String) response.body().Company;
                        comText.setText(userCom1);
                        String userPos1 = (String) response.body().Position;
                        posiText.setText(userPos1);
                        String userPhone1 = (String) response.body().Phone;
                        phoneText.setText(userPhone1);
                        String userduty1 = response.body().Duty;
                        dutyText.setText(userduty1);
                        String usermail1 = response.body().Email;
                        mailText.setText(usermail1);
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });


        //내명함등록하기
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = InfoUser.transuserID;
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

                BusinessCardService userServiceMyBC = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                BusinessCard UserCall = new BusinessCard(userID, userName, userCom, userPos, userDuty, userPhone,
                        userMail, userDepart, userTeam, userTel, userFax, userAdd);
                Call<BusinessCard> call = userServiceMyBC.PostUserBC(UserCall,InfoUser.access_token);
                call.enqueue(new Callback<BusinessCard>() {
                    @Override
                    public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {
                        try {
                            Toast.makeText(getApplicationContext(), "명함을 등록했습니다", Toast.LENGTH_SHORT).show();
                            Intent cancelMyBCIntent = new Intent(RegisterMyBCActivity.this, MainActivity.class);
                            RegisterMyBCActivity.this.startActivity(cancelMyBCIntent);
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
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "명함등록을 취소했습니다", Toast.LENGTH_SHORT).show();
                Intent cancelMyBCIntent = new Intent(RegisterMyBCActivity.this, MainActivity.class);
                RegisterMyBCActivity.this.startActivity(cancelMyBCIntent);
            }
        });
    }
}