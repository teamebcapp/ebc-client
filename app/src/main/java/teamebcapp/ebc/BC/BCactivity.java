package teamebcapp.ebc.BC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import teamebcapp.ebc.LoginActivity;
import teamebcapp.ebc.MainActivity;
import teamebcapp.ebc.R;

public class BCactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcactivity);

        Button manageBC = (Button) findViewById(R.id.manageBC);
        Button registerBC = (Button) findViewById(R.id.registerBC);

        manageBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageBCIntent = new Intent(BCactivity.this, manageBCactivity.class);
                BCactivity.this.startActivity(manageBCIntent);
            }

        });

        registerBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerBCIntent = new Intent(BCactivity.this, registerBCactivity.class);
                BCactivity.this.startActivity(registerBCIntent);
            }

        });
    }
}
