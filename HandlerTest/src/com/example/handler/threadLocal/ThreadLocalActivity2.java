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
public class ThreadLocalActivity2 extends MainActivity implements View.OnClickListener {

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
                test1();
                break;
            case R.id.run:
                test2();
                break;
            default: break;
        }
    }

   private void test1(){

       Log.i(tag,"entity1:"+entity1);
       Log.i(tag,"entity2:"+entity2);
       local.remove();
       //在UI线程中把entity1放入ThreadLocal中
       local.set(entity1);
       //起新线程
       Thread thread1 = new Thread(new MyThread());
       thread1.start();
       Log.i(tag, "UI Thread entity:" + local.get());

//       09-30 11:38:31.247    6991-6991/com.example.handler I/>>>>>>>>>>>>﹕ entity1:com.example.handler.Entity@4195738
//       09-30 11:38:31.247    6991-6991/com.example.handler I/>>>>>>>>>>>>﹕ entity2:com.example.handler.Entity@41957418
//       09-30 11:38:31.247    6991-6991/com.example.handler I/>>>>>>>>>>>>﹕ UI Thread entity:com.example.handler.Entity@41957380
//       09-30 11:38:31.247    6991-7016/com.example.handler I/>>>>>>>>>>>>﹕ Other thread entity :null
//       在Thread1中没有取得entity1,这是因为entity1是在UI线程放入ThreadLocal;只能在UI本线程中取到
   }

    private void test2(){

        Log.i(tag,"entity1:"+entity1);
        Log.i(tag,"entity2:"+entity2);
        local.remove();
        //起新线程
        Thread thread2 = new Thread(new MyThread2());
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(tag, "UI Thread entity:" + local.get());


//        09-30 11:42:00.847    7140-7140/com.example.handler I/>>>>>>>>>>>>﹕ entity1:com.example.handler.Entity@41959090
//        09-30 11:42:00.847    7140-7140/com.example.handler I/>>>>>>>>>>>>﹕ entity2:com.example.handler.Entity@41959128
//        09-30 11:42:00.847    7140-7190/com.example.handler I/>>>>>>>>>>>>﹕ Other thread entity :com.example.handler.Entity@41959128
//        09-30 11:42:02.847    7140-7140/com.example.handler I/>>>>>>>>>>>>﹕ UI Thread entity:null
        //在线程thread2中把entity2存入ThreadLocal，再UI线程没能取到entity2，只在thread2中取到entity2
    }

    class MyThread implements  Runnable{
        @Override
        public void run() {
            //在新线程中看看是否能够取到entity1
             Log.i(tag,"Other thread entity :"+local.get());
        }
    }


    class MyThread2 implements  Runnable{
        @Override
        public void run() {
            //在新线程中讲entity2放入ThreadLocal

            local.set(entity2);
            Log.i(tag,"Other thread entity :"+local.get());
        }
    }
}