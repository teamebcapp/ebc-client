package teamebcapp.ebc;

import android.content.Context;
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

public class LoginActivity extends AppCompatActivity {
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();


                UserService userServicelogin = teamebcapp.ebc.Retrofit.retrofit.create
                        (UserService.class);
                Call<User> call = userServicelogin.GetUser(userID,userPassword);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        try {
                            InfoUser.transuserID = response.body().UserId;

                            if (response.isSuccessful() == true) {
                                if (response.body().UserSeq != 0) {
                                    Toast.makeText(getApplicationContext(), "로그인되었습니다", Toast.LENGTH_SHORT).show();
                                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    LoginActivity.this.startActivity(loginIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"서버에 문제가 있습니다",Toast.LENGTH_SHORT).show();
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
            }
        });
    }
}