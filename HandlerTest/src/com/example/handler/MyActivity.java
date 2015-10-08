package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private Button button;
    private Button run;
    private Looper mLooper;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(">>>>>>>>>","handleMessage");
        }
    };

    private Handler mHandler;

    private int i = 0;
    private Runnable runnable = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = (Button)findViewById(R.id.button);
        run = (Button)findViewById(R.id.run);
        runnable = new Runnable() {
            @Override
            public void run() {

                Log.e(">>>>>>>>>","running"+(i++));

                handler.postDelayed(runnable,1000);
                if(i == 10){
                    handler.removeCallbacks(runnable);
                }
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                mHandler.sendEmptyMessage(1);
                mLooper.quit();

            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                handler.post(runnable);
                Log.e(">>>>>>>>>","view:"+Looper.myLooper()+"  msg:"+Looper.myQueue());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(">>>>>>>>>", "start:" + Looper.myLooper());
                        Looper.prepare();

                        Log.e(">>>>>>>>>", "run:" + Looper.myLooper() + "  msg:" + Looper.myQueue());
                        mLooper = Looper.myLooper();
                        mHandler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                            }
                        };
                        Looper.loop();
                        Log.e(">>>>>>>>>", "end:" + Looper.myLooper() + "  msg:" + Looper.myQueue());
                    }
                }).start();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
