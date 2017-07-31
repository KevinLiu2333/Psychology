package demo.sweetjava;

import org.junit.Test;
import org.nutz.lang.Lang;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/31 0031
 * Time: 16:19
 */
public class SweetDemo {
    class Person {
        private String name;
        private int age;
    }

    @Test
    public void test1() {
        Person persons[] = new Person[3];
        Person person = new Person();
        person.age = 25;
        person.name = "刘坤";
        persons[0] = person;
        persons[1] = person;
        persons[2] = person;

//        为数组赋值
//        把person对象的name字段的值作为key,创建一个map
//        有点捞啊,数组必须塞满,不然会报错
        HashMap personMap = Lang.array2map(HashMap.class, persons, "name");
//        数组to数组
        String[] names = (String[]) Lang.array2array(persons, String.class);
        System.out.println(names[1]);
    }

    @Test
    public void test2() {

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

    public void test9() {
    }
}
