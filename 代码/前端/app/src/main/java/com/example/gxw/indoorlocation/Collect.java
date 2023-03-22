package com.example.gxw.indoorlocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

//              wifi对应表
//              301： 狗子们      AP1
//              302：XUPT CMCC    AP2
//              303：公安局       AP3
//              304：ceayoeu!     AP4
//              305：Real Fans    AP5


public class Collect extends AppCompatActivity {
    Button btn;
    TextView tv;
    EditText editText;
    EditText editText3;
    int count;
    float Xcoord;//X轴坐标
    float Ycoord;//X轴坐标

    StringBuffer buffer;
    float AP1, AP2, AP3, AP4, AP5;
//    float 狗子们, XUPT CMCC, 公安局, ceayoeu!,Real Fans;


    //实现wifi信息采集工作，只采集特定的信号源
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        btn = (Button) findViewById(R.id.button4);
        tv = (TextView) findViewById(R.id.textView2);
        editText=findViewById(R.id.editText);
        editText3=findViewById(R.id.editText3);


        System.out.println("测试坐标");
        System.out.println(Xcoord);
        System.out.println(Ycoord);
        tv.setText("AP1:" + AP1 + "\n"+"AP2:" + AP2 + "\n"+ "AP3:" + AP3 + "\n"+ "AP4:" + AP4 + "\n"+ "AP5:" + AP5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Xcoord=Float.valueOf(editText.getText().toString());
                Ycoord=Float.valueOf(editText3.getText().toString());
                init();
                waiting();
            }
        });
    }

    public void init() {                 //数据初始化
        AP1 = 0;
        AP2 = 0;
        AP3 = 0;
        AP4 = 0;
        AP5 = 0;
        count = 0;
    }
    //扫描附近WiFi信号
    private void scan() {

        WifiManager wm;           //WifiManager

        wm=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        boolean b = wm.startScan();//开始扫描AP
        //System.out.println(b);

        List<ScanResult> results = wm.getScanResults();  //拿到扫描的结果
        System.out.println(results);
        for (ScanResult result : results) {
            if (result.SSID.equals("CMCC-ddm4") && result.level > -100) {
                AP1 = result.level;
            }
            if (result.SSID.equals("CMCC-d5fe") && result.level > -100) {
                AP2 = result.level;
            }
            if (result.SSID.equals("NONE BY") && result.level > -100) {
                AP3 = result.level;
            }
            if (result.SSID.equals("HONOR 20 PRO") && result.level > -100) {
                AP4 = result.level;
            }
            if (result.SSID.startsWith("CMCC-ddm4") && result.level > -100) {
                AP5 = result.level;
            }
        }
    }
    private void delay(int ms){     //延时函数
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void waiting() {
        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                Object[] objects = new Object[5];
                while (AP1 == 0 || AP2 == 0 || AP3 == 0 || AP4 == 0 || AP5 == 0) {
                    scan();
                }
                objects[0] = AP1;
                objects[1] = AP2;
                objects[2] = AP3;
                objects[3] = AP4;
                objects[4] = AP5;
                publishProgress(objects);
                return null;
            }
            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
                AP1 = (float) values[0];
                AP2 = (float) values[1];
                AP3 = (float) values[2];
                AP4 = (float) values[3];
                AP5 = (float) values[4];
                System.out.println(AP1);
                System.out.println(AP2);
                System.out.println(AP3);
                System.out.println(AP4);
                System.out.println(AP5);
                //显示到页面上
                tv.setText("AP1:" + AP1 + "\n" + "AP2:" + AP2 + "\n" + "AP3:" + AP3 + "\n" + "AP4:" + AP4 + "\n" + "AP5:" + AP5);

                delay(300);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;
                        HttpURLConnection conn = null;
                        try {
                            url = new URL("http://192.168.10.27:7001/Main"); //后台服务器
                            conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.connect();
                            OutputStream out = conn.getOutputStream();
                            //创建字符流对象并用高效缓冲流包装它，便获得最高的效率
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

                            JSONObject jsonObject=new JSONObject();
                            jsonObject.put("Xcoord",Xcoord);
                            jsonObject.put("Ycoord",Ycoord);
                            jsonObject.put("AP1",AP1);
                            jsonObject.put("AP2",AP2);
                            jsonObject.put("AP3",AP3);
                            jsonObject.put("AP4",AP4);
                            jsonObject.put("AP5",AP5);

                            bw.write(jsonObject.toString());//把json字符串写入缓冲区中
                            System.out.println(jsonObject.toString());
                            bw.flush();//刷新缓冲区，把数据发送出去
                            out.close();
                            bw.close();//使用完关闭

                            //接收服务器数据

                            if (conn.getResponseCode() == 200) {    // 服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。
                                //字符流读取服务端返回的数据
                                InputStream in = conn.getInputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                String str = null;
                                buffer = new StringBuffer();
                                while ((str = br.readLine()) != null) {
                                    buffer.append(str);
                                }

                                Looper.prepare();
                                Toast.makeText(Collect.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                                Looper.loop();

                                in.close();
                                br.close();
                            }
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        };
        asyncTask.execute();
    }

}
