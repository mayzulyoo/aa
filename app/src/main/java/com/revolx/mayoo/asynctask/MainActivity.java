package com.revolx.mayoo.asynctask;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button btn;
ProgressBar pb;
TextView tv;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=(ProgressBar)findViewById(R.id.pb);
        tv=(TextView)findViewById(R.id.tv);
        btn=(Button)findViewById(R.id.process);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ProcessTask dTask=new ProcessTask();
                dTask.execute(100);
            }
        });

    }
    class ProcessTask extends AsyncTask<Integer,Integer,String> {

        @Override
        protected String doInBackground(Integer... integers) {
            for(int i=0;i<=100;i++)
            {
                publishProgress(i);
                try{
                    Thread.sleep(integers[0]);
                }
                catch(Exception e)
                {

                }
            }
            return "Completion of execution";
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected void onProgressUpdate(Integer... progress)
        {
            pb.setProgress(progress[0]);
            tv.setText(progress[0]+"%");
            super.onProgressUpdate(progress);
        }
        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        protected void onPostExecute(String result)
        {
            setTitle(result);
            super.onPostExecute(result);
        }
    }
}
