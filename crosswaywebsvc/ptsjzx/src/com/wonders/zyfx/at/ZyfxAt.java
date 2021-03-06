package com.wonders.zyfx.at;

import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/18
 * Time: 10:22
 */
@At("/zyfx")
@IocBean
public class ZyfxAt {


    @At
    @Ok("jsp:jsp.wbj.index")
    public Map<String, Object> toIndex(String id, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
        Map<String, Object> result = new HashMap<String, Object>();
        SAXReader reader = new SAXReader();
        String web_inf = session.getServletContext().getRealPath("/WEB-INF/");
        Document doc;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(web_inf + "/fw.xml"), "utf-8");
            doc = reader.read(isr);
            Document document = DocumentHelper.createDocument();
            Element root = DocumentHelper.createElement("dl");
            document.setRootElement(root);
//            自然人专题
            root.add((Node) doc.selectSingleNode("//dt[@id='menu2']").clone());
            root.add((Node) doc.selectSingleNode("//dd[@id='dd2']").clone());
//            法人专题
            root.add((Node) doc.selectSingleNode("//dt[@id='menu3']").clone());
            root.add((Node) doc.selectSingleNode("//dd[@id='dd3']").clone());
//            产业园区专题
            root.add((Node) doc.selectSingleNode("//dt[@id='menu4']").clone());
            root.add((Node) doc.selectSingleNode("//dd[@id='dd4']").clone());
            StringWriter writer = new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlwriter = new XMLWriter(writer, format);
            xmlwriter.write(root);
            result.put("menu", writer.toString());
        } catch (DocumentException | IOException e1) {
            e1.printStackTrace();
        }
        result.put("displayName", user.getDisplayName());
        result.put("id", id);
        return result;
    }

}
