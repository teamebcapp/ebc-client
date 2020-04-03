package teamebcapp.ebc.Frag2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.R;

public class MyBCPageAdapter extends PagerAdapter {
    //int mNumOfTabs;
    private List<BusinessCard> bcs = new ArrayList<BusinessCard>();

    private Context context;

    public MyBCPageAdapter(Context context){
        this.context = context;
    }
    public void setBusinessCards(List<BusinessCard> bcs) {
        this.bcs = bcs;
    }

    public List<BusinessCard> getBcs() {
        return this.bcs;
    }
    public void addBc(BusinessCard bc) {
        this.bcs.add(bc);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.my_bc_fregment, container, false);

        TextView companyT = (TextView) v.findViewById(R.id.compony);
        companyT.setText(this.bcs.get(position).Company);
        TextView departT = (TextView) v.findViewById(R.id.depart);
        departT.setText(this.bcs.get(position).Depart);
        TextView nameT = (TextView) v.findViewById(R.id.name);
        nameT.setText(this.bcs.get(position).Name);
        TextView phoneT = (TextView) v.findViewById(R.id.phone);
        phoneT.setText(this.bcs.get(position).Phone);
        TextView emailT = (TextView) v.findViewById(R.id.email);
        emailT.setText(this.bcs.get(position).Email);
        TextView addressT = (TextView) v.findViewById(R.id.address);
        addressT.setText(this.bcs.get(position).Address);

        container.addView(v);
        return v;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
    @Override
    public int getCount() {
        return bcs.size();
    }

    @Override
    public int getItemPosition(Object object) { return POSITION_NONE;}

}
