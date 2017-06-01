package lee;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/1
 * Time: 15:41
 */
public class LoginRegistAction extends ActionSupport {
    private static final long serialVersionUID = -5423932901616743045L;
    private String username;
    private String password;
    private String tip;

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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    //    Action包含的注册逻辑
    public String regist() throws Exception {
        ActionContext.getContext().getSession().put("user", getUsername());
        setTip("恭喜您," + getUsername() + ",您已经注册成功!");
        return SUCCESS;
    }

    //    处理用户登录的登录逻辑
    public String login() throws Exception {
        if ("scott".equals(getUsername()) && "tiger".equals(getPassword())) {
            ActionContext.getContext().getSession().put("user", getUsername());
            setTip("欢迎," + getUsername() + ",您已经登陆成功!");
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
}
