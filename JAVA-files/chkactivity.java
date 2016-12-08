package rkapoors.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class chkactivity extends AppCompatActivity {
    private String br[]={"CSE","ECE","CE","ME","ICE","BIO","IPE"};
    private String ct[]={"Tutorial","Lecture"};
    private String branchval="";
    private String categoryval="";
    private String date="";
    final Calendar c = Calendar.getInstance();
LoginAdapter loginadapter;
    int selectedYear=c.get(Calendar.YEAR);
    int selectedMonth=c.get(Calendar.MONTH)+1;
    int selectedDayOfMonth=c.get(Calendar.DAY_OF_MONTH);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chkactivity);

        DatePicker datePicker=(DatePicker)findViewById(R.id.datePicker);
        Button sb=(Button)findViewById(R.id.submitbutton);
        datePicker.init(
                datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        selectedYear = year;
                        selectedMonth = monthOfYear+1;
                        selectedDayOfMonth = dayOfMonth;
                    }
                });

        Spinner branchspinner=(Spinner)findViewById(R.id.branchspinner);
        Spinner categoryspinner=(Spinner)findViewById(R.id.categoryspinner);
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

        date=Integer.toString(selectedDayOfMonth);
        date=date.concat(Integer.toString(selectedMonth));
        date=date.concat(Integer.toString(selectedYear));

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//add here code for reading the rollno. where date,category,branch mtches from table;
                String result=loginadapter.getroll(date,branchval,categoryval);
                if(result.equals("not exist"))
                {
                    Toast.makeText(chkactivity.this,"No record found !!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(chkactivity.this,"Present are "+result,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginadapter.close();
    }
}
