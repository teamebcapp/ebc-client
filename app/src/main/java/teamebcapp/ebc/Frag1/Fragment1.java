package teamebcapp.ebc.Frag1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import teamebcapp.ebc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    EditText textView;
    Button QRgenerator;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1,null);
        QRgenerator =(Button) view.findViewById(R.id.QRgenerator);
        textView=(EditText) view.findViewById(R.id.editText);
        QRgenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               textView.getText().toString();
                }
        });
        return  view;
    }



}