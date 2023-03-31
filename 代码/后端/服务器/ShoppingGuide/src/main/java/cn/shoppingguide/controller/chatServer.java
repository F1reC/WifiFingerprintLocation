package cn.shoppingguide.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
//聊天室
public class chatServer { static List<Socket> cons = new LinkedList<Socket>();
    private static Socket socket = null;

    public static class ServerThread extends Thread{
        private Socket s;

        public ServerThread(Socket socket){
            this.s = socket;
            cons.add(s);
        }

        @Override
        public void run(){
            System.out.print("新用户加入\n");
            System.out.print("当前在线数量："+cons.size()+"\n");
            try{
                while(true){

                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String mess;// = br.readLine();
                    //保存信息
                    if((mess=br.readLine())!=null) {
                        if(mess.equals("-用户退出-")){
                            s.close();
                        }
                        System.out.print("客户端：" + mess + "\n");
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                        //为了证明是服务器返回的数据，我对mess修改在发送到客户端
                        //这里修改广播到所有客户端
                        for (Socket so : cons){
                            BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
                            String str = "服务器>>"+mess+"\n";
                            buffw.write(str);
                            buffw.flush();

                        }

                    }
                }
            }catch (IOException e){
                System.out.print("用户退出！\n");
                cons.remove(s);
                e.printStackTrace();
                this.interrupt();
                //e.printStackTrace();
            }catch (NullPointerException e) {
                System.out.print("NullPointerException");
            }finally {
                try {
                    s.close();
                }catch (IOException e){
                    System.out.print("IOException-2");
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(30001);
        while(true){
            Socket s = ss.accept();
            new Thread(new ServerThread(s)).start();
        }
    }
}
