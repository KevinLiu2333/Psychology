package demo.httpclient;

import org.junit.Test;
import org.nutz.Nutz;
import org.nutz.http.*;
import org.nutz.http.sender.FilePostSender;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/8/1 0001
 * Time: 8:45
 */
public class Demo {
    @Test
    public void test() {
        Response response = Http.get("http://nutz.cn/");
        assert response != null;
        System.out.println(response.getDetail());
        System.out.println(response.getContent());
        System.out.println(response.getEncodeType());
        System.out.println(response.getHeader());
        System.out.println(response.getProtocal());
        System.out.println(response.getStatus());
    }

    @Test
    public void post() {
        Map<String, Object> params = new HashMap<>();
        params.put("version", "NutzTest");
        params.put("website", Nutz.version());
        Response response = Http.post2("http://nutztest.herokuapp.com/",
                params,
                5 * 1000); // 可以同时设置超时时间
        System.out.println(response.getStatus());
        System.out.println(response.getProtocal());
        System.out.println(response.getHeader());
        System.out.println(response.getEncodeType());
        System.out.println(response.getContent());
        System.out.println(response.getDetail());
    }

    @Test
    public void fileUpload() throws Exception {
        Request req = Request.create("aabbccdd" + "/upload/image", Request.METHOD.POST);
        File f = File.createTempFile("nutz", "data");
        FileWriter fw = new FileWriter(f);
        fw.write("abc");
        fw.flush();
        fw.close();
        req.getParams().put("file", f);
        FilePostSender sender = new FilePostSender(req);
        Response resp = sender.send();
        assertEquals("image&3", resp.getContent());
    }

    @Test
    public void test3() {
//        设置http代理
        Http.setHttpProxy("adashkf", 8080);
//        代理回落,代理服务器不可连接时,尝试直连
        Http.setAutoSwitch(true);
    }

    @Test
    public void test4() {
//        关闭jvm证书检查(Nutz全局)
        Http.disableJvmHttpsCheck();
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=2015-01-12&leftTicketDTO.from_station=UXP&leftTicketDTO.to_station=SJP&purpose_codes=ADULT";
        Http.get(url);
    }

    @Test
    public void test5() {
//        初始化线程池,只需要执行一次
        Sender.setup(null);
        Request request = Request.create("https://www.baidu.com", Request.METHOD.GET);
//        传入回调,可以是null,如果你不在意结果
        Sender.create(request).send(obj -> {
        });
//        程序结束前,shutdown
        Sender.shutdown();
    }

    @Test
    public void test6() {
        Request request = Request.create("https://nutz.cn", Request.METHOD.GET);
        Cookie cookie = new Cookie();
        cookie.set("sid", UUID.randomUUID().toString());
        Sender sender = Sender.create(request);
        sender.setInterceptor(cookie);
        sender.send();
    }

    public void test7() {
    }

    public void test8() {
    }
}
