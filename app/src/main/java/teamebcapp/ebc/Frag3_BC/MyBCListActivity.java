package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        mListView = findViewById(R.id.listView);

        Button cancelButton = findViewById(R.id.cancelbutton);
        /* 아이템 추가 및 어댑터 등록 */



        UserService userService= teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);
        String user = InfoUser.transuserID;
        Call<List<User>> call = userService.GetUserBCs(user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                try {
                    List<User> result=response.body();

                    int i=0,resultsize=result.size();
                    while (resultsize>0) {

                        myAdapter.addItem(result.get(i).BcSeq,result.get(i).UserId,result.get(i).Name,result.get(i).Company,
                                result.get(i).Position,result.get(i).Duty,result.get(i).Phone,result.get(i).Email,result.get(i).Depart,
                                result.get(i).Team,result.get(i).Tel,result.get(i).Fax,result.get(i).Address,result.get(i).UserSeq);
                        mListView.setAdapter(myAdapter);
                        resultsize--;
                        i++;
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
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelMyBCIntent = new Intent(MyBCListActivity.this, MainActivity.class);
                MyBCListActivity.this.startActivity(cancelMyBCIntent);
            }
        });
        }
}
