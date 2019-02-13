package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class MyBCListActivity extends AppCompatActivity {

    private ListView mListView;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bclist);

        myAdapter=new MyAdapter();
        mListView = (ListView)findViewById(R.id.listView);
        mListView.setAdapter(myAdapter);

        /* 아이템 추가 및 어댑터 등록 */



        UserService userService= teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        String user = InfoUser.transuserID;
        Call<List<User>> call = userService.GetUserBCs(user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                try {
                    List<User> result=response.body();

                    Iterator iterator = result.iterator();
                    int count=0;
                    while (iterator.hasNext()) {
                        count++;
                        String element = (String) iterator.next();

                    }

                    for(int i=0;i<count;i++){

                    }

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
        Intent registerMyBCIntent = new Intent(MyBCListActivity.this, MainActivity.class);
        MyBCListActivity.this.startActivity(registerMyBCIntent);

    }
}
