package com.wonders.fr.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.wonders.fr.ImportXml2Table;
import com.wonders.fr.bean.PunishNoteBean;
import com.wonders.fr.bean.PunishNoteCorpBean;
import com.wonders.fr.bean.PunishNoteInfoBean;

public class FrService {

	Ioc				ioc		= null;
	Dao				dao		= null;

	private Logger	logger	= Logger.getLogger(ImportXml2Table.class);

	public void getDao() {
		try {
			if (ioc == null) {
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.wonders"));
			}
			if (dao == null) {
				dao = ioc.get(Dao.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public PunishNoteBean getPunishNote(int punish_enty_id) {
		Criteria cri = Cnd.cri();
		cri.where().and("punish_enty_id", "=", punish_enty_id);
		List<PunishNoteBean> pnbList = dao.query(PunishNoteBean.class, cri);

		return pnbList.get(0);
	}

	public PunishNoteCorpBean getPunishNoteCorp(int punish_enty_id) {
		Criteria cri = Cnd.cri();
		cri.where().and("punish_enty_id", "=", punish_enty_id);
		List<PunishNoteCorpBean> pnbList = dao.query(PunishNoteCorpBean.class, cri);

		return pnbList.get(0);
	}

	public void updatePunishNote(PunishNoteBean ab) {
		dao.update(ab);
	}

	public void insertPunishNote(PunishNoteBean ab) {
		dao.insert(ab);

	}

	public PunishNoteInfoBean getPunishNoteInfo(PunishNoteInfoBean ab) {
		Criteria cri = Cnd.cri();
		cri.where().and("punish_enty_id", "=", ab.getPunish_enty_id());
		cri.where().and("punish_info_id", "=", ab.getPunish_info_id());

		List<PunishNoteInfoBean> pnbList = dao.query(PunishNoteInfoBean.class, cri);

		return pnbList.get(0);
	}

	public void updatePunishNoteInfo(PunishNoteInfoBean ab) {
		dao.update(ab);
	}

	public void insertPunishNoteINfo(PunishNoteInfoBean ab) {
		dao.insert(ab);
	}

	public PunishNoteCorpBean getPunishNoteCorpInfo(PunishNoteCorpBean ab) {
		Criteria cri = Cnd.cri();
		cri.where().and("punish_enty_id", "=", ab.getPunish_enty_id());
		cri.where().and("punish_corp_id", "=", ab.getPunish_corp_id());

		List<PunishNoteCorpBean> pnbList = dao.query(PunishNoteCorpBean.class, cri);

		return pnbList.get(0);
	}

	public void updatePunishNoteCorp(PunishNoteCorpBean ab) {
		dao.update(ab);
	}

	public void insertPunishNoteCorp(PunishNoteCorpBean ab) {
		dao.insert(ab);
	}
}
