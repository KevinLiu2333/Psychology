package com.wonders.ws.receive.task;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.log.entity.InterfaceReceiveLog;
import com.wonders.ws.receive.bean.PtQfbWangtingBean;
import com.wonders.ws.receive.getMethod.wangting.XmlServiceProxy;

@IocBean
public class QfbWangtingTask {

	@Inject
	private Dao	dao;

	public void extract() {
		// 区府办网上大厅信息
		getQfbWangting();
	}

	@SuppressWarnings("rawtypes")
	public void getQfbWangting() {
		XmlServiceProxy service = new XmlServiceProxy();
		String json = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String date1 = df.format(cal.getTime()) + " 20:00";
			String date2 = df.format(new Date()) + " 20:00";
			json = service.getXmlList(date1, date2);
			// json = service.getXmlList("2016-11-01 20:00", "2016-12-06 20:00");
			String res1 = json.replace("[", "");
			String res2 = res1.replace("]", "");
			String[] list = res2.split(",");
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInputNum(list.length);
			log.setInterfaceName("getXml");
			log.setServiceName("获取网上大厅信息");
			log.setReceiveName("网上大厅信息");
			log.setDeptName("区府办");
			log.setDeptId("DT41");
			log.setCallNum(1);
			log.setTableName("PT_QFB_WANGTING");
			for (int i = 0; i < list.length; i++) {
				String result = service.getXml(list[i].trim());
				if (!Strings.isEmpty(result)) {
					Document document1 = DocumentHelper.parseText(result);
					Element root1 = document1.getRootElement();
					Iterator iter1 = root1.elementIterator("apply");
					while (iter1.hasNext()) {
						Element ele = (Element) iter1.next();
						PtQfbWangtingBean wt = new PtQfbWangtingBean();
						wt.setId(ele.elementText("id"));
						wt.setSuid(ele.elementText("suid"));
						wt.setDid(ele.elementText("did"));
						wt.setAffairname(ele.elementText("affair_name"));
						wt.setMaindepartment(ele.elementText("main_department"));
						wt.setRegion(ele.elementText("region"));
						wt.setType(ele.elementText("type"));
						wt.setApplyname(ele.elementText("apply_name"));
						wt.setApplicanttype(ele.elementText("applicant_type"));
						wt.setApplicant(ele.elementText("applicant"));
						wt.setCertificatetype(ele.elementText("certificate_type"));
						wt.setCertificateno(ele.elementText("certificate_no"));
						wt.setContactname(ele.elementText("contact_name"));
						wt.setContactaddress(ele.elementText("contact_address"));
						wt.setContactpostcode(ele.elementText("contact_postcode"));
						wt.setContacttel(ele.elementText("contact_tel"));
						wt.setContactmobile(ele.elementText("contact_mobile"));
						wt.setContactfax(ele.elementText("contact_fax"));
						wt.setContactemail(ele.elementText("contact_email"));
						wt.setToll(ele.elementText("toll"));
						wt.setResult(ele.elementText("result"));
						wt.setResultview(ele.elementText("result_view"));
						wt.setApplymemo(ele.elementText("apply_memo"));
						wt.setApplycommitmentdays(ele.elementText("apply_commitment_days"));
						wt.setApplycommitmenttype(ele.elementText("apply_commitment_type"));
						wt.setApplycommitmenttime(ele.elementText("apply_commitment_time"));
						wt.setApplytime(ele.elementText("apply_time"));
						wt.setAccepttime(ele.elementText("accept_time"));
						wt.setFinishtime(ele.elementText("finish_time"));
						wt.setTransactdays(ele.elementText("transact_days"));
						wt.setIsovertime(ele.elementText("is_overtime"));
						wt.setIspublic(ele.elementText("is_public"));
						wt.setSubmittype(ele.elementText("submit_type"));
						dao.insert(wt);
					}
				}
			}
			log.setEndDate(date.format(new Date()));
			if (!(list.length == 1)) {
				dao.insert(log);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
