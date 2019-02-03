package teamebcapp.ebc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class LoginActivity extends AppCompatActivity {


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://52.231.26.243:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText=(EditText) findViewById(R.id.idText);
        final EditText passwordText=(EditText) findViewById(R.id.passwordText);
        final TextView registerButton= (TextView) findViewById(R.id.registerButton);
        final Button loginButton=(Button) findViewById(R.id.loginButton);

/*
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });*/


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID =idText.getText().toString();
                String userPassword=passwordText.getText().toString();

                UserService userService = retrofit.create(UserService.class);
                Call<User> call = userService.GetUser(userID,userPassword);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User result= null;
                        try {
                            result = response.body();
                        }catch (Exception e){
                        }

                        if(result==null){
                            //토스트
                        }
                        else {
                            if(result.UserSeq != 0) {
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(loginIntent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call,Throwable t) {
                        // handle failure
                        call.cancel();
                    }
                });

            }
            }
        );
    }

}

