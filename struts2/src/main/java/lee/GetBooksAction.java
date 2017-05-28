package lee;

import com.opensymphony.xwork2.ActionContext;
import service.BookService;

/**
 *  * Created with IntelliJ IDEA.
 *  * User: Kevin
 *  * Date: 2017/5/28
 *  * Time: 7:05
 *  
 */
public class GetBooksAction implements Action {

    //    该属性并不封装用户的请求参数,而用于封装Action需要输出到JSP页面的信息
    private String[] books;
//    books属性的setter方法

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public String execute() throws Exception {
//        获取Session中的user属性
        String user = (String) ActionContext.getContext().getSession().get("user");
        //如果属性不为空,且该属性值为scott
        if (user != null && user.equals("scott")) {
//            创建BookService实例
            BookService bs = new BookService();
//            将业务逻辑组件返回值设置成该Action的属性
            setBooks(bs.getBooks());
            return SUCCESS;
        } else {
            return LOGIN;
        }
    }
}





















