package teamebcapp.ebc.Frag3_BC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import teamebcapp.ebc.R;
import teamebcapp.ebc.user.UserService;

public class MyBCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bcactivity);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText1 = (EditText) findViewById(R.id.passwordText);
        //비밀번호확인 한번 받을거니?
        //final EditText passwordText2 = (EditText) findViewById(R.id.passwordText2);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText posiText = (EditText) findViewById(R.id.posiText);
        final EditText comText = (EditText) findViewById(R.id.comText);
        final EditText dutyText = (EditText) findViewById(R.id.dutyText);
        final EditText phoneText =(EditText) findViewById(R.id.phoneText);
        final EditText mailText = (EditText) findViewById(R.id.mailText);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button backButton = (Button) findViewById(R.id.cancelButton);

        String userID = idText.getText().toString();
        String userPassword1 = passwordText1.getText().toString();
        //String userPassword2 = passwordText2.getText().toString();
        String userName = nameText.getText().toString();
        String userCom = comText.getText().toString();
        String userPos = posiText.getText().toString();
        String userPhone = phoneText.getText().toString();
        String userduty = dutyText.getText().toString();
        String usermail = mailText.getText().toString();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService userService = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
            }
        });
    }
}
