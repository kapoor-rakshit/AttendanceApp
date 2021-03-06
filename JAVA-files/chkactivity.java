package rkapoors.attendanceapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class chkactivity extends AppCompatActivity {
    private String br[]={"CSE","ECE","CE","ME","ICE","BIO","IPE"};
    private String ct[]={"Lecture","Tutorial","Lab"};
    private String gp[]={"NA","G1","G2","G3","G4"};
    private String yr[]={"First","Second","Third","Final"};
    private String branchval="";
    private String categoryval="";
    private String groupval="";
    private String yearval="";
    private String dte="";
    final Calendar c = Calendar.getInstance();
    LoginAdapter loginadapter;

    int selectedYear;
    int selectedMonth;
    int selectedDayOfMonth;
    TextView cdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chkactivity);

        selectedYear=c.get(Calendar.YEAR);
        selectedMonth=c.get(Calendar.MONTH);
        selectedDayOfMonth=c.get(Calendar.DAY_OF_MONTH);
        dte=Integer.toString(selectedDayOfMonth);
        dte=dte.concat(Integer.toString(selectedMonth+1));
        dte=dte.concat(Integer.toString(selectedYear));
        Button cdb=(Button)findViewById(R.id.choosedatebutton);
        Button sb=(Button)findViewById(R.id.submitbutton);
        cdt=(TextView)findViewById(R.id.dttextView);
        String datetoshow=Integer.toString(selectedDayOfMonth)+"-"+Integer.toString(selectedMonth+1)+"-"+Integer.toString(selectedYear);
        cdt.setText(datetoshow);
        // DatePicker datePicker=(DatePicker)findViewById(R.id.datePicker);
        /*datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedYear = year;
                        selectedMonth = monthOfYear+1;
                        selectedDayOfMonth = dayOfMonth;
                        dte=Integer.toString(selectedDayOfMonth);
                        dte=dte.concat(Integer.toString(selectedMonth));
                        dte=dte.concat(Integer.toString(selectedYear));
                    }
                });*/

        Spinner branchspinner=(Spinner)findViewById(R.id.branchspinner);
        Spinner categoryspinner=(Spinner)findViewById(R.id.categoryspinner);
        Spinner groupspinner=(Spinner)findViewById(R.id.groupspinner);
        Spinner yearspinner=(Spinner)findViewById(R.id.yearspinner);
        loginadapter=new LoginAdapter(this);
        loginadapter=loginadapter.open();
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.branch,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> sa = ArrayAdapter
                .createFromResource(this, R.array.category,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> staticAdaptergroup = ArrayAdapter
                .createFromResource(this, R.array.group,
                        android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> sayear = ArrayAdapter
                .createFromResource(this, R.array.year,
                        android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticAdaptergroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sayear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        branchspinner.setAdapter(staticAdapter);
        categoryspinner.setAdapter(sa);
        groupspinner.setAdapter(staticAdaptergroup);
        yearspinner.setAdapter(sayear);

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

        groupspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                groupval=gp[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                yearval=yr[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//add here code for reading the rollno. where date,category,branch mtches from table;
                String result=loginadapter.getroll(dte,branchval,categoryval,groupval,yearval);
                if(result.equals("not exist"))
                {
                    Toast.makeText(chkactivity.this,"No record found !!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(chkactivity.this,"Record found",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(chkactivity.this,chkscreen.class);
                    in.putExtra("results",result);
                    startActivity(in);
                }
            }
        });

        cdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginadapter.close();
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener,selectedYear,selectedMonth,selectedDayOfMonth);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    selectedYear=arg1;
                    selectedMonth=arg2+1;
                    selectedDayOfMonth=arg3;
                    dte=Integer.toString(selectedDayOfMonth);
                    dte=dte.concat(Integer.toString(selectedMonth));
                    dte=dte.concat(Integer.toString(selectedYear));
                    String dshow=Integer.toString(selectedDayOfMonth)+"-"+Integer.toString(selectedMonth)+"-"+Integer.toString(selectedYear);
                    cdt.setText(dshow);
                }
            };
}
