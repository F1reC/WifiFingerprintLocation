package cn.shoppingguide.controller;

import java.util.ArrayList;
import java.util.List;
//后端寻路算法findpath
public class FindPath {

/*  0----1---2
	     |
	     |
	     |
	     |
	3----4---5
	     |
	     |
	     |
	     |
	6----7---8
	     */
//Floyd算法实现每对顶点的最短路径
	/*
	 求最短路径算法的思想

	 */
    private static int[][] D;//D代表顶点与顶点的最短路径权值和的矩阵
    private static int [][] P;//P代表对应顶点的最短路径的前驱矩阵
    private static int numOfNodes;//节点个数
    private static int inf=65535;//相当于2的16次方减1
    static List<Integer> list = new ArrayList<Integer>();//使用链表存储路径


    public static void init(){
        D = new int[][]{
                { 0, 4, inf, inf, inf, inf, inf, inf, inf},
                { 4, 0, 3, inf, 4, inf, inf, inf, inf},
                { inf, 3, 0, inf, inf, inf, inf, inf, inf},
                { inf, inf, inf, 0, 4, inf, inf, inf, inf},
                { inf, 4, inf, 4, 0, 3, inf, 4, inf},
                { inf, inf, inf, inf, 3, 0, inf, inf, inf},
                { inf, inf, inf, inf, inf, inf, 0, 4, inf},
                { inf, inf, inf, inf, 4, inf, 4, 0, 3},
                { inf, inf, inf, inf, inf, inf, inf, 3, 0}
        };
        numOfNodes = D[0].length;

        P = new int[numOfNodes][numOfNodes];
        for(int i = 0; i < numOfNodes; i++){
            for(int j = 0; j< numOfNodes; j++){
                P[i][j] = j;
            }
        }
    }

    public static void floydMethod(){

        for(int i = 0; i < numOfNodes; i++){
            for(int j = 0; j < numOfNodes; j++) {
                for(int k = 0; k < numOfNodes; k++){
                    if(D[j][k] > D[j][i] + D[i][k]){
                        D[j][k] = D[j][i] + D[i][k];
                        P[j][k] = P[j][i];
                    }
                }
            }
        }
    }

    public static void printP(){
        for(int i = 0; i < numOfNodes; i++){
            for(int j = 0; j < numOfNodes; j++){
                System.out.print(P[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void printResult(int i, int j) {
        System.out.print(i + "->" + j + " weight: " + D[i][j] + " Path: " + i);
        int k = P[i][j];
        list.add(i);
        while (k != j) {
            System.out.print("->" + k + " ");
            list.add(k);
            k = P[k][j];
        }
        list.add(j);
        System.out.println(list);
        System.out.print("->" + j + "\n");
    }

    public static void main(String[] args){

        init();
        floydMethod();
        getList(1,2);
        System.out.println(getList(1,2));
    }
    public static ArrayList<Integer> getList(Integer X,Integer Y)
    {
        init();
        floydMethod();
        printResult(X,Y);
        System.out.println(list);
        return (ArrayList<Integer>) list;
    }


}
