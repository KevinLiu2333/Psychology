package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/21
 * Time: 13:59
 */
public class CyclicBarrierTest {

    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 10;

    final CyclicBarrier barrier = new CyclicBarrier(STUDENT_COUNT, () -> System.out.println("人到齐了,开门..."));

    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "已刷卡,等待开门回家~");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + "放学回家");
    }

    /**
     * CyclicBarrier把所有的线程都阻塞在一个阀门位置，然后等到等待的线程数到达预设的值，就打开这个阀门。记得是阻塞线程，不是阻塞操作，在同一个线程使劲掉await是没什么效果的。
     * @param args
     */
    public static void main(String[] args) {
        final CyclicBarrierTest instance = new CyclicBarrierTest();
        /**
         * 每一个线程代表一个学生,到了十个学生后,barrier打开阀门
         */
        for (int i = 0; i < instance.STUDENT_COUNT; i++) {
            new Thread("学生" + i + " ") {
                public void run() {
                    try {
                        instance.goHome();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    }


}




