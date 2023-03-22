package com.example.gxw.indoorlocation;

/**
 */

public class PathPoint {           //使用此类完成点到坐标的对应

    static public int Xcoord;
    static public int Ycoord;


//            0    2 1           点对应相关坐标
//            1    6 1
//            2    9 1
//            3    2 5
//            4    6 5
//            5    9 5
//            6    2 9
//            7    6 9
//            8    9 9

    static public void getCoord(int i){
        if(i==0)
        {
            Xcoord=2;
            Ycoord=2;
        }
        if(i==1)
        {
            Xcoord=4;
            Ycoord=2;
        }
        if(i==2)
        {
            Xcoord=7;
            Ycoord=2;
        }
        if(i==3)
        {
            Xcoord=2;
            Ycoord=6;
        }
        if(i==4)
        {
            Xcoord=4;
            Ycoord=6;
        }
        if(i==5)
        {
            Xcoord=7;
            Ycoord=6;
        }
        if(i==6)
        {
            Xcoord=2;
            Ycoord=10;
        }
        if(i==7)
        {
            Xcoord=4;
            Ycoord=10;
        }
        if(i==8)
        {
            Xcoord=7;
            Ycoord=10;
        }
    }
}
