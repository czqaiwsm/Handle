package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

/**
 * @desc 主Activity
 * @creator caozhiqing
 * @data 2015/9/29
 */
public class MainActivity extends Activity {
    public String tag = ">>>>>>>>>>>>";

    public  ThreadLocal<Entity> local = new ThreadLocal<Entity>();

    protected Button button;
    protected Button run;

    public Entity entity1,entity2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.button);
        run = (Button)findViewById(R.id.run);
        entity1 = new Entity("entity1","1");
        entity2 = new Entity("entity2","2");
    }
}