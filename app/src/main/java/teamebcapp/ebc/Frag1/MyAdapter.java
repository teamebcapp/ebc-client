package teamebcapp.ebc.Frag1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import teamebcapp.ebc.R;

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
        TextView nameText = convertView.findViewById(R.id.itemnameText);
        TextView posiText = convertView.findViewById(R.id.itemposiText);
        TextView comText = convertView.findViewById(R.id.itemcomText);
        TextView phoneText = convertView.findViewById(R.id.itemphoneText);
        TextView teamText = convertView.findViewById(R.id.itemteamText);
        //ImageView logoImage=convertView.findViewById(R.id.imageView);

        /*TextView idText = convertView.findViewById(R.id.itemidText);
        TextView dutyText = convertView.findViewById(R.id.itemdutyText);
        TextView mailText = convertView.findViewById(R.id.itemmailText);
        TextView departText = convertView.findViewById(R.id.itemdepartText);
        TextView telText = convertView.findViewById(R.id.itemtelText);
        TextView faxText = convertView.findViewById(R.id.itemfaxText);
        TextView addText = convertView.findViewById(R.id.itemaddText);*/

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final MyItem myItem = mItems.get(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */

        nameText.setText(myItem.getName());
        comText.setText(myItem.getCompany());
        posiText.setText(myItem.getPosition());
        phoneText.setText(myItem.getPhone());
        teamText.setText(myItem.getTeam());
        //logoImage.setImageDrawable(myItem.getIcon());

        /*dutyText.setText(myItem.getDuty());
        mailText.setText(myItem.getEmail());
        departText.setText(myItem.getDepart());
        idText.setText(myItem.getUserId());
        telText.setText(myItem.getTel());
        faxText.setText(myItem.getFax());
        addText.setText(myItem.getAddress());
*/
          return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(int BcSeq, String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                        String Depart, String Team, String Tel, String Fax, String Address) {
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

        mItems.add(addInfo);
    }
}
