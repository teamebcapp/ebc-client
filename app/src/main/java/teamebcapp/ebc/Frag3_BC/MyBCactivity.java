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
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class MyBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bcactivity);

        final EditText idText = (EditText) findViewById(R.id.idText);
        idText.setText("");
        final EditText passwordText1 = (EditText) findViewById(R.id.passwordText);
        passwordText1.setText("");
        //비밀번호확인 한번 받을거니?
        //final EditText passwordText2 = (EditText) findViewById(R.id.passwordText2);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText posiText = (EditText) findViewById(R.id.posiText);
        final EditText comText = (EditText) findViewById(R.id.comText);
        final EditText dutyText = (EditText) findViewById(R.id.dutyText);
        final EditText phoneText =(EditText) findViewById(R.id.phoneText);
        final EditText mailText = (EditText) findViewById(R.id.mailText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword1 = passwordText1.getText().toString();
                //String userPassword2 = passwordText2.getText().toString();
                String userName = nameText.getText().toString();
                String userCom = comText.getText().toString();
                String userPos = posiText.getText().toString();
                String userPhone = phoneText.getText().toString();
                String userduty = dutyText.getText().toString();
                String usermail = mailText.getText().toString();

                UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
                Call<User> call = userService.GetMyUser(userID, userPassword1,userName,userCom,userPos,userPhone,userduty,usermail);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User result = null;
                        
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // handle failure
                        call.cancel();
                    }
        });


        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerMyBCIntent = new Intent(MyBCactivity.this, MyBCactivity.class);
                MyBCactivity.this.startActivity(registerMyBCIntent);
            }
        });
    }
}
