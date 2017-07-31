package com.wondersgroup.cmc.dispatch.m420101.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.m420101.model.M420101VO;
import com.wondersgroup.cmc.dispatch.m420101.service.M420101VS;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondef;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondefhis;
import com.wondersgroup.cmc.dispatch.model.bo.Ifsubmon;
import com.wondersgroup.cmc.dispatch.model.dto.IfdefineDTO;
import com.wondersgroup.cmc.dispatch.model.dto.IfmondefDTO;
import com.wondersgroup.cmc.dispatch.model.dto.IfmondetailDTO;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.utils.NumberUtils;
import com.wondersgroup.cmc.utils.UserContextUtils;
import com.wondersgroup.cmc.utils.pagequery.BaseQueryVSImpl;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoLogUtils;
import com.wondersgroup.cmc.utils.pagequery.JdbcDaoUtils;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.util.BeanTools;
import com.wondersgroup.wssip.util.DateTools;
import com.wondersgroup.wssip.util.StringTools;

public class M420101VSImpl extends BaseQueryVSImpl implements M420101VS {
	private static Log log = LogFactory.getLog(M420101VSImpl.class);
	
    /**
     * 新增监控定义配置
     */
    @Override
    public void insert(M420101VO vo) {
	    checkData(vo, "insert");
		Ifmondef formBean =new Ifmondef();
		BeanTools.copyProperties(vo, formBean);	
		//保存进Ifmondef表
		formBean.setEdtime(new Date());
		formBean.setUsrname(UserContextUtils.getOperatorName());
		formBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
		formBean.setValid(DispatchContent.VALID);//有效标志为1
		CommonHibernateDaoUtils.save(formBean);
		//保存进Ifmondefhis表
		Ifmondefhis formBeanHis = new Ifmondefhis();
		BeanTools.copyProperties(formBean, formBeanHis);
		CommonHibernateDaoUtils.save(formBeanHis);
		//保存进Ifsubmon表
		Ifdefine ifdefine = CommonHibernateDaoUtils.load(Ifdefine.class, formBean.getIfdefineid());
		if("04".equals(ifdefine.getIftype())){
			Ifsubmon ifsubmon = new Ifsubmon();
			ifsubmon.setIfmondefid(formBean.getIfmondefid());
			CommonHibernateDaoUtils.save(ifsubmon);
		}
    }
    
    /**
     * 校验入参完整性
     * @param vo
     * @param type
     */
    private void checkData(M420101VO vo,String type) {
    	if(type=="insert"){
        	if (NumberUtils.isNotEmpty(vo.getIfmondefid())) {
        	    throw new BusinessException("新增时ID必须为空，请确认");
        	}
    	}
	}

    /**
     * 删除监控定义配置
     */
    @Override
    public void delete(M420101VO vo) {
    	if (StringTools.isEmpty(Long.toString(vo.getIfmondefid()))) {
    	    throw new BusinessException("删除时ID不能为空，请确认");
    	}
		//更新IFDEFINE表，valid置为0
    	Ifmondef updateBean = CommonHibernateDaoUtils.load(Ifmondef.class, Long.valueOf(vo.getIfmondefid()));
    	updateBean.setValid(DispatchContent.UNVALID);
    	updateBean.setEdtime(new Date());
    	updateBean.setUsrname(UserContextUtils.getOperatorName());
    	updateBean.setUsrid(Long.parseLong(UserContextUtils.getOperatorId()));
    	CommonHibernateDaoUtils.update(updateBean);
		//保存进IFDEFINEHIS表
    	Ifmondefhis formBeanHis = new Ifmondefhis();
		BeanTools.copyProperties(updateBean, formBeanHis);
    	CommonHibernateDaoUtils.save(formBeanHis);
    }



