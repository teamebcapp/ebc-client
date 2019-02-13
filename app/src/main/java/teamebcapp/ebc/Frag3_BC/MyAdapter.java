package teamebcapp.ebc.Frag3_BC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.R;

public class MyAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<MyItem> mItems = new ArrayList<MyItem>();

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

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_list_item, parent, false);
        }

        final TextView idText = convertView.findViewById(R.id.idText);
        idText.setText(InfoUser.transuserID);
        final EditText nameText = convertView.findViewById(R.id.nameText);
        final EditText posiText = convertView.findViewById(R.id.posiText);
        final EditText comText = convertView.findViewById(R.id.comText);
        final EditText dutyText = convertView.findViewById(R.id.dutyText);
        final EditText phoneText = convertView.findViewById(R.id.phoneText);
        final EditText mailText = convertView.findViewById(R.id.mailText);
        final EditText departText = convertView.findViewById(R.id.departText);
        final EditText teamText = convertView.findViewById(R.id.teamText);
        final EditText telText = convertView.findViewById(R.id.telText);
        final EditText faxText = convertView.findViewById(R.id.faxText);
        final EditText addText = convertView.findViewById(R.id.addText);


        final Button registerButton = convertView.findViewById(R.id.registerButton);
        final Button cancelButton = convertView.findViewById(R.id.cancelButton);

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
/*        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img) ;
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents) ;*/

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
/*        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());*/

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    /*public void addItem(int BcSeq, String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                        String Depart, String Team, String Tel, String Fax, String Address) {

        MyItem mItem = new MyItem();

        *//* MyItem에 아이템을 setting한다. *//*
        mItem.setName(Name);
        mItem.setContents(Contents);

        *//* mItems에 MyItem을 추가한다. *//*
        mItems.add(mItem);

    }*/

    /*public void addItem() {
        MyItem item = new MyItem();
        mItems.add(item);
    }*/
}
