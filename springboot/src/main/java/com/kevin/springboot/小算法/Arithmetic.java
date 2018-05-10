package com.kevin.springboot.小算法;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/30
 * Time: 17:00
 */
public class Arithmetic {

    /**
     * 1-n阶乘之和
     *
     * @param n n
     */
    public static void factorial(int n) {
//        总和
        double sum = 0;
//        阶乘值，初始化为1
        double factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = factorial * i;
            sum = (int) (sum + factorial);

        }
        System.out.println(sum);
    }

    /**
     * 求出二维数组每列的最小值
     *
     * @param arrays 二维数组
     */
    public static void minArray(int[][] arrays) {

        int maxColLength = arrays[0].length;
        int minArray[] = new int[maxColLength];
        for (int i = 0; i < maxColLength; i++) {
            int min = arrays[0][i];
            for (int j = 1; j < arrays.length; j++) {

//                if(arrays[j][i]<arrays[j-1][i]){
                //修改
                if (arrays[j][i] < min) {
                    min = arrays[j][i];
                }
            }
            minArray[i] = min;
            System.out.println(min);
        }

    }

    public static void main(String[] args) {
        int[][] arrays = {
                {23, 106, 8, 234},
                {25, 9, 73, 19},
                {56, 25, 67, 137}
        };
        minArray(arrays);
    }
}
