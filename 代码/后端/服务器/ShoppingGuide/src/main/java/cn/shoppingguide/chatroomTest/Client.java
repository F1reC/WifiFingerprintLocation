package cn.shoppingguide.chatroomTest;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static String name;
    private static Socket socket;
    public static void main(String[] args) {
        try {
            name = "testuser";
            Socket s = new Socket("127.0.0.1",30001);
            socket = s;
            System.out.println("客户端IP:"+s.getLocalAddress()+"端口"+s.getPort());
            //构建IO流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            //建立键盘输入：
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("请输入发送消息内容：");
                bw.write(name+":"+scanner.nextLine()+"\n");
                bw.newLine();
                bw.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取服务器返回的消息数据
                System.out.println(s.getInetAddress().getLocalHost()+":"+s.getPort()+">>"+br.readLine());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
