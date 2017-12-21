package com.wonders.wbj.at;

import com.wonders.qxkz.QXConstants;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.util.PropertyUtils;
import com.wonders.wbj.entity.DataFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@At("/wbj")
@IocBean
public class WbjAt {

    @Inject
    private Dao dao;

    @At
    @Ok("jsp:jsp.wbj.people")
    public void toPeople() {

    }

    @At
    @Ok("jsp:jsp.wbj.crop_info")
    public void toCropInfo() {

    }

    @At
    @Ok("jsp:jsp.wbj.credit")
    public void toCredit() {

    }

    @At
    @Ok("jsp:jsp.wbj.index")
    public Map<String, Object> toIndex(String id, HttpServletRequest req) throws IOException, FileNotFoundException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
        Map<String, Object> result = new HashMap<String, Object>();
        SAXReader reader = new SAXReader();
        String web_inf = session.getServletContext().getRealPath("/WEB-INF/");
        Document doc;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(web_inf + "/fw.xml"), "utf-8");
            doc = reader.read(isr);
            Element root = doc.getRootElement();
            if (!user.getRoleId().contains(QXConstants.TB_USER)) {
                root.remove(doc.selectSingleNode("//dt[@id='menu1']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd1']"));
            }
            if (!user.getRoleId().contains(QXConstants.KEY_USER)) {
                root.remove(doc.selectSingleNode("//a[@id='loc4']"));
            }
            if (!user.getRoleId().contains(QXConstants.ADMIN)) {
//			root.remove(doc.selectSingleNode("//a[@id='loc7']"));
                root.remove(doc.selectSingleNode("//dt[@id='menu5']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd5']"));
                root.remove(doc.selectSingleNode("//dt[@id='menu13']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd13']"));
                root.remove(doc.selectSingleNode("//dt[@id='menu2']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd2']"));
                root.remove(doc.selectSingleNode("//dt[@id='menu16']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd16']"));
                root.remove(doc.selectSingleNode("//dt[@id='menu10']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd10']"));
                Node node = doc.selectSingleNode("//a[@id='loc64']");
                node.getParent().remove(node);
                //海豚信用
                Node node1 = doc.selectSingleNode("//a[@id='loc102']");
                node1.getParent().remove(node1);
                //海豚信用统计
                Node node2 = doc.selectSingleNode("//a[@id='loc103']");
                node2.getParent().remove(node2);

                //资源目录检索申请
                Node node3 = doc.selectSingleNode("//a[@id='loc60']");
                node3.getParent().remove(node3);
                Node node4 = doc.selectSingleNode("//a[@id='loc61']");
                node4.getParent().remove(node4);

            }
            if (!user.getRoleId().contains(QXConstants.JK_USER)) {
                root.remove(doc.selectSingleNode("//dt[@id='menu6']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd6']"));
            }

            if (!user.getRoleId().contains(QXConstants.ONLY_USER)) {
                //root.remove(doc.selectSingleNode("//dt[@id='menu4']"));
                //root.remove(doc.selectSingleNode("//dd[@id='dd4']"));
            }
            if (!user.getRoleId().contains(QXConstants.SGS_USER)) {
                root.remove(doc.selectSingleNode("//dt[@id='menu100']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd100']"));
            }

            // 资源编目权限
            if (!user.getRoleId().contains(QXConstants.ZYBM_USER) && !user.getRoleId().contains(QXConstants.ADMIN)) {
                Node node = doc.selectSingleNode("//a[@id='loc58']");
                node.getParent().remove(node);
            }

            // 资源审核发布权限
            if (!user.getRoleId().contains(QXConstants.ZYFB_USER) && !user.getRoleId().contains(QXConstants.ADMIN)) {
                Node node = doc.selectSingleNode("//a[@id='loc59']");
                node.getParent().remove(node);
            }
            if (!user.getRoleId().contains(QXConstants.ZYBM_USER) && !user.getRoleId().contains(QXConstants.ZYFB_USER) &&
                    !user.getRoleId().contains(QXConstants.ADMIN)) {
                root.remove(doc.selectSingleNode("//dt[@id='menu17']"));
                root.remove(doc.selectSingleNode("//dd[@id='dd17']"));
            }
            if (!"1".equals(user.getType()) && !"2".equals(user.getType())) {
                Node node = doc.selectSingleNode("//a[@id='loc62']");
                node.getParent().remove(node);
            }

            root.remove(doc.selectSingleNode("//dt[@id='menu7']"));
            root.remove(doc.selectSingleNode("//dd[@id='dd7']"));
            root.remove(doc.selectSingleNode("//dt[@id='menu8']"));
            root.remove(doc.selectSingleNode("//dd[@id='dd8']"));
            root.remove(doc.selectSingleNode("//dt[@id='menu9']"));
            root.remove(doc.selectSingleNode("//dd[@id='dd9']"));
            root.remove(doc.selectSingleNode("//dt[@id='menu14']"));
            root.remove(doc.selectSingleNode("//dd[@id='dd14']"));
            StringWriter writer = new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlwriter = new XMLWriter(writer, format);
            xmlwriter.write(root);
            result.put("menu", writer.toString());
        } catch (DocumentException | UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        result.put("displayName", user.getDisplayName());
        result.put("id", id);
        return result;
    }

    @At
    @Ok("jsp:jsp.wbj.file_list")
    public Map<String, Object> tofilelist() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<DataFile> list = dao.query(DataFile.class, null);
        result.put("list", list);
        return result;
    }

    @At
    public void downloadfile(String id, HttpServletResponse response) {
        try {
            DataFile dataFile = dao.fetch(DataFile.class, Cnd.where("ID", "=", id));
            File file = new File(getFilePath() + "/"
                    + PropertyUtils.getProperty("datafilepath") + "/"
                    + dataFile.getFilelocal());
            InputStream inStream = new FileInputStream(file);
            OutputStream outStream = response.getOutputStream();
            response.reset();
            String filename = dataFile.getFilename();
            response.addHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(filename, "utf-8"));
            int tempbyte;
            while ((tempbyte = inStream.read()) != -1) {
                outStream.write(tempbyte);
                outStream.flush();
            }
            outStream.close();
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFilePath() {
        String path = PropertyUtils.getProperty("app.path");
        return path;
    }
}