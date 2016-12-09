package rkapoors.attendanceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread time=new Thread()
        {
            public void run()
            {
                try{
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally{
                    Intent intent=new Intent(splashscreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        time.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }

}
