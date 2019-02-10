package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.CreateUser;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;


public class MyBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bcactivity);

        final TextView idText = (TextView) findViewById(R.id.idText);
        idText.setText(InfoUser.transuserID);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText posiText = (EditText) findViewById(R.id.posiText);
        final EditText comText = (EditText) findViewById(R.id.comText);
        final EditText dutyText = (EditText) findViewById(R.id.dutyText);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);
        final EditText mailText = (EditText) findViewById(R.id.mailText);
        final EditText departText = findViewById(R.id.departText);
        final EditText teamText = findViewById(R.id.teamText);
        final EditText telText = findViewById(R.id.telText);
        final EditText faxText = findViewById(R.id.faxText);
        final EditText addText = findViewById(R.id.addText);

        final Button registerButton = findViewById(R.id.registerButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        //taking your info that you registered the ID
        UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        Call<User> call = userService.GetUser(InfoUser.transuserID, InfoUser.transuserPass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String userName1 = response.body().Name;
                nameText.setText(userName1);
                String userCom1 = response.body().Company;
                comText.setText(userCom1);
                String userPos1 = response.body().Position;
                posiText.setText(userPos1);
                String userPhone1 = response.body().Phone;
                phoneText.setText(userPhone1);
                String userduty1 = response.body().Duty;
                dutyText.setText(userduty1);
                String usermail1 = response.body().Email;
                mailText.setText(usermail1);
/*                String userdepart1 = response.body().Depart;
                departText.setText(userdepart1);
                String usertel1= response.body().Tel;
                telText.setText(usertel1);
                String userteam1= response.body().Team;
                teamText.setText(userteam1);
                String userfax1= response.body().Fax;
                faxText.setText(userfax1);
                String useradd1= response.body().Address;
                addText.setText(useradd1);*/
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

                UserService userServiceMyBC = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                User UserCall = new User(userID, userName, userCom, userPos, userDuty, userPhone, userMail, userDepart, userTeam, userTel, userFax, userAdd);
                Call<User> call = userServiceMyBC.PostBC(UserCall);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        try {
                            Toast.makeText(getApplicationContext(), "명함을 등록했습니다", Toast.LENGTH_SHORT).show();
                            Intent cancelMyBCIntent = new Intent(MyBCactivity.this, MainActivity.class);
                            MyBCactivity.this.startActivity(cancelMyBCIntent);
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
                Toast.makeText(getApplicationContext(), "명함등록을 취소했습니다", Toast.LENGTH_SHORT).show();
                Intent cancelMyBCIntent = new Intent(MyBCactivity.this, MainActivity.class);
                MyBCactivity.this.startActivity(cancelMyBCIntent);
            }
        });
    }
}