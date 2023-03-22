package com.example.gxw.indoorlocation;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Collect;
    private Button setloca;
    private Button PlanButton;
    private Button shoppingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collect=findViewById(R.id.button);      //信息采集
        setloca=findViewById(R.id.button2);    //定位
        PlanButton=findViewById(R.id.button3);//路径规划
        shoppingList=findViewById(R.id.button7);
        //监听事件
        //启动新的activity
        Collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Collect.class);
                startActivity(intent);
            }
        });

        setloca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,setloca.class);
                startActivity(intent);

            }
        });

        PlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PlanButton.class);
                startActivity(intent);
            }
        });

        shoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,shoppingList.class);
                startActivity(intent);
            }
        });

    }
}
