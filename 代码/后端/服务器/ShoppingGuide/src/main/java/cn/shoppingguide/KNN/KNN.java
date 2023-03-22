package cn.shoppingguide.KNN;

import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class KNN {

    private static double disCal(KNNData i, KNNData td) {
        double cost = -1,numerator = 0,dinominator;//分子与分母声明
        List<Double> numer,dinomin;
        numer = new ArrayList<>();//分母记录列表
        dinomin = new ArrayList<>();
        int same = 0;
        numer.add((double)(-1*i.AP1));
        numer.add((double)(-1*i.AP2));
        numer.add((double)(-1*i.AP3));
        numer.add((double)(-1*i.AP4));
        numer.add((double)(-1*i.AP5));
        numerator += (double)(-1*i.AP1)* td.AP1;//调整正负使其处在同一坐标范围
        dinomin.add((double)td.AP1);
        numerator += (double)(-1*i.AP2)* td.AP2;//调整正负使其处在同一坐标范围
        dinomin.add((double)td.AP2);
        numerator += (double)(-1*i.AP3)* td.AP3;//调整正负使其处在同一坐标范围
        dinomin.add((double)td.AP3);
        numerator += (double)(-1*i.AP4)* td.AP4;//调整正负使其处在同一坐标范围
        dinomin.add((double)td.AP4);
        numerator += (double)(-1*i.AP5)* td.AP5;//调整正负使其处在同一坐标范围
        dinomin.add((double)td.AP5);
        dinominator = calMod(numer)*calMod(dinomin); // 计算分母
        cost = numerator/dinominator; // 计算相似性
        return cost;
    }
    public static KNNData knnCal(int k, KNNData i, List<KNNData> ts) {
        for (KNNData td : ts) {
            td.distance = disCal(i, td);
        }
        Collections.sort(ts);
        findPosByKNN(k, i, ts);
        return i;
    }

    private static double calMod(List<Double> numbers){ // 分母部分的计算公式
        double res = 0;
        Iterator<Double> iterator = numbers.iterator();
        while(iterator.hasNext()){ // 通过循环计算
            double num = iterator.next();
            res += num*num;
        }
        res = Math.sqrt(res);
        return res;
    }

    private static KNNData findPosByKNN(int K, KNNData position,List<KNNData> ts){ // 获取位置估计值
        int x = 0,y = 0;
        double totalCost = 0;
        int same = 0;
        for (int i = 0; i < K; i++) {
            KNNData td = ts.get(i);
            totalCost += td.distance+1;
        }
        for (int i = 0; i < K; i++) { // 通过权值计算均值
            KNNData pos = ts.get(i);
            x += (int) ((double)pos.x* (pos.distance+1)/totalCost);
            y += (int) ((double)pos.y* (pos.distance+1)/totalCost);
        }
        position.x = x;
        position.y = y;
        System.err.println(x+" "+y+"coor");

        return position;
    }
}
