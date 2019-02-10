/*
package teamebcapp.ebc.Frag2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.CreateUser;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class retrieveBCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_bcactivity);

        UserService userServiceMyBC = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        int user = int bcSeq;
        Call<List<User>> call = userServiceMyBC.PostBC(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {

                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });

        Toast.makeText(getApplicationContext(), "명함을 등록했습니다", Toast.LENGTH_SHORT).show();
        Intent BackBCIntent = new Intent(retrieveBCActivity.this, MainActivity.class);
        retrieveBCActivity.this.startActivity(BackBCIntent);
    }
}*/
