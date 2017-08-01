package demo.elengine;

import org.junit.Test;
import org.nutz.el.El;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/31 0031
 * Time: 15:02
 */
public class ElDemo {

    @Test
    public void test1() {
        System.out.println(El.eval("3+4*6").equals(33));
        /**
         * 解析成后缀表达式形式的一个队列
         * 将后缀表达式解析成一颗运算树
         * 对运算树根节点进行运算
         */
        Context context = Lang.context();
        context.set("a", 10);
        System.out.println(El.eval(context, "a*10"));
    }

    @Test
    public void test2() {
//        预编译变量
        El exp = new El("a*10");
        Context context = Lang.context();
        context.set("a", 10);
        System.out.println(exp.eval(context));
    }

    public void test3() {

    }

    public void test4() {
    }

    public void test5() {
    }

    public void test6() {
    }

    public void test7() {
    }

    public void test8() {
    }
}
