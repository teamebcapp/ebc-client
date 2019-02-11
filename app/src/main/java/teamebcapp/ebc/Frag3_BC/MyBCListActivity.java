package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.Frag2.ListBCActivity;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class MyBCListActivity extends AppCompatActivity {

    private ListView mListView;

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bclist);
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();


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
                        InfoUser.transName=result.get(i).Name;
                        InfoUser.transCompany=result.get(i).Company;
                        InfoUser.transPosition=result.get(i).Position;
                        InfoUser.transDuty=result.get(i).Duty;
                        InfoUser.transPhone=result.get(i).Phone;
                        InfoUser.transEmail=result.get(i).Email;
                        InfoUser.transDepart=result.get(i).Depart;
                        InfoUser.transTeam=result.get(i).Team;
                        InfoUser.transTel=result.get(i).Tel;
                        InfoUser.transFax=result.get(i).Fax;
                        InfoUser.transAddress=result.get(i).Address;
                        InfoUser.transBcSeq=result.get(i).BcSeq;
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
