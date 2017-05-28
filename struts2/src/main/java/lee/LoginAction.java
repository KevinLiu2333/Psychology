package lee;

import com.opensymphony.xwork2.ActionContext;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/5/27
 * Time: 11:38
 */
public class LoginAction implements Action {
    private String username;
    private String password;

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

    public String execute() throws Exception {
        //当用户参数符合条件时,返回success
        //否则返回error
        if ("scott".equals(getUsername()) && "tiger".equals(getPassword())){
            //通过ActionContext对象访问Web应用的Session
            ActionContext.getContext().getSession().put("user", getUsername());
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
}
