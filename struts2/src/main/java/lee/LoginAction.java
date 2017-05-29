package lee;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/5/27
 * Time: 11:38
 */
public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 2376746218112233583L;
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

    @Override
    public String execute() throws Exception {
        //当用户参数符合条件时,返回success
        //否则返回error
        if ("scott".equals(getUsername()) && "tiger".equals(getPassword())) {
            //通过ActionContext对象访问Web应用的Session
            ActionContext.getContext().getSession().put("user", getUsername());
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    /**
     * validate方法会在执行系统的execute方法之前进行
     */
    public void validate() {
        //如果用户名为空字符串
        if (getUsername() == null || getUsername().trim().equals("")) {
//            添加表单校验错误
//            addFieldError("username", "user.required");
//            国际化输出
            addFieldError("username", getText("user.required"));
        }
//        当密码为空时,或者密码为空字符串,添加表单校验错误
        if (getPassword() == null || getPassword().trim().equals("")) {
//            addFieldError("password", "pass.required");
//            国际化输出
            addFieldError("password", getText("user.required"));
        }
    }
}


