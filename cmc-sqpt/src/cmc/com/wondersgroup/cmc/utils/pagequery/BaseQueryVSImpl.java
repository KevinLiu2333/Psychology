package com.wondersgroup.cmc.utils.pagequery;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.model.dto.QueryDTO;
import com.wondersgroup.wsscclib.pagequery.Sort;
import com.wondersgroup.wsscclib.pagequery.Sorts;
import com.wondersgroup.wssip.util.StringTools;

/**
 * 基本VS实现
 * @author jacky
 * @version $Revision$ 2013-12-12
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class BaseQueryVSImpl {
	
	private static Log log = LogFactory.getLog(BaseQueryVSImpl.class);
	
	private final static String TYPE_INT = "java.lang.Integer";
	private final static String TYPE_STRING = "java.lang.String";
	private final static String TYPE_LONG = "java.lang.Long";
	private final static String TYPE_DOUBLE = "java.lang.Double";
	
	@SuppressWarnings("rawtypes")
	protected void appendCondition(StringBuffer condition, List args, String sql, Object value, String replaceFlag){
		
		if("?".equals(replaceFlag)){
			appendCondition(condition, args, sql, value);
		}else{
			
			String type = value.getClass().getName();
			
			if(type.equals(TYPE_STRING)){
				String val = (String)value;
				val = filterForSQL(val, sql);   // 过滤非法字符，并且防止SQL注入
				if(StringUtils.isNotEmpty(val)){
					condition.append(JdbcDaoLogUtils.replaceStr(sql, replaceFlag, val));
				}
			}else if(type.equals(TYPE_INT)){
				int val = ((Integer)value).intValue();
				if( val >= 0){
					condition.append(JdbcDaoLogUtils.replaceStr(sql, replaceFlag, val + ""));
				}
			}else if(type.equals(TYPE_LONG)){
				long val = ((Long)value).longValue();
				if( val >= 0){
					condition.append(JdbcDaoLogUtils.replaceStr(sql, replaceFlag, val + ""));
				}
			}else if(type.equals(TYPE_DOUBLE)){
				double val = ((Double)value).doubleValue();
				if( val >= 0){
					condition.append(JdbcDaoLogUtils.replaceStr(sql, replaceFlag, val + ""));
				}
			}
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void appendCondition(StringBuffer condition, List args, String sql, Object value){
			
		String type = value.getClass().getName();
		
		if(type.equals(TYPE_STRING)){
			String val = (String)value;
			if(StringUtils.isNotEmpty(val)){
				condition.append(sql);
				val = filterForSQL(val, sql); // 防止非法字符
				args.add(val);
			}
		}else if(type.equals(TYPE_INT)){
			int val = ((Integer)value).intValue();
			if( val >= 0){
				condition.append(sql);
				args.add(val);
			}
		}else if(type.equals(TYPE_LONG)){
			long val = ((Long)value).longValue();
			if( val >= 0){
				condition.append(sql);
				args.add(val);
			}
		}else if(type.equals(TYPE_DOUBLE)){
			double val = ((Double)value).doubleValue();
			if( val >= 0){
				condition.append(sql);
				args.add(val);
			}
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	protected String formatWhere(StringBuffer condition, List args){
		int size = args.size();
		String where = condition.toString();
		if(size == 0 && "".equals(where)) {
			return "";
		}else{
			if(where.indexOf(" AND") == 0){
				where = " WHERE "  + where.substring(4, where.length());
			}else if(where.indexOf(" OR") ==0){
				where = " WHERE "  + where.substring(3, where.length());
			}
			return where;
		}
	}
	
	protected String formatOrderBy(String orderBy, PageQueryDTO dto){
		
		String order = "";
		if(StringUtils.isNotEmpty(dto.getSortField())){
			order = " ORDER BY " + dto.getSortField() + " " + dto.getSortOrder();
		}else{
			if(StringUtils.isNotEmpty(orderBy)){
				order = " ORDER BY " + orderBy;
			}
		}
		return order;
	}
	
	/**
	 * @param orderBy
	 * @param sort
	 * @return
	 */
	protected String formatOrderBy(String orderBy, Sorts sorts) {
		String sortsInfo ="";
		String order ="";
		for(Sort sort :sorts){
			
		   String name=	sort.getName();
		   String px;
		   if( sort.isAsc()){
			    px ="  asc";
		   }else{
			   px ="  desc ";
		   };
		   
		   sortsInfo=sortsInfo+" "+name+px;
		  
		}
		 if(StringTools.hasText(sortsInfo)){
			 order ="order by "	+sortsInfo;
		}
		 return order;
	}
	//*****************************************//
	
	//防止非法字符,比如单引号
	
	@SuppressWarnings("unused")
	private static String filterForSQL(String _sContent){
		return filterForSQL(_sContent, "");
	}
	
	private static String filterForSQL(String _sContent, String _sContentname)
    {
        if(_sContent == null)
            return "";
        int nLen = _sContent.trim().length();
        if(nLen == 0)
            return "";
        char srcBuff[] = _sContent.trim().toCharArray();
        StringBuffer retBuff = new StringBuffer((int)((double)nLen * 1.5D));
        for(int i = 0; i < nLen; i++)
        {
            char cTemp = srcBuff[i];
            switch(cTemp)
            {
            case 39: // '\''
                retBuff.append("''");
                break;
            default:
                retBuff.append(cTemp);
                break;
            }
        }

        return avoidSQLInject(retBuff.toString(), _sContentname);
    }
	
	//防止SQL注入
	@SuppressWarnings("unused")
	private static String avoidSQLInject(String _content){
		return  avoidSQLInject(_content, "");
	}
	
	private static String avoidSQLInject(String _content, String _contentname){
		if(_content == null) 
			return "";
		
		String[] dangerWords = {"AND ","OR ","SELECT "};
		
		for(int i=0; i<dangerWords.length; i++){
			String dword = dangerWords[i];
			if(_content.toUpperCase().indexOf(dword) >=0) {
				JdbcDaoLogUtils.doLog(log, "★★★ SQL注入攻击★★★    name=["+ _contentname + "], content=[" +  _content + "]");
				return "***";
			}
			else
				continue;
		}
		return _content;
	}
	
	//***************************************//
	
	protected String formatOrderBy(String orderBy, QueryDTO dto) {

		String order = "";
		if (StringUtils.isNotEmpty(dto.getSortField())) {
		    order = " ORDER BY " + dto.getSortField() + " "
			    + dto.getSortOrder();
		} else {
		    if (StringUtils.isNotEmpty(orderBy)) {
			order = " ORDER BY " + orderBy;
		    }
		}
		return order;
	    }
	
}
