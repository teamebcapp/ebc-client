package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.CreateUser;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class manageBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bcactivity);
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

                UserService userServiceMyBC = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                CreateUser UserCall = new CreateUser(userID, userName, userCom, userPos, userDuty, userPhone, userMail, userDepart, userTeam, userTel, userFax, userAdd);
                Call<CreateUser> call = userServiceMyBC.PutBC(UserCall);
                call.enqueue(new Callback<CreateUser>() {
                    @Override
                    public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
                        try {
                            CreateUser result = response.body();
                            Intent registerMyBCIntent = new Intent(manageBCactivity.this, MainActivity.class);
                            manageBCactivity.this.startActivity(registerMyBCIntent);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateUser> call, Throwable t) {
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
                Intent cancelMyBCIntent = new Intent(manageBCactivity.this, MainActivity.class);
                manageBCactivity.this.startActivity(cancelMyBCIntent);
            }
        });
    }
}