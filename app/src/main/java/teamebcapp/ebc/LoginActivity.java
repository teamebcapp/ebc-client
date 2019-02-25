package teamebcapp.ebc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.common.utils.SharedPreferManager;
import teamebcapp.ebc.ocr.ExamCVActivity;
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
                            if (response.isSuccessful() == true) {
                                if (response.body().UserSeq != 0) {
                                    InfoUser.transuserID=userID;

                                    String access_token = response.headers().get("access_token");
                                    SharedPreferManager.setAccessToken(context , access_token);

                                    InfoUser.access_token = access_token;

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

        final String access_token = SharedPreferManager.getAccessToken(context);
        if(access_token != null && !access_token.equals("")) {
            UserService userServicelogin = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
            Call<Map<String,Object>> call = userServicelogin.GetValidToken(access_token);
            call.enqueue(new Callback<Map<String,Object>>() {
                @Override
                public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                    try {
                        if (response.isSuccessful() == true) {
                            if( response.body().get("ResultCode").equals("200") ) {
                                InfoUser.transuserID = response.headers().get("user_id");
                                InfoUser.access_token = access_token;
                                Toast.makeText(getApplicationContext(), "자동로그인되었습니다", Toast.LENGTH_SHORT).show();
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(loginIntent);
                            }
                        }
                    } catch (Exception e) {
                    }

                }
                @Override
                public void onFailure(Call<Map<String,Object>> call, Throwable t) {
                    // handle failure
                    call.cancel();
                }
            });
        }
    }
}