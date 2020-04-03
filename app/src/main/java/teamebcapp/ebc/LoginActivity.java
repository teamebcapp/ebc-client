package teamebcapp.ebc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.Toast;


import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.common.utils.SharedPreferManager;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class LoginActivity extends AppCompatActivity {
    public static Context context;
    private long   backPressedTime = 0;

    EditText idText;
    EditText passwordText;
    Button registerButton;
    Button loginButton;
    CheckBox checkBox;
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        long FINISH_INTERVAL_TIME = 2000;
        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            backPressedTime = tempTime;
            //ToastmakeText(getApplicationContext(), "뒤로가기 버튼을 다시 누르면 종료됩니다", //ToastLENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        registerButton = findViewById(R.id.joinButton);
        loginButton = findViewById(R.id.loginButton);
        checkBox =findViewById(R.id.checkSave);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterIDActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                login(userID,userPassword);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if(isChecked){
                    checkBox.setChecked(true);
                    SharedPreferManager.setTokenCheck(context,true);
                }
                else {
                    checkBox.setChecked(false);
                    SharedPreferManager.removeCheckInfo(context);
                }
            }
        });


        checkBox.setChecked(SharedPreferManager.getCheck(context));

        if(checkBox.isChecked()) {
            autoLogin();
        }
    }

    private void login(final String userID,final String userPassword){
        UserService userServicelogin = Retrofit.retrofit.create(UserService.class);
        Call<User> call = userServicelogin.GetUser(userID,userPassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        if (response.body().UserSeq != 0) {
                            UserInfo.transuserID=userID;

                            String access_token = response.headers().get("access_token");
                            SharedPreferManager.setAccessToken(context , access_token);

                            UserInfo.access_token = access_token;

                            Toast.makeText(getApplicationContext(), "로그인되었습니다", Toast.LENGTH_SHORT).show();
                            Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.startActivity(MainIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"서버에 문제가 있습니다. 개발자에게 연락하십시오.", Toast.LENGTH_SHORT).show();
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

    private void autoLogin(){
        final String access_token = SharedPreferManager.getAccessToken(context);
        //ToastmakeText(getApplicationContext(), "체크되었씁니다", //ToastLENGTH_SHORT).show();
        if(access_token != null && !access_token.equals("")) {
            Log.e("MYAPP", "autoLogin: ~~~~~~~~~~~~~");
            UserService userServicelogin = Retrofit.retrofit.create(UserService.class);
            Call<Map<String,Object>> call = userServicelogin.GetValidToken(access_token);
            call.enqueue(new Callback<Map<String,Object>>() {
                @Override
                public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                    try {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if(Objects.equals(response.body().get("ResultCode"), "200")) {
                                UserInfo.transuserID = response.headers().get("user_id");
                                UserInfo.access_token = access_token;
                                idText.setText(UserInfo.transuserID);
                                Toast.makeText(getApplicationContext(), "자동 로그인되었습니다", Toast.LENGTH_SHORT).show();
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