package com.example.gxw.indoorlocation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Api;

import org.json.JSONObject;

import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//
//注册类
public class RegisterActivity extends AppCompatActivity {
    //参数
    EditText user, pwo, rpw, uname;
    Button buttonR, buttonB;
    RadioGroup sexq;
    CheckBox checkBox3;
    String sex="男";

    //参数初始化
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        {
            buttonB = findViewById(R.id.buttonB);//寻找布局文件里ID与自己定义的绑定 返回按钮
            buttonR = findViewById(R.id.buttonR);//注册按钮
            uname = findViewById(R.id.uname);//文本框 用户名
            pwo = findViewById(R.id.pwo);//文本框 密码
            rpw = findViewById(R.id.rpw);//文本框 确认密码
            user = findViewById(R.id.user);//文本框 姓名
            sexq = findViewById(R.id.sexq);//按钮 男女
            checkBox3 = findViewById(R.id.checkBox3);//阅读会员协议

            //选择 男 女
            //复选框的写法
            sexq.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i)
                {
                    switch (radioGroup.getCheckedRadioButtonId())
                    {
                        case R.id.ButtonM:
                            sex = "男";
                            break;
                        case R.id.ButtonW:
                            sex = "女";
                            break;
                    }
                }
            });

            //点击注册按钮
            buttonR.setOnClickListener(new View.OnClickListener()
            {
                @Override

                //打印
                public void onClick(View view)
                {
                    //打印
                    if (checkBox3.isChecked())
                    {
                        //或 填写内容为空
                        if (rpw.getText().toString().trim().equals("") || uname.getText().toString().trim().equals("") || pwo.getText().toString().trim().equals("") || user.getText().toString().trim().equals(""))
                        {
                            Toast.makeText(RegisterActivity.this, "请填写所有内容", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //密码不一致
                        if (!rpw.getText().toString().trim().equals(pwo.getText().toString().trim()))
                        {
                            Toast.makeText(RegisterActivity.this, "两次密码输入不一致，请重新输入", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //输入没问题 成功注册
                        //信息都在get text里，要从这传入数据库
                        String url="http//103.46.128.46";

                        String username = user.getText().toString().trim();
                        String password = pwo.getText().toString().trim();


                        //将string转化为json，传输到服务器
                        //res包含全部信息
                        String res = "欢迎你，你的注册信息如下：\n" + "用户名：" + user.getText().toString().trim() + "\n密码：" + pwo.getText().toString().trim() + "\n姓名：" + uname.getText().toString().trim() + "\n性别" + sex + "\n";

                        //在 这里操作
                        //~
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    FormBody.Builder params = new FormBody.Builder();
                                    params.add("username",username);
                                    params.add("password",password);
                                    OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                                    Request request = new Request.Builder()
                                            .url( "https://p556045p45.zicp.fun/insert") //后端请求接口的路径
                                            .post(params.build())
                                            .build(); //创造http请求
                                    Response response = client.newCall(request).execute(); //执行发送指令

                                    //收信息
                                    JSONObject jasonObject = new JSONObject(response.body().string());
                                    Log.d("登录状态",jasonObject.getString("info"));

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this,"网络请求成功！",Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                    //判断用户名是否重复
                                    if(jasonObject.getString("info").equals("输入的用户名重复请重新输入")){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(RegisterActivity.this,"用户名已存在，请选择其他用户名~",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                    else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(RegisterActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                                                //这里仅是个打印
                                                Toast.makeText(RegisterActivity.this, res, Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }

                                }catch (Exception e){
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this,"网络请求失败！",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }).start();






                    }

                    //未框选请阅读协议
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "请阅读会员协议并勾选", Toast.LENGTH_LONG).show();
                    }

                }
            });

            //点击返回跳转登录界面
            buttonB = findViewById(R.id.buttonB);//寻找布局文件里ID与自己定义的绑定//返回按钮
            //按钮 点击监听
            buttonB.setOnClickListener(new View.OnClickListener()
            {
                //对按钮B点击后  （v是随便定义的）
                @Override
                public void onClick(View v)
                {

                    //退出此界面 返回登录页
                    finish();
                }
            });
        }

    }
}
