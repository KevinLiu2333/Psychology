package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/21
 * Time: 14:11
 */
public class CyclicBarrierTest2 {

    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 12;
    /**
     * 循环阻拦,满足需求线程个数后放行(复用性)
     */
    final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("有3个学生到齐了,放行");
    });

    public void goHome() throws BrokenBarrierException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + "已刷卡,等待开门回家");
        barrier.await();
    }

    public static void main(String[] args) {
        final CyclicBarrierTest2 instnce = new CyclicBarrierTest2();
        /**
         * 每个线程代表一个学生
         */
        for (int i = 0; i < instnce.STUDENT_COUNT; i++) {
            new Thread("学生" + i + " ") {
                public void run() {
                    try {
                        instnce.goHome();
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }
}
