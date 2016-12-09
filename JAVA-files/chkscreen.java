package rkapoors.attendanceapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class chkscreen extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_chkscreen);

        Intent nin=getIntent();
        final String results=nin.getStringExtra("results");

        String rolls[]=new String[100];
        for(int i=0;i<100;i++) rolls[i]="";
        int ind=0;
        int l=results.length();
        String temp="";
        for(int i=0;i<l;i++)
        {
            if(results.charAt(i)==' ')
            {
                rolls[ind++]=temp;
                temp="";
            }
            else
            {
                temp=temp+results.charAt(i);
            }
        }
        rolls[ind++]=temp;
        setListAdapter(new ArrayAdapter<>(this, R.layout.activity_chkscreen,R.id.label,rolls));
    }
}
