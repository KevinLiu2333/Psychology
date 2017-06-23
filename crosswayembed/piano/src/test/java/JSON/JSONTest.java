//package JSON;
//
//import net.sf.json.JSONObject;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.json.JSONArray;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by liukun on 2017/3/23.
// * JSONTest
// */
//public class JSONTest {
//
//    @Test
//    public void test1() {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("type", "dsada");
//        JSONArray jsonArray = new JSONArray();
//        Map<String, String> map = new HashMap<>();
//        map.put("dasd", "dad");
//        map.put("dd", "dad");
//        map.put("das", "dad");
//        map.put("dasd", "dad");
//        jsonArray.put(map);
//        jsonObject.put("data", jsonArray.toString());
//        System.out.println("{\"errmsg\":\"序列号不能为空\",\"errmsg\":\"SN is null\",\"username\":\"\"}");
//
//    }
//
//    @Test
//    public void test2() throws Exception {
//        String uri = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + "w7xWHzfOSIBJcvwzWRYb8ZpXPOfJruUXJDK587q674gvQPG7ie5Fnomn91UU_WjWypx3kQ-0a8YnjKRq-rvZ_fm4HM5uVTLE0aTiQjLqtog" + "&openid=" + "oNCOqwRIeeMF0ckqxCBA-MyvGr9g" + "&lang=zh_CN";
//        HttpClient client = new HttpClient();
//        GetMethod method = new GetMethod(uri);
//        client.executeMethod(method);
//        JSONObject jsonObject = JSONObject.fromObject(method.getResponseBodyAsString());
//        System.out.println();
//    }
//
//    public Integer getInteger() {
//        Integer a = 10;
//        return a;
//    }
//}
//
//
//
