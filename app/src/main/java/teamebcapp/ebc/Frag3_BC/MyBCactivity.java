package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.CreateUser;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;


public class MyBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bcactivity);

        final TextView idText=(TextView) findViewById(R.id.idText);
        idText.setText(InfoUser.transuserID);
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


        //내명함등록하기
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID=InfoUser.transuserID;
                final String userName = nameText.getText().toString();
                final String userCom = comText.getText().toString();
                final String userPos = posiText.getText().toString();
                final String userPhone = phoneText.getText().toString();
                final String userduty = dutyText.getText().toString();
                final String usermail = mailText.getText().toString();
               /* final String userdepart=departText.getText().toString();
                final String userteam=teamText.getText().toString();
                final String usertel=telText.getText().toString();
                final String userfax=faxText.getText().toString();
                final String useradd=addText.getText().toString();*/

               final String userdepart=null,userteam=null,usertel=null,userfax=null,useradd=null;
                UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                CreateUser user=new CreateUser(userID, userName,userCom,userPos,userduty,userPhone,usermail,userdepart,userteam,usertel,userfax,useradd);
                Call<CreateUser> call = userService.PutUser(user);
                call.enqueue(new Callback<CreateUser>() {
                    @Override
                    public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
                        try {
                            Intent registerMyBCIntent = new Intent(MyBCactivity.this, Fragment3.class);
                            MyBCactivity.this.startActivity(registerMyBCIntent);
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
                Intent cancelMyBCIntent = new Intent(MyBCactivity.this, Fragment3.class);
                MyBCactivity.this.startActivity(cancelMyBCIntent);
            }
        });
    }
}