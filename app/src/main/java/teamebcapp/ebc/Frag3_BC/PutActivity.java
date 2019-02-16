package teamebcapp.ebc.Frag3_BC;

import android.content.Context;
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
import teamebcapp.ebc.LoginActivity;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.common.utils.SharedPreferManager;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class PutActivity extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);
        context = this;

        final TextView idText = findViewById(R.id.idText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final EditText passwordText2 = findViewById(R.id.passwordText2);
        final Button reviseButton = findViewById(R.id.reviseButton);
        final Button cancelButton = findViewById(R.id.cancelButton);

        final EditText nameText = findViewById(R.id.nameText);
        final EditText posiText = findViewById(R.id.posiText);
        final EditText comText = findViewById(R.id.comText);
        final EditText dutyText = findViewById(R.id.dutyText);
        final EditText phoneText = findViewById(R.id.phoneText);
        final EditText mailText = findViewById(R.id.mailText);

        ///회원가입시 정보가져오기
        UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        Call<User> call = userService.GetUserInfo(InfoUser.transuserID,InfoUser.access_token);
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
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cancelIntent = new Intent(PutActivity.this, MainActivity.class);
                PutActivity.this.startActivity(cancelIntent);
            }
        });

        reviseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                idText.setText(InfoUser.transuserID);
                final String userPassword1 = passwordText.getText().toString();
                final String userPassword2 = passwordText2.getText().toString();
                final String userName = nameText.getText().toString();
                final String userCom = comText.getText().toString();
                final String userPosi = posiText.getText().toString();
                final String userDuty = dutyText.getText().toString();
                final String userPhone = phoneText.getText().toString();
                final String userEmail = mailText.getText().toString();

                if (userPassword1 != null && userPassword2 != null) {
                    if (userPassword1.equals(userPassword2)) { //checking whether passowords are same

                        UserService userService = teamebcapp.ebc.Retrofit.retrofit.create
                                (UserService.class);
                        User user = new User(userID, userPassword1, userName, userCom, userPosi, userDuty, userPhone, userEmail);
                        Call<User> call = userService.PutUser(user,InfoUser.access_token);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                try {
                                    if (response.isSuccessful() == true) {
                                        if (response.body().UserSeq != 0) {

                                            String access_token = response.headers().get("access_token");
                                            SharedPreferManager.setAccessToken(context , access_token);

                                            InfoUser.access_token = access_token;

                                            Toast.makeText(getApplicationContext(), "회원정보가 수정되었습니다", Toast.LENGTH_SHORT).show();
                                            Intent loginIntent = new Intent(PutActivity.this, LoginActivity.class);
                                            PutActivity.this.startActivity(loginIntent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "계정에 문제가 있습니다", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "서버에 문제가 있습니다", Toast.LENGTH_SHORT).show();
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
                }else {Toast.makeText(getApplicationContext(), "비밀번호를 입력하십시오", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}
