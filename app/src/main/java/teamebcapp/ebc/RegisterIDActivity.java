package teamebcapp.ebc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class RegisterIDActivity extends AppCompatActivity {

    EditText idText;
    EditText passwordText1;
    EditText passwordText2;
    EditText nameText;
    EditText posiText;
    EditText comText;
    EditText dutyText;
    EditText phoneText;
    EditText mailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_id);

        idText = (EditText) findViewById(R.id.idText);
        passwordText1 = (EditText) findViewById(R.id.passwordText);
        passwordText2 = (EditText) findViewById(R.id.passwordText2);
        nameText = (EditText) findViewById(R.id.nameText);
        posiText = (EditText) findViewById(R.id.posiText);
        comText = (EditText) findViewById(R.id.comText);
        dutyText = (EditText) findViewById(R.id.dutyText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        mailText = (EditText) findViewById(R.id.mailText);
        Button registerButton = (Button) findViewById(R.id.joinButton);
        Button backButton = (Button) findViewById(R.id.backButton);

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
                String userDuty = dutyText.getText().toString();
                String userMail = mailText.getText().toString();

                if (userID.isEmpty() || userPassword1.isEmpty() || userPassword2.isEmpty()) {
                    //ToastmakeText(getApplicationContext(), "별표친 부분을 모두 채워주세요", //ToastLENGTH_LONG).show();
                } else {
                    IsRegisterID(userID, userPassword1, userPassword2, userName,
                            userCom, userPos, userDuty, userPhone, userMail);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        passwordText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ImageView setImage = findViewById(R.id.iv_check);
                if (passwordText1.getText().toString().equals(passwordText2.getText().toString())) {
                    setImage.setImageResource(R.drawable.confim);
                } else {

                    setImage.setImageResource(R.drawable.no);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void IsRegisterID(String userID, String userPassword1, String userPassword2, String userName,
                              String userCom, String userPos, String userDuty, String userPhone, String userMail) {
        if (userPassword1.equals(userPassword2)) { //비밀번호확인용

            UserService userServiceregi = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
            User user = new User(userID, userPassword1, userName, userCom, userPos, userDuty, userPhone, userMail, null, null, null, null, null);
            Call<User> call = userServiceregi.PostUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    //ToastmakeText(getApplicationContext(), "회원가입이 완료되었습니다", //ToastLENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // handle failure
                    //ToastmakeText(getApplicationContext(), "서버문제입니다", //ToastLENGTH_LONG).show();
                    call.cancel();
                }
            });
        } else if (!userPassword1.equals(userPassword2)) {
            //ToastmakeText(getApplicationContext(), "비밀번호가 같지 않습니다", //ToastLENGTH_LONG).show();
        }
    }
}
