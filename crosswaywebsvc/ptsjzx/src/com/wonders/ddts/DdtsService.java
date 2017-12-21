package com.wonders.ddts;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.dom4j.Element;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.tiles.authority.entity.Org;
import com.wonders.tiles.authority.entity.User;

/**
 * 推送操作.
 *
 */
@IocBean
public class DdtsService {

	@Inject
	private Dao dao;
	@Inject
	private GetDept getDept;
	/**
	 * 增加用户.
	 * 
	 * @param xml
	 * @return
	 */
	public String addUser(Element root) {
		User user = new User();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("UserUid".equals(e.getName())) {
					user.setLogonName(e.getText());
				}
				if ("OrgCode".equals(e.getName())) {
					user.setOrgCode(e.getText());
				}
				if ("UserEmail".equals(e.getName())) {
					user.seteMail(e.getText());
				}
				if ("UserFullName".equals(e.getName())) {
					user.setDisplayName(e.getText());
				}
				if ("UserPassword".equals(e.getName())) {
					user.setPassword(e.getText());
				}
				if("UserSequence".equals(e.getName())){
					user.setUserId(e.getText());
				}
			}
		}
		user.setState("0");
		user.setRoleId("0");
		user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.fastInsert(user);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<bua:Response xmlns:res=\"http://www.beyondbit.com/bua/sao/domains/response210001\" xmlns:bua=\"http://www.beyondbit.com/bua\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><bua:Head><bua:TrCode>210001</bua:TrCode><bua:ApplicationCode></bua:ApplicationCode><bua:UserUid></bua:UserUid><bua:ClientTxSeq></bua:ClientTxSeq><bua:ServerTxSeq></bua:ServerTxSeq><bua:RspCode>0000</bua:RspCode><bua:ErrMsg></bua:ErrMsg><bua:MacCode></bua:MacCode></bua:Head><bua:Body xsi:type=\"res:ResponseBody210001\"/></bua:Response>";
	}

	/**
	 * 修改用户.
	 * 
	 * @param xml
	 * @return
	 */
	public String updateUser(Element root) {
		User user = new User();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("UserUid".equals(e.getName())) {
					user.setLogonName(e.getText());
				}
				if ("OrgCode".equals(e.getName())) {
					user.setOrgCode(e.getText());
				}
				if ("UserEmail".equals(e.getName())) {
					user.seteMail(e.getText());
				}
				if ("UserFullName".equals(e.getName())) {
					user.setDisplayName(e.getText());
				}
				if ("UserPassword".equals(e.getName())) {
					user.setPassword(e.getText());
			}
		  }
		}
		User t = dao.fetch(User.class, Cnd.where("logonName", "=", user.getLogonName()));
		t.setOrgCode(user.getOrgCode());
		t.setUpdateDate(new Date());
		t.seteMail(user.geteMail());
		t.setPassword(user.getPassword());
		t.setDisplayName(user.getDisplayName());
		t.setDept(getDept.getDeptName(user.getOrgCode()));
		dao.update(t);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<bua:Response xmlns:res=\"http://www.beyondbit.com/bua/sao/domains/response210002\" xmlns:bua=\"http://www.beyondbit.com/bua\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><bua:Head><bua:TrCode>210002</bua:TrCode><bua:ApplicationCode></bua:ApplicationCode><bua:UserUid></bua:UserUid><bua:ClientTxSeq></bua:ClientTxSeq><bua:ServerTxSeq></bua:ServerTxSeq><bua:RspCode>0000</bua:RspCode><bua:ErrMsg></bua:ErrMsg><bua:MacCode></bua:MacCode></bua:Head><bua:Body xsi:type=\"res:ResponseBody210002\"/></bua:Response>";
	}

	/**
	 * 删除用户.
	 * 有問題
	 * @param xml
	 * @return
	 */
	public String removeUser(Element root) {
		User user =new User();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("UserUid".equals(e.getName())) {
					user.setLogonName(e.getText());
				}
			}
		}
		User t =dao.fetch(User.class,Cnd.where("logonName", "=", user.getLogonName()));
		dao.delete(t);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<bua:Response xmlns:res=\"http://www.beyondbit.com/bua/sao/domains/response210003\" xmlns:bua=\"http://www.beyondbit.com/bua\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><bua:Head><bua:TrCode>210003</bua:TrCode><bua:ApplicationCode></bua:ApplicationCode><bua:UserUid></bua:UserUid><bua:ClientTxSeq></bua:ClientTxSeq><bua:ServerTxSeq></bua:ServerTxSeq><bua:RspCode>0000</bua:RspCode><bua:ErrMsg></bua:ErrMsg><bua:MacCode></bua:MacCode></bua:Head><bua:Body xsi:type=\"res:ResponseBody210003\"/></bua:Response>";
	}

	/**
	 * 增加组织.
	 * 
	 * @param xml
	 * @return
	 */
	public String addOrg(Element root) {
		Org org = new Org();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("OrgCode".equals(e.getName())) {
					org.setOrgCode(e.getText());
				}
				if ("OrgName".equals(e.getName())) {
					org.setOrgName(e.getText());
				}
				if ("OrgSequence".equals(e.getName())) {
					org.setOrgSequence(e.getText());
				}
				if("ParentOrgCode".equals(e.getName())){
					org.setOrgParentId(e.getText());
				}
			}
		}
		org.setCreateTime(String.valueOf(new Date()));
		org.setStatu("1");
		org.setOrgId(UUID.randomUUID().toString().replaceAll("-", ""));
		dao.fastInsert(org);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<bua:Response xmlns:res=\"http://www.beyondbit.com/bua/sao/domains/response200001\" xmlns:bua=\"http://www.beyondbit.com/bua\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><bua:Head><bua:TrCode>200001</bua:TrCode><bua:ApplicationCode></bua:ApplicationCode><bua:UserUid></bua:UserUid><bua:ClientTxSeq></bua:ClientTxSeq><bua:ServerTxSeq></bua:ServerTxSeq><bua:RspCode>0000</bua:RspCode><bua:ErrMsg></bua:ErrMsg><bua:MacCode></bua:MacCode></bua:Head><bua:Body xsi:type=\"res:ResponseBody200001\"/></bua:Response>";
	}

	/**
	 * 更新组织.
	 * 
	 * @param xml
	 * @return
	 */
	public String updateOrg(Element root) {
		Org org = new Org();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("OrgCode".equals(e.getName())) {
					org.setOrgCode(e.getText());
				}
				if ("OrgName".equals(e.getName())) {
					org.setOrgName(e.getText());
				}
				if ("OrgSequence".equals(e.getName())) {
					org.setOrgSequence(e.getText());
				}
			}
		}
		Org t = dao.fetch(Org.class, org.getOrgCode());
		t.setOrgName(org.getOrgName());
		t.setOrgSequence(org.getOrgSequence());
		t.setLastUpdateTime(String.valueOf(new Date()));
		dao.update(t);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<bua:Response xmlns:res=\"http://www.beyondbit.com/bua/sao/domains/response200002\" xmlns:bua=\"http://www.beyondbit.com/bua\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><bua:Head><bua:TrCode>200002</bua:TrCode><bua:ApplicationCode></bua:ApplicationCode><bua:UserUid></bua:UserUid><bua:ClientTxSeq></bua:ClientTxSeq><bua:ServerTxSeq></bua:ServerTxSeq><bua:RspCode>0000</bua:RspCode><bua:ErrMsg></bua:ErrMsg><bua:MacCode></bua:MacCode></bua:Head><bua:Body xsi:type=\"res:ResponseBody200002\"/></bua:Response>";
	}

	/**
	 * 删除组织.
	 * 
	 * @param xml
	 * @return
	 */
	public String removeOrg(Element root) {
		Org org =new Org();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator j = element.elementIterator(); j.hasNext();) {
				Element e = (Element) j.next();
				if ("OrgCode".equals(e.getName())) {
					org.setOrgCode(e.getText());
					
				}
			}
		}
		Org t = dao.fetch(Org.class, org.getOrgCode());
		dao.delete(t);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<bua:Response xmlns:res=\"http://www.beyondbit.com/bua/sao/domains/response200003\" xmlns:bua=\"http://www.beyondbit.com/bua\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><bua:Head><bua:TrCode>200003</bua:TrCode><bua:ApplicationCode></bua:ApplicationCode><bua:UserUid></bua:UserUid><bua:ClientTxSeq></bua:ClientTxSeq><bua:ServerTxSeq></bua:ServerTxSeq><bua:RspCode>0000</bua:RspCode><bua:ErrMsg></bua:ErrMsg><bua:MacCode></bua:MacCode></bua:Head><bua:Body xsi:type=\"res:ResponseBody200003\"/></bua:Response>";
	}
}
