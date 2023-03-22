package com.example.gxw.indoorlocation;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class shopping extends AppCompatActivity {
    private Button buttonx;
    private Button buttony;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        buttonx=findViewById(R.id.button2);      //信息采集
        buttony=findViewById(R.id.button7);
        button11=findViewById(R.id.button);      //信息采集
        button12=findViewById(R.id.button3);
        button13=findViewById(R.id.button10);      //信息采集
        button14=findViewById(R.id.button11);
        buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shopping.this,"已添加至购物清单！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        buttony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shopping.this,"已添加至购物清单！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shopping.this,"已添加至购物清单！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shopping.this,"已添加至购物清单！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shopping.this,"已添加至购物清单！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(shopping.this,"已添加至购物清单！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
