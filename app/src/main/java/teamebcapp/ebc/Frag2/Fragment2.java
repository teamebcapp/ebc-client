package teamebcapp.ebc.Frag2;

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
public class Fragment2 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2,null);
        Button registerButton = view.findViewById(R.id.registerButton);
        Button listButton = view.findViewById(R.id.listButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerBCIntent = new Intent(getActivity(), registerBCactivity.class);
                getActivity().startActivity(registerBCIntent);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListBCIntent = new Intent(getActivity(), ListBCActivity.class);
                getActivity().startActivity(ListBCIntent);
            }
        });
        return view;
    }

}