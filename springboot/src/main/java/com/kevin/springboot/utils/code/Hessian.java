package com.kevin.springboot.utils.code;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 14:00
 */
public class Hessian {

    @Test
    public void test1() throws IOException {
        Person zhangsan = new Person();
        zhangsan.setAge(17);
        zhangsan.setBirth(new Date());
        zhangsan.setName("张三");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //Hessian的序列化输出
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(zhangsan);
        byte[] zhangsanByte = os.toByteArray();
        ByteArrayInputStream is  = new ByteArrayInputStream(zhangsanByte);
        //Hessian的反序列化读取对象
        HessianInput hi = new HessianInput(is);
        Person person = (Person) hi.readObject();
        System.out.println(person.getAge());
    }
}
