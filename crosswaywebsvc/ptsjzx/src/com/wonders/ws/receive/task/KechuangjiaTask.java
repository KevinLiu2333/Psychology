package com.wonders.ws.receive.task;

import com.wonders.ws.bean.CorpInfoBean;
import com.wonders.ws.receive.bean.PtKwKechuangCorpinfoBean;
import com.wonders.ws.receive.bean.PtKwKechuangDisanfangBean;
import com.wonders.ws.receive.bean.PtKwKechuangZaitiBean;
import org.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.http.Header;
import org.nutz.http.Request;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/1/16
 * Time: 14:40
 */
@IocBean
public class KechuangjiaTask {

    @Inject
    private Dao dao;

    public void extract() {
        getKechuangjiaInfo();
    }

    public void getKechuangjiaInfo() {
//        Pager pager = new Pager();
//        pager.setPageNumber(1);
//        pager.setPageSize(100);
//        List<CorpInfoBean> corpInfoBeans = dao.query(CorpInfoBean.class, cri, pager);
//        for (CorpInfoBean bean : corpInfoBeans) {
//            JSONObject params = new JSONObject();
//            String uniscid = bean.getUniscid();
//            String organcode = bean.getOrgancode();
//            //构造请求参数参数
//            params.put("key", "1f6855e928034bd4aa62aee1069617cf");
//            params.put("method", "getCorpInfo");
//            if (uniscid != null) {
//                params.put("Uniscid", bean.getUniscid().trim());
//            }
//            if (organcode != null) {
//                params.put("Organcode", organcode);
//            }
//
//            Response response = post("http://192.168.7.6/webservice.php", params.toString(), null, 5 * 1000);
//            JSONObject json = new JSONObject(response.getContent());
//            //查有信息
//            if (0 == json.getInt("errcode")) {
//                int type = json.getInt("company_type");
//                switch (type) {
//                    case 1:
//                        saveCorpInfo(json, bean);
//                        break;
//                    case 2:
//                        saveZaiti(json, bean);
//                        break;
//                    case 3:
//                        saveDisanfang(json, bean);
//                        break;
//                    default:
//                        break;
//
//                }
//            }
//        }
    }

    private void saveCorpInfo(JSONObject json, CorpInfoBean bean) {
        PtKwKechuangCorpinfoBean corpinfoBean = new PtKwKechuangCorpinfoBean();
        corpinfoBean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        corpinfoBean.setCompanytype(json.getString("company_type"));
        corpinfoBean.setCompanyscope(json.getString("company_scope"));
        corpinfoBean.setContactname(json.getString("contact_name"));
        corpinfoBean.setContacttel(json.getString("contact_tel"));
        corpinfoBean.setCompanyachievement(json.getString("company_achievement"));
        corpinfoBean.setSelfInsertDate(new Date());
        corpinfoBean.setOrganCode(bean.getOrgancode());
        corpinfoBean.setUniscid(bean.getUniscid());
        dao.insert(corpinfoBean);

    }

    private void saveZaiti(JSONObject json, CorpInfoBean bean) {
        PtKwKechuangZaitiBean zt = new PtKwKechuangZaitiBean();
        zt.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        zt.setCompanytype(json.getString("company_type"));
        zt.setCompanyscope(json.getString("company_scope"));
        zt.setContactname(json.getString("contact_name"));
        zt.setContacttel(json.getString("contact_tel"));
        zt.setCompanycategory(json.getString("company_category"));
        zt.setCompanylon(json.getString("company_lon"));
        zt.setCompanylat(json.getString("company_lat"));
        zt.setCompanyurl(json.getString("company_url"));
        zt.setCompanycode(json.getString("company_code"));
        zt.setCompanylet(json.getString("company_let"));
        zt.setSelfInsertDate(new Date());
        zt.setOrganCode(bean.getOrgancode());
        zt.setUniscid(bean.getUniscid());
        dao.insert(zt);

    }

    private void saveDisanfang(JSONObject json, CorpInfoBean bean) {
        PtKwKechuangDisanfangBean dsf = new PtKwKechuangDisanfangBean();
        dsf.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        dsf.setCompanytype(json.getString("company_type"));
        dsf.setCompanyscope(json.getString("company_scope"));
        dsf.setContactname(json.getString("contact_name"));
        dsf.setContacttel(json.getString("contact_tel"));
        dsf.setServicercode(json.getString("servicer_code"));
        dsf.setServicercoupon(json.getString("servicer_coupon"));
        dsf.setServicerscope(json.getString("servicer_scope"));
        dsf.setServicertype(json.getString("servicer_type"));
        dsf.setServicerurl(json.getString("servicer_site"));
        dsf.setSelfInsertDate(new Date());
        dsf.setOrganCode(bean.getOrgancode());
        dsf.setUniscid(bean.getUniscid());
        dao.insert(dsf);

    }

    public static Response post(String url, Object body, Header header, int timeout) {
        Request req = Request.create(url, Request.METHOD.POST).setHeader(header);
        if (body != null) {
            req.setData(String.valueOf(body));
        }
        return Sender.create(req).setTimeout(timeout).send();
    }
}




