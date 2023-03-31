package cn.shoppingguide.KNN;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//
@WebServlet(urlPatterns="/KNKManger")
public class KNNManger extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    Float Xcoord;
    Float Ycoord;
    Float AP1;
    Float AP2;
    Float AP3;
    Float AP4;
    Float AP5;
    String url = "jdbc:mysql://localhost:3307/rssi1";//服务器地址
    String username="root";
    String password="jj18435";
    Connection con;
    Statement stat;
    List<KNNData> kd;


    public KNNManger() throws ClassNotFoundException, SQLException {
        super();
        // TODO Auto-generated constructor stub
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection(url, username, password);
        stat=(Statement) con.createStatement();

        String sql="select * from information";
        ResultSet rs=stat.executeQuery(sql);

        System.out.println(rs);
        System.out.println("定位功能启动");

        kd = new ArrayList<KNNData>();
        while(rs.next())
        {

            Xcoord=Float.valueOf(rs.getString(1));
            Ycoord=Float.valueOf(rs.getString(2));
            AP1=Float.valueOf(rs.getString(3));
            AP2=Float.valueOf(rs.getString(4));
            AP3=Float.valueOf(rs.getString(5));
            AP4=Float.valueOf(rs.getString(6));
            AP5=Float.valueOf(rs.getString(7));
            kd.add(new KNNData(Xcoord,Ycoord,AP1,AP2,AP3,AP4,AP5));
        }

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().println("这是定位功能");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        br.close();
        try {
            JSONObject jsonObject=new JSONObject(buffer.toString());
            System.out.println(jsonObject);
            AP1=Float.valueOf(jsonObject.getString("AP1"));
            AP2=Float.valueOf(jsonObject.getString("AP2"));
            AP3=Float.valueOf(jsonObject.getString("AP3"));
            AP4=Float.valueOf(jsonObject.getString("AP4"));
            AP5=Float.valueOf(jsonObject.getString("AP5"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        KNNData locat= KNN.knnCal(1, new KNNData(0,0,AP1,AP2,AP3,AP4,AP5), kd);

        System.out.println(locat.x);
        System.out.println(locat.y);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("X",locat.x);
            jsonObject.put("Y",locat.y);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.reset();
        ServletOutputStream out = response.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(jsonObject.toString());
        System.out.println(jsonObject.toString());
        bw.flush();
        out.close();
        bw.close();
    }

}
