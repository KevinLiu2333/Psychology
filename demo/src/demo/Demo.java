package demo;

/**
 * Created by Kevin on 2016/12/1.
 */
public class Demo {
    private final static int end = Integer.MAX_VALUE;
    private final static int start = Integer.MAX_VALUE
            - 100;

    public static void main(String[] args) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            count++;
            System.out.println(i);
        }
        System.out.println("我是count---->" + count);
    }


}
