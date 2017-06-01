package lee;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * Action直接访问ServletAPI
 * ServletContextAware:访问ServletContext实例
 * ServletRequestAware:访问HttpServletRequest实例
 * ServletResponseAware:访问HttpServletResponse实例
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/5/27
 * Time: 11:38
 */
public class LoginAction implements Action, ServletResponseAware {
    private String username;
    private String password;
    private String tip;
    //需要访问的HttpServletResponse对象
    //只是控制器.并不会对客户端造成输出
    private HttpServletResponse response;


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

    //    下面是处理用户请求的execute方法
    public String execute() throws Exception {
//        获取ActionContext实例,通过该实例访问Servlet API
        ActionContext ctx = ActionContext.getContext();
        //获取ServletContext里面的counter属性
        Integer counter = (Integer) ctx.getApplication().get("counter");
        //如果counter属性为null,设置该counter属性为1
        if (counter == null) {
            counter = 1;
//            否则将counter+1
        } else {
            counter++;
        }
//        将增加1后的counter值设置成counter属性,从applicationScope中访问
        ctx.getApplication().put("counter", counter);
//        将登陆用的username属性设置成一个HttpSession属性,从sessionScope中文芳
        ctx.getSession().put("user", getUsername());
//        如果用户名为scott,密码为tiger
        if ("scott".equals(getUsername()) && "tiger".equals(getPassword())) {
            //从requestScope中访问
            ctx.put("tip", "服务器提示:您已经成功登陆");
//            创建一个user对象
            Cookie c = new Cookie("user", getUsername());
//            设置Cookie对象的最大生存事件
            c.setMaxAge(60 * 60);
            //使用HttpServletResponse添加Cookie对象
            response.addCookie(c);
            return SUCCESS;
        } else {
            ctx.put("tip", "服务器提示:登录失败");
            return ERROR;
        }
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}