    /**
     * 分页查询监控
     */
    @SuppressWarnings("rawtypes")
    @Override
    public DataGrid query(PageQueryDTO dto) {
		JdbcDaoLogUtils.doLog(log, "[ifdefineQuery]QueryDTO = [" +  dto.toString() + "]");
		
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT a.*,b.iftype,b.ifdefcode,b.ifdefname FROM IFMONDEF a, IFDEFINE b ");
		// (1.1) 附加默认排序
		String orderBy = " b.ifdefcode ";
		
		// (2) new 查询语句where条件部分和? 参数部分
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();
		
		// (3) 添加条件判断
		String query = dto.getParamAsString("queryText");
		if(StringTools.hasText(query)){
			query = "%"+query+"%";
			appendCondition(condition, args, " AND (b.IFDEFCODE LIKE ? ", query);
			appendCondition(condition, args, " OR b.IFDEFNAME LIKE ? ) ", query);
		}
		
		String iftype = dto.getParamAsString("iftype");
		if(StringTools.hasText(iftype)){
			iftype = "%"+iftype+"%";
			appendCondition(condition, args, " AND b.iftype LIKE ? ", iftype);
		}
		appendCondition(condition, args, " AND a.VALID = ? ", dto.getParamAsString("valid"));
		
		condition.append(" AND a.ifdefineid = b.ifdefineid ");
		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);
		
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where + orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), IfmondefDTO.class);
		return dataGrid;
    }

    /**
     * 根据选择接口大类查询对应接口
     */
    public List<IfdefineDTO> getIfDefine(PageQueryDTO dto) {
		JdbcDaoLogUtils.doLog(log, "[ifdefineQuery]QueryDTO = [" +  dto.toString() + "]");
		
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT * FROM IFDEFINE a ");
		// (1.1) 附加默认排序
		String orderBy = " a.ifdefcode ";
		
		// (2) new 查询语句where条件部分和? 参数部分
		StringBuffer condition = new StringBuffer("");
		@SuppressWarnings("rawtypes")
		List args = new ArrayList();
		// (3) 添加条件判断
		appendCondition(condition, args, " AND a.iftype = ? ", dto.getParamAsString("iftype"));
		appendCondition(condition, args, " AND a.VALID = ? ", dto.getParamAsString("valid"));
		if(StringTools.isEmpty(dto.getParamAsString("allabove"))) {
			condition.append(" AND a.ifdefineid not in (select b.ifdefineid from IFMONDEF b where b.valid='1') ");
		}
		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);
		
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where + orderby;
		Object[] params = args.toArray();
		List<IfdefineDTO> IfdefineList = JdbcDaoUtils.query(sql, IfdefineDTO.class, params);
		return IfdefineList;
    }

    
    
    /**
     * 查询监控明细
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryMonDetail(PageQueryDTO dto) throws ParseException {
		JdbcDaoLogUtils.doLog(log, "[ifdefineQuery]QueryDTO = [" +  dto.toString() + "]");
		
		// (1) 查询语句 from部分
		String fromSQL = new String("SELECT a.*,b.iftype,b.ifdefcode,b.ifdefname FROM IFMONDETAIL a, IFDEFINE b ");
		// (1.1) 附加默认排序
		String orderBy = " a.lsmontime desc ";
		
		// (2) new 查询语句where条件部分和? 参数部分
		StringBuffer condition = new StringBuffer("");
		List args = new ArrayList();

		// (3) 添加条件判断
		String startDate = dto.getParamAsString("startDate");
		String endDate = dto.getParamAsString("endDate");
		String lsmonstatus = dto.getParamAsString("lsmonstatus");
		if(StringTools.hasText(startDate)&&StringTools.hasText(endDate)){
			startDate = startDate.replace("T", " ");
			endDate = endDate.replace("T", " ");
			condition.append(" AND a.lsmontime >= ? ");
			args.add(DateTools.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
			condition.append(" AND a.lsmontime <= ? ");
			args.add(DateTools.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringTools.hasText(lsmonstatus)){
			appendCondition(condition, args, " AND a.lsmonstatus = ? ", lsmonstatus);
		}
		appendCondition(condition, args, " AND a.ifmondefid = ? ", dto.getParamAsString("ifmondefid"));
		
		condition.append(" AND a.ifdefineid = b.ifdefineid ");

		// (4) 处理完成where和orderby部分
		String where = formatWhere(condition, args);
		String orderby = formatOrderBy(orderBy, dto);
		
		// (5)处理完成的SQL语句 , 带?和参数
		String sql = fromSQL + where + orderby;
		Object[] params = args.toArray();
		DataGrid dataGrid = JdbcDaoUtils.queryForDataGrid(sql, params, dto.getPageIndex(), dto.getPageSize(), IfmondetailDTO.class);
		return dataGrid;
	}
	

}
