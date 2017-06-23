package JSONTest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserInfoTest {

    private static Logger log = Logger.getLogger(UserInfoTest.class);

    /**
     * @param @throws Exception
     * @return void
     * @throws
     * @Description: 修改昵称
     * @author LiuKun
     * @date 2016年9月7日
     */
    @Test
    public void modifyNickname() throws Exception {
        String url = TestUtils.DOMAIN + "/modifyNickname";
        NameValuePair username = new NameValuePair("username", "13585149563");
        NameValuePair type = new NameValuePair("type", "stu");
        NameValuePair ticket = new NameValuePair("ticket", "96B1751C721322F73FD6A54098D5D6FF");
        NameValuePair nickname = new NameValuePair("nickname", "刘坤");
        NameValuePair[] nvps = new NameValuePair[]{username, type, ticket, nickname};
        String response = TestUtils.getPostRequest(nvps, url, null);
        log.info(response);
    }

    /**
     * @param @throws Exception
     * @return void
     * @throws
     * @Description: 修改邮箱
     * @author LiuKun
     * @date 2016年9月7日
     */
    @Test
    public void modifyEmailAddress() throws Exception {
        String url = TestUtils.DOMAIN + "/modifyEmailAddress";
        NameValuePair username = new NameValuePair("username", "13585149563");
        NameValuePair type = new NameValuePair("type", "stu");
        NameValuePair ticket = new NameValuePair("ticket", "96B1751C721322F73FD6A54098D5D6FF");
        NameValuePair email = new NameValuePair("email", "liukun44919731992@163.com");
        NameValuePair[] nvps = new NameValuePair[]{username, type, ticket, email};
        String response = TestUtils.getPostRequest(nvps, url, null);
        log.info(response);
    }

    /**
     * @param @throws Exception
     * @return void
     * @throws
     * @Description: 上传作业
     * @author LiuKun
     * @date 2016年9月7日
     */
    @Test
    public void homeworkUpload() throws Exception {
        String url = TestUtils.DOMAIN + "/opern/upload";
        NameValuePair username = new NameValuePair("userId", "1");
        NameValuePair[] nvps = new NameValuePair[]{username};
        File file = new File("D:/aaa.png");
        Map<String, File> fileMap = new HashMap<>();
        fileMap.put("operns", file);
        String response = TestUtils.getPostRequest(nvps, url, fileMap);
        log.info(response);
    }


    /**
     * @param @throws Exception
     * @return void
     * @throws
     * @Description: 作业列表
     * @author LiuKun
     * @date 2016年9月7日
     */
    @Test
    public void homeworkList() throws Exception {
        String url = TestUtils.DOMAIN + "/homework/homeworkList";
        NameValuePair username = new NameValuePair("username", "13585149563");
        NameValuePair type = new NameValuePair("type", "stu");
        NameValuePair ticket = new NameValuePair("ticket", "96B1751C721322F73FD6A54098D5D6FF");
        NameValuePair status = new NameValuePair("status", "2");
        NameValuePair pageNum = new NameValuePair("pageNum", "1");
        NameValuePair[] nvps = new NameValuePair[]{username, type, ticket, status, pageNum};
        String response = TestUtils.getPostRequest(nvps, url, null);
        log.info(response);
    }


    /**
     * @param @throws Exception
     * @return void
     * @throws
     * @Description: 作业详情
     * @author LiuKun
     * @date 2016年9月7日
     */
    @Test
    public void homeworkDetail() throws Exception {
        String url = TestUtils.DOMAIN + "/homework/homeworkDetail";
        NameValuePair username = new NameValuePair("username", "13585149563");
        NameValuePair type = new NameValuePair("type", "stu");
        NameValuePair ticket = new NameValuePair("ticket", "96B1751C721322F73FD6A54098D5D6FF");
        NameValuePair homeworkId = new NameValuePair("homeworkId", "755");
        NameValuePair[] nvps = new NameValuePair[]{username, type, ticket, homeworkId};
        String response = TestUtils.getPostRequest(nvps, url, null);
        log.info(response);
    }


    /**
     * @param @throws Exception
     * @return void
     * @throws
     * @Description: 老师的所有作业
     * @author LiuKun
     * @date 2016年9月7日
     */
    @Test
    public void teacherHomework() throws Exception {
        String url = TestUtils.DOMAIN + "/homework/teacher/homeworkList";
        NameValuePair username = new NameValuePair("username", "13585149563");
        NameValuePair type = new NameValuePair("type", "tea");
        NameValuePair status = new NameValuePair("status", "2");
        NameValuePair ticket = new NameValuePair("ticket", "96B1751C721322F73FD6A54098D5D6FF");
        NameValuePair pageNum = new NameValuePair("pageNum", "1");
        NameValuePair[] nvps = new NameValuePair[]{username, type, ticket, status, pageNum};
        String response = TestUtils.getPostRequest(nvps, url, null);
        log.info(response);
    }

//    @Test
//    public void dataBase() {
//        Connection conn = null;
//        String sql;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("成功加载MySQL驱动程序");
//            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.158/KlswLiveDB?user=root&password=klsw5512&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
//            Statement stmt = conn.createStatement();
//            sql = "select * from tb_LiveUser";
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()){
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getString(3));
//                System.out.println(rs.getString(4));
//                System.out.println(rs.getString(5));
//                System.out.println(rs.getString(6));
//                System.out.println(rs.getString(7));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}








