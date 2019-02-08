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
import teamebcapp.ebc.LoginActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class MyBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bcactivity);

        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText posiText = (EditText) findViewById(R.id.posiText);
        final EditText comText = (EditText) findViewById(R.id.comText);
        final EditText dutyText = (EditText) findViewById(R.id.dutyText);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);
        final EditText mailText = (EditText) findViewById(R.id.mailText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);


        UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        Call<User> call = userService.GetUser(InfoUser.transuserID,InfoUser.transuserPass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String userName1=response.body().Name;
                nameText.setText(userName1);
                String userCom1=response.body().Company;
                comText.setText(userCom1);
                String userPos1=response.body().Position;
                posiText.setText(userPos1);
                String userPhone1=response.body().Phone;
                phoneText.setText(userPhone1);
                String userduty1=response.body().Duty;
                dutyText.setText(userduty1);
                String usermail1=response.body().Email;
                mailText.setText(usermail1);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });

        final String userName = nameText.getText().toString();
        final String userCom = comText.getText().toString();
        final String userPos = posiText.getText().toString();
        final String userPhone = phoneText.getText().toString();
        final String userduty = dutyText.getText().toString();
        final String usermail = mailText.getText().toString();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                Call<User> call = userService.GetMyUser(userName,userCom,userPos,userPhone,userduty,usermail);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

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
                Intent registerMyBCIntent = new Intent(MyBCactivity.this, MyBCactivity.class);
                MyBCactivity.this.startActivity(registerMyBCIntent);
            }
        });
    }
}