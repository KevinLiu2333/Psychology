package lee;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/5/27
 * Time: 11:38
 */
public interface Action {
    String SUCCESS = "success";
    String NONE = "none";
    String ERROR = "error";
    String INPUT = "input";
    String LOGIN = "login";

    //    定义处理用户请求的execute抽象方法
    String execute() throws Exception;
}
