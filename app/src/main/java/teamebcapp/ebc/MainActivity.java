package teamebcapp.ebc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import teamebcapp.ebc.Frag1.Fragment1;
import teamebcapp.ebc.Frag2.Fragment2;
import teamebcapp.ebc.Frag3_BC.Fragment3;
import teamebcapp.ebc.common.utils.SharedPreferManager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Context context;
/*    static   {
        System . loadLibrary ( "native-lib" ) ;
    }*/

    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;

    private ImageButton btn_tab1, btn_tab2, btn_tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯에 대한 참조
        Button logoutButton=findViewById(R.id.logoutButton);
        btn_tab1 =findViewById(R.id.btn_tab1);
        btn_tab2 =findViewById(R.id.btn_tab2);
        btn_tab3 =findViewById(R.id.btn_tab3);
        // 탭 버튼에 대한 리스너 연결
        logoutButton.setOnClickListener(this);
        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
        btn_tab3.setOnClickListener(this);

        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 정함
        callFragment(FRAGMENT1);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tab1 :
                v.setSelected(true);
                findViewById(R.id.btn_tab2).setSelected(false);
                findViewById(R.id.btn_tab3).setSelected(false);
                callFragment(FRAGMENT1);
                break;

            case R.id.btn_tab2 :
                v.setSelected(true);
                findViewById(R.id.btn_tab1).setSelected(false);
                findViewById(R.id.btn_tab3).setSelected(false);
                callFragment(FRAGMENT2);
                break;

            case R.id.btn_tab3 :
                v.setSelected(true);
                findViewById(R.id.btn_tab1).setSelected(false);
                findViewById(R.id.btn_tab2).setSelected(false);
                callFragment(FRAGMENT3);
                break;

            case R.id.logoutButton:

                SharedPreferManager.removeAllPreferences(LoginActivity.context);

                Toast.makeText(getApplicationContext(), "로그아웃되었습니다", Toast.LENGTH_SHORT).show();
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(logoutIntent);

                break;
        }
    }



    private void callFragment(int frament_no){

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no){
            case 1:
                // '프래그먼트1' 호출
                Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // '프래그먼트2' 호출
                Fragment2 fragment2 = new Fragment2();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

            case 3:
                // '프래그먼트3' 호출
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.fragment_container, fragment3);
                transaction.commit();
                break;
        }
    }


}