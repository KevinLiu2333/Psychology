package service;

/**
 *  * Created with IntelliJ IDEA.
 *  * User: Kevin
 *  * Date: 2017/5/28
 *  * Time: 7:02
 *  
 */
public class BookService {
    //    模拟数据库
    private String[] books = new String[]{
            "Spring2.0宝典",
            "轻量级J2EE企业应用实战",
            "基于J2EE的Ajax宝典",
            "Struts,Spring,Hibernate整合开发"
    };

    public String[] getBooks() {
        return books;
    }
}

