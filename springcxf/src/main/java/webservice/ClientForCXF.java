package webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/28 0028
 * Time: 18:37
 */
public class ClientForCXF {
    public static MyWebService getInterFace() {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(MyWebService.class);
        factoryBean.setAddress("http://localhost:8080/server/web-publish");
        return (MyWebService) factoryBean.create();
    }

    public static void main(String[] args) {
        MyWebService myWebService = getInterFace();
        System.out.println("client: " + myWebService.add(1, 3));
    }

}
