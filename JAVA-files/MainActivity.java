package rkapoors.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button nab,cab,uab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nab=(Button)findViewById(R.id.newbutton);
        cab=(Button)findViewById(R.id.chkbutton);
        uab=(Button)findViewById(R.id.updatebutton);
        cab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chki=new Intent(MainActivity.this,chkactivity.class);
                startActivity(chki);
            }
        });

        nab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent ni=new Intent(MainActivity.this,newactivity.class);
                startActivity(ni);
            }
        });

        uab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ui=new Intent(MainActivity.this,updateactivity.class);
                startActivity(ui);
            }
                               }
        );
    }
}
