package com.example.handler.threadLocal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.handler.MainActivity;
import com.example.handler.R;

/**
 * @desc 请测试ThreadLocal
 * @creator caozhiqing
 * @data 2015/9/30
 */
public class ThreadLocalActivity extends MainActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button.setOnClickListener(this);
        run.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                break;
            case R.id.run:
                uniqueEntity();
                break;
            default: break;
        }
    }

    private void uniqueEntity(){
        Log.i(tag,"entity1:"+entity1);
        Log.i(tag,"entity2:"+entity2);
        new Thread(new MyThread()).start();
    }

    class MyThread implements  Runnable{
        @Override
        public void run() {
            //在同一线程中，把entity1、entity2都set进去，get到的只有entity2
            local.set(entity1);
            local.set(entity2);
            Log.i(tag,"entity :"+local.get());
        }
    }

}