package com.example.gxw.indoorlocation;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//简单的注册登录界面
public class LoginActivity extends AppCompatActivity
{
    //参数定义
    @SuppressLint("WrongViewCast")
    EditText user,pwo;
    Button button5,buttonR;
    //显示登录信息的文本框
    TextView tvC;

    //参数初始化
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //展示布局
        setContentView(R.layout.login);
        initView();

        //这个是干嘛的*
        //为登录按钮设置点击监听，但目前没用，位置也不对，应该放在下面
        //调用下方的MyButtonListener() 在下方文本框显示登录信息
        button5.setOnClickListener(new MyButtonListener());
    }

    //参数初始化
    @SuppressLint("WrongViewCast")
    private void initView ()
    {
        user = findViewById(R.id.user);//寻找布局文件里ID与自己定义的绑定 用户名
        pwo = findViewById(R.id.pwo);//密码
        tvC = findViewById(R.id.tvRC);
        button5 = findViewById(R.id.button5);//登录
        //点击登录跳转
        //这里要操作
        //~


        //点击注册跳转
        buttonR = findViewById(R.id.buttonR);//注册
        //点击监听
        buttonR.setOnClickListener(new View.OnClickListener()
        {

            //没看懂？
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //显示登录信息 这是啥 感觉没用 //确实没用*
    public void Login(View view)
    {

    }

    //为啥没绑定登录按钮就能显示？
    class MyButtonListener implements View.OnClickListener
    {
        public void onClick(View view)
        {
            //点击后就显示用户名，密码文本框中的信息
            String name=user.getText().toString();
            String pow=pwo.getText().toString();
            tvC.setText("用户名:"+name+"密码:"+pow+"登录");
            //在这里比对登录信息吧
            //文本框显示未登录成功
            //成功直接跳转
            //要发送含信息的请求 后端比对 返回true or false
            //true就跳转 false就在文本框显示登录失败

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        FormBody.Builder params = new FormBody.Builder();
                        params.add("username",name);
                        params.add("password",pow);
                        OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                        Request request = new Request.Builder()
                                .url( "https://p556045p45.zicp.fun/select") //后端请求接口的路径
                                .post(params.build())
                                .build(); //创造http请求
                        Response response = client.newCall(request).execute(); //执行发送指令

                        //收信息
                        JSONObject jasonObject = new JSONObject(response.body().string());
                        Log.d("登录状态",jasonObject.getString("info"));


                        //是否联网
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"网络请求成功！",Toast.LENGTH_SHORT).show();
                            }
                        });


                        //判断是否登录成功
                        if(jasonObject.getString("info").equals("密码正确,进入系统...")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                                }
                            });

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            Thread.interrupted();
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"网络请求失败！",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();

        }
    }
}
