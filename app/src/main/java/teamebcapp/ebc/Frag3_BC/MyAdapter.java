package teamebcapp.ebc.Frag3_BC;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.R;
import teamebcapp.ebc.user.User;
import teamebcapp.ebc.user.UserService;

public class MyAdapter extends BaseAdapter {

    private ArrayList<MyItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_list_item, parent, false);
        }

        TextView idText = convertView.findViewById(R.id.itemidText);
        TextView nameText = convertView.findViewById(R.id.itemnameText);
        TextView posiText = convertView.findViewById(R.id.itemposiText);
        TextView comText = convertView.findViewById(R.id.itemcomText);
        TextView dutyText = convertView.findViewById(R.id.itemdutyText);
        TextView phoneText = convertView.findViewById(R.id.itemphoneText);
        TextView mailText = convertView.findViewById(R.id.itemmailText);
        TextView departText = convertView.findViewById(R.id.itemdepartText);
        TextView teamText = convertView.findViewById(R.id.itemteamText);
        TextView telText = convertView.findViewById(R.id.itemtelText);
        TextView faxText = convertView.findViewById(R.id.itemfaxText);
        TextView addText = convertView.findViewById(R.id.itemaddText);

        Button putButton = convertView.findViewById(R.id.putButton);
        Button registerButton = convertView.findViewById(R.id.itemregisterButton);
        Button cancelButton = convertView.findViewById(R.id.itemcancelButton);


        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final MyItem myItem = mItems.get(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        idText.setText(myItem.getUserId());
        nameText.setText(myItem.getName());
        comText.setText(myItem.getCompany());
        posiText.setText(myItem.getPosition());
        phoneText.setText(myItem.getPhone());
        dutyText.setText(myItem.getDuty());
        mailText.setText(myItem.getEmail());
        departText.setText(myItem.getDepart());
        teamText.setText(myItem.getTeam());
        telText.setText(myItem.getTel());
        faxText.setText(myItem.getFax());
        addText.setText(myItem.getAddress());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        putButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){/*
                UserService userService= teamebcapp.ebc.Retrofit.retrofit.create(UserService.class);


                Call<User> call = userService.PutBC(myItem.BcSeq);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        try {
                            response.body().BcSeq


                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // handle failure
                        call.cancel();
                    }
                });*/
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //대표설정

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(int BcSeq, String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                        String Depart, String Team, String Tel, String Fax, String Address, int Priority) {
        MyItem addInfo;
        addInfo = new MyItem();
        addInfo.setBcSeq(BcSeq);
        addInfo.setUserId(UserId);
        addInfo.setName(Name);
        addInfo.setCompany(Company);
        addInfo.setPosition(Position);
        addInfo.setDuty(Duty);
        addInfo.setPhone(Phone);
        addInfo.setEmail(Email);
        addInfo.setDepart(Depart);
        addInfo.setTeam(Team);
        addInfo.setTel(Tel);
        addInfo.setFax(Fax);
        addInfo.setAddress(Address);
        addInfo.setPriority(Priority);

        mItems.add(addInfo);
    }
}
