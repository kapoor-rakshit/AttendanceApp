package rkapoors.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class updateactivity extends AppCompatActivity {
    private String br[]={"CSE","ECE","CE","ME","ICE","BIO","IPE"};
    private String ct[]={"Tutorial","Lecture"};
    private String branchval="";
    private String categoryval="";
    private String rollno="";
    private String dte="";
    final Calendar c = Calendar.getInstance();
    LoginAdapter loginadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateactivity);

        final int selectedYear=c.get(Calendar.YEAR);
        final int selectedMonth=c.get(Calendar.MONTH)+1;
        final int selectedDayOfMonth=c.get(Calendar.DAY_OF_MONTH);

        Spinner branchspinner=(Spinner)findViewById(R.id.branchspinner);
        Spinner categoryspinner=(Spinner)findViewById(R.id.categoryspinner);

        final EditText roll=(EditText)findViewById(R.id.updateeditText);
        Button sb=(Button)findViewById(R.id.submitbutton);
        loginadapter=new LoginAdapter(this);
        loginadapter=loginadapter.open();

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.branch,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> sa = ArrayAdapter
                .createFromResource(this, R.array.category,
                        android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchspinner.setAdapter(staticAdapter);
        categoryspinner.setAdapter(sa);

        branchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                branchval=br[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                categoryval=ct[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add here code for inserting the rollno,date,category,branch in table;
                dte=Integer.toString(selectedDayOfMonth);
                dte=dte.concat(Integer.toString(selectedMonth));
                dte=dte.concat(Integer.toString(selectedYear));
                rollno=roll.getText().toString();
                String frollno=loginadapter.getroll(dte,branchval,categoryval);
                frollno=frollno.concat(" ").concat(rollno);
                loginadapter.updateentry(dte,branchval,categoryval,frollno);
                Toast.makeText(updateactivity.this,"Attendance Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
