package lee;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 对于采用模型驱动的Action来言,该Action必须实现
 * ModelDriven接口,下面是本应用所使用的Action的代码
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/3
 * Time: 15:19
 */
public class LoginAction implements Action,ModelDriven<UserBean>{
    //定义封装请求参数和处理结果的类型
    private UserBean model = new UserBean();

    public UserBean getModel() {
        return model;
    }
    //处理用户请求的execute方法
    public String execute() throws Exception {
        //如果username请求参数为scott,password请求参数为tiger
        if(getModel().getUsername().equals("scott")&&getModel().getPassword().equals("tiger")){
            getModel().setTip("哈哈,服务器提示");
            return SUCCESS;
        }else {
            return ERROR;
        }
    }
}
