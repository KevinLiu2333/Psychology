package com.wonders.ddts;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.wonders.tiles.tools.IocSingle;

/**
 * 单点推送webService.
 *
 */
public class Ddts {

	public String sync(String xml) throws Exception {
		System.out.println(xml);
		SAXReader reader = new SAXReader();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		Ioc ioc = IocSingle.getInstance();
		DdtsService ds = ioc.get(DdtsService.class);
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("TrCode".equals(e.getName()) && "210001".equals(e.getText())) {
					String str = ds.addUser(root);
					return str;
				}
				if ("TrCode".equals(e.getName()) && "210002".equals(e.getText())) {
					String str = ds.updateUser(root);
					return str;
				}
				if ("TrCode".equals(e.getName()) && "210003".equals(e.getText())) {
					String str = ds.removeUser(root);
					return str;
				}
				if ("TrCode".equals(e.getName()) && "200001".equals(e.getText())) {
					String str = ds.addOrg(root);
					return str;
				}
				if ("TrCode".equals(e.getName()) && "200002".equals(e.getText())) {
					String str = ds.updateOrg(root);
					return str;
				}
				if ("TrCode".equals(e.getName()) && "200003".equals(e.getText())) {
					String str = ds.removeOrg(root);
					return str;
				}
			}
		}
		return null;
	}

}
