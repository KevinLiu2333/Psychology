/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge.builtin;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.DateTools;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.ParameterizedBridge;
import com.wondersgroup.sh.search.bridge.TwoWayStringBridge;
import com.wondersgroup.sh.search.enums.ResolutionEnum;


/**
 * Bridge a java.util.Date to a String, truncated to the resolution
 * Date are stored GMT based
 * <p/>
 * ie
 * Resolution.YEAR: yyyy
 * Resolution.MONTH: yyyyMM
 * Resolution.DAY: yyyyMMdd
 * Resolution.HOUR: yyyyMMddHH
 * Resolution.MINUTE: yyyyMMddHHmm
 * Resolution.SECOND: yyyyMMddHHmmss
 * Resolution.MILLISECOND: yyyyMMddHHmmssSSS
 *
 */
//TODO split into StringBridge and TwoWayStringBridge?
public class DateBridge implements TwoWayStringBridge, ParameterizedBridge {

	public static final TwoWayStringBridge DATE_YEAR = new DateBridge( ResolutionEnum.YEAR );
	public static final TwoWayStringBridge DATE_MONTH = new DateBridge( ResolutionEnum.MONTH );
	public static final TwoWayStringBridge DATE_DAY = new DateBridge( ResolutionEnum.DAY );
	public static final TwoWayStringBridge DATE_HOUR = new DateBridge( ResolutionEnum.HOUR );
	public static final TwoWayStringBridge DATE_MINUTE = new DateBridge( ResolutionEnum.MINUTE );
	public static final TwoWayStringBridge DATE_SECOND = new DateBridge( ResolutionEnum.SECOND );
	public static final TwoWayStringBridge DATE_MILLISECOND = new DateBridge( ResolutionEnum.MILLISECOND );

	private DateTools.Resolution resolution;

	public DateBridge() {
	}

	public DateBridge(ResolutionEnum resolution) {
		setResolution( resolution );
	}

	public Object stringToObject(String stringValue) {
		if ( StringUtils.isEmpty( stringValue ) ) return null;
		try {
			return DateTools.stringToDate( stringValue );
		}
		catch (ParseException e) {
			throw new SearchException( "Unable to parse into date: " + stringValue, e );
		}
	}

	public String objectToString(Object object) {
		return object != null ?
				DateTools.dateToString( (Date) object, resolution ) :
				null;
	}

	public void setParameterValues(Map parameters) {
		Object resolution = parameters.get( "resolution" );
		ResolutionEnum wdResolution;
		if ( resolution instanceof String ) {
			wdResolution = ResolutionEnum.getEnum( ((String) resolution).toUpperCase( Locale.ENGLISH ) );
		}
		else {
			wdResolution = (ResolutionEnum) resolution;
		}
		setResolution( wdResolution );
	}

	private void setResolution(ResolutionEnum resolution) {
		if( ResolutionEnum.YEAR.equals(resolution) ) 
			this.resolution = DateTools.Resolution.YEAR;
		else if( ResolutionEnum.MONTH.equals(resolution) )
			this.resolution = DateTools.Resolution.MONTH;
		else if( ResolutionEnum.DAY.equals(resolution) )
			this.resolution = DateTools.Resolution.DAY;
		else if( ResolutionEnum.HOUR.equals(resolution) )
			this.resolution = DateTools.Resolution.HOUR;
		else if( ResolutionEnum.MINUTE.equals(resolution) )
			this.resolution = DateTools.Resolution.MINUTE;
		else if( ResolutionEnum.SECOND.equals(resolution) )
			this.resolution = DateTools.Resolution.SECOND;
		else if( ResolutionEnum.MILLISECOND.equals(resolution) )
			this.resolution = DateTools.Resolution.MILLISECOND;
		else
			throw new SearchException( "Unknown Resolution: " + resolution );
	}
}
