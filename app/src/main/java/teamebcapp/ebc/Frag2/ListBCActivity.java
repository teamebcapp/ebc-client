package teamebcapp.ebc.Frag2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.CreateUser;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class ListBCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bc);
        UserService userServiceMyBC = teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        String user = InfoUser.transuserID;
        Call<List<User>> call = userServiceMyBC.GetBCs(user);


        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                try {
                    List<User> result=response.body();
                    result.get(0);
                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
                    result.get(1);

                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // handle failure
                call.cancel();
            }
        });

        Toast.makeText(getApplicationContext(), "명함을 등록했습니다", Toast.LENGTH_SHORT).show();
        Intent registerMyBCIntent = new Intent(ListBCActivity.this, MainActivity.class);
        ListBCActivity.this.startActivity(registerMyBCIntent);
    }

}
