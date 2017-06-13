package lee;

/**
 * 封装请求参数和处理结果的javaBean
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/3
 * Time: 15:16
 */
public class UserBean {
    //封装请求参数的username和password属性
    private String username;
    private String password;
    private String tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}










