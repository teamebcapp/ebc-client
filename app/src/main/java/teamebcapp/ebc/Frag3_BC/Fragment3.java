package teamebcapp.ebc.Frag3_BC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import teamebcapp.ebc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {


    public Fragment3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, null);

        Button registerBC = view.findViewById(R.id.registerMyBC);
        Button MyBCList =view.findViewById(R.id.MyBCList);
        Button reviseButton =view.findViewById(R.id.reviseButton);

        registerBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerMyBCIntent = new Intent(getActivity(), RegisterMyBCActivity.class);
                getActivity().startActivity(registerMyBCIntent);
            }

        });

        MyBCList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent MyBCListIntent= new Intent(getActivity(),MyBCListActivity.class);
                getActivity().startActivity(MyBCListIntent);
            }
        });


        reviseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent reviseIntent= new Intent(getActivity(),PutActivity.class);
                getActivity().startActivity(reviseIntent);
            }
        });
        return view;

    }
}