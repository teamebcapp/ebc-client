package teamebcapp.ebc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText1 = (EditText) findViewById(R.id.passwordText);
        final EditText passwordText2 = (EditText) findViewById(R.id.passwordText2);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText posiText = (EditText) findViewById(R.id.posiText);
        final EditText comText = (EditText) findViewById(R.id.comText);
        final EditText dutyText = (EditText) findViewById(R.id.dutyText);
        final EditText phoneText =(EditText) findViewById(R.id.phoneText);
        final EditText mailText = (EditText) findViewById(R.id.mailText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button backButton = (Button) findViewById(R.id.backButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword1 = passwordText1.getText().toString();
                String userPassword2 = passwordText2.getText().toString();
                String userName = nameText.getText().toString();
                String userCom = comText.getText().toString();
                String userPos = posiText.getText().toString();
                String userPhone = phoneText.getText().toString();
                String userduty = dutyText.getText().toString();
                String usermail = mailText.getText().toString();


                if (userPassword1.equals(userPassword2)) { //비밀번호확인용

                    UserService userServiceregi = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                    User user = new User (userID, userPassword1,userName,userCom,userPos,userduty,userPhone,usermail,null,null,null,null,null);
                    Call<User> call = userServiceregi.PostUser(user);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다", Toast.LENGTH_LONG).show();
                            Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            RegisterActivity.this.startActivity(registerIntent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            // handle failure
                            Toast.makeText(getApplicationContext(), "서버문제입니다", Toast.LENGTH_LONG).show();
                            call.cancel();
                        }
                    });
                }
                else if (!userPassword1.equals(userPassword2)){
                    Toast.makeText(getApplicationContext(), "비밀번호가 같지 않습니다", Toast.LENGTH_LONG).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
