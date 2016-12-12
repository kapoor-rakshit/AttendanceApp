package rkapoors.attendanceapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class compiledelactivity extends AppCompatActivity {
    private String br[]={"CSE","ECE","CE","ME","ICE","BIO","IPE"};
    private String ct[]={"Lecture","Tutorial","Lab"};
    private String gp[]={"NA","G1","G2","G3","G4"};
    private String yr[]={"First","Second","Third","Final"};
    private String branchval="";
    private String categoryval="";
    private String rollno="";
    private String groupval="";
    private String yearval="";
    LoginAdapter loginadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiledelactivity);

        Button del=(Button)findViewById(R.id.delbutton);
        Button comp=(Button)findViewById(R.id.compilebutton);
        Spinner branchspinner=(Spinner)findViewById(R.id.branchspinner);
        Spinner categoryspinner=(Spinner)findViewById(R.id.categoryspinner);
        Spinner groupspinner=(Spinner)findViewById(R.id.groupspinner);
        Spinner yearspinner=(Spinner)findViewById(R.id.yearspinner);
        final EditText roll=(EditText)findViewById(R.id.editText2);
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
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(v);
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             rollno=roll.getText().toString();
                int res=loginadapter.compileentry(branchval,categoryval,rollno,groupval,yearval);
                int mx=loginadapter.getmxofcategory(branchval,categoryval,groupval,yearval);
                Toast.makeText(compiledelactivity.this,rollno+" attended "+res+" / "+mx+" "+categoryval+"s",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Sure to delete records of "+branchval);
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                loginadapter.deleteentry(branchval);
                Toast.makeText(compiledelactivity.this,"Records deleted",Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginadapter.close();
    }
}
