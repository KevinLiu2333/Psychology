/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.builtin.BigDecimalBridge;
import com.wondersgroup.sh.search.bridge.builtin.BigIntegerBridge;
import com.wondersgroup.sh.search.bridge.builtin.BlobBridge;
import com.wondersgroup.sh.search.bridge.builtin.BooleanBridge;
import com.wondersgroup.sh.search.bridge.builtin.ClassBridge;
import com.wondersgroup.sh.search.bridge.builtin.ClobBridge;
import com.wondersgroup.sh.search.bridge.builtin.DateBridge;
import com.wondersgroup.sh.search.bridge.builtin.DoubleBridge;
import com.wondersgroup.sh.search.bridge.builtin.FloatBridge;
import com.wondersgroup.sh.search.bridge.builtin.IntegerBridge;
import com.wondersgroup.sh.search.bridge.builtin.LongBridge;
import com.wondersgroup.sh.search.bridge.builtin.ShortBridge;
import com.wondersgroup.sh.search.bridge.builtin.StringBridge;
import com.wondersgroup.sh.search.bridge.builtin.UriBridge;
import com.wondersgroup.sh.search.bridge.builtin.UrlBridge;
import com.wondersgroup.sh.search.enums.ResolutionEnum;
import com.wondersgroup.sh.search.enums.TypeEnum;
import com.wondersgroup.sh.search.lucene.config.FieldBridgeInfo;
import com.wondersgroup.sh.search.lucene.config.FieldInfo;
import com.wondersgroup.sh.search.util.ReflectHelper;

/**
 * This factory is responsible for creating and initializing build-in and custom <i>FieldBridges</i>.
 *
 */
public class BridgeFactory {
	private static Map builtInBridges = new HashMap();
	private static Map buildInBridgesByField = new HashMap();

	private BridgeFactory() {
	}

	public static final TwoWayFieldBridge DOUBLE = new TwoWayString2FieldBridgeAdaptor( new DoubleBridge() );

	public static final TwoWayFieldBridge FLOAT = new TwoWayString2FieldBridgeAdaptor( new FloatBridge() );

	public static final TwoWayFieldBridge SHORT = new TwoWayString2FieldBridgeAdaptor( new ShortBridge() );

	public static final TwoWayFieldBridge INTEGER = new TwoWayString2FieldBridgeAdaptor( new IntegerBridge() );

	public static final TwoWayFieldBridge LONG = new TwoWayString2FieldBridgeAdaptor( new LongBridge() );

	public static final TwoWayFieldBridge BIG_INTEGER = new TwoWayString2FieldBridgeAdaptor( new BigIntegerBridge() );

	public static final TwoWayFieldBridge BIG_DECIMAL = new TwoWayString2FieldBridgeAdaptor( new BigDecimalBridge() );

	public static final TwoWayFieldBridge STRING = new TwoWayString2FieldBridgeAdaptor( new StringBridge() );

	public static final TwoWayFieldBridge BOOLEAN = new TwoWayString2FieldBridgeAdaptor( new BooleanBridge() );

	public static final TwoWayFieldBridge CLAZZ = new TwoWayString2FieldBridgeAdaptor( new ClassBridge() );

	public static final TwoWayFieldBridge Url = new TwoWayString2FieldBridgeAdaptor( new UrlBridge() );

	public static final TwoWayFieldBridge Uri = new TwoWayString2FieldBridgeAdaptor( new UriBridge() );
	
	public static final FieldBridge CLOB = new String2FieldBridgeAdaptor(new ClobBridge());

	public static final TwoWayFieldBridge DATE_YEAR = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_YEAR );
	public static final TwoWayFieldBridge DATE_MONTH = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_MONTH );
	public static final TwoWayFieldBridge DATE_DAY = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_DAY );
	public static final TwoWayFieldBridge DATE_HOUR = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_HOUR );
	public static final TwoWayFieldBridge DATE_MINUTE = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_MINUTE );
	public static final TwoWayFieldBridge DATE_SECOND = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_SECOND );
	public static final TwoWayFieldBridge DATE_MILLISECOND = new TwoWayString2FieldBridgeAdaptor( DateBridge.DATE_MILLISECOND );

	static {
		builtInBridges.put( Double.class.getName(), DOUBLE );
		builtInBridges.put( double.class.getName(), DOUBLE );
		builtInBridges.put( Float.class.getName(), FLOAT );
		builtInBridges.put( float.class.getName(), FLOAT );
		builtInBridges.put( Short.class.getName(), SHORT );
		builtInBridges.put( short.class.getName(), SHORT );
		builtInBridges.put( Integer.class.getName(), INTEGER );
		builtInBridges.put( int.class.getName(), INTEGER );
		builtInBridges.put( Long.class.getName(), LONG );
		builtInBridges.put( long.class.getName(), LONG );
		builtInBridges.put( BigInteger.class.getName(), BIG_INTEGER );
		builtInBridges.put( BigDecimal.class.getName(), BIG_DECIMAL );
		builtInBridges.put( String.class.getName(), STRING );
		builtInBridges.put( Boolean.class.getName(), BOOLEAN );
		builtInBridges.put( boolean.class.getName(), BOOLEAN );
		builtInBridges.put( Class.class.getName(), CLAZZ );
		builtInBridges.put( URL.class.getName(), Url );
		builtInBridges.put( URI.class.getName(), Uri );
		builtInBridges.put( Date.class.getName(), DATE_MILLISECOND );
		
		buildInBridgesByField.put(TypeEnum.STRING, STRING);
		buildInBridgesByField.put(TypeEnum.DOUBLE, DOUBLE);
		buildInBridgesByField.put(TypeEnum.INT, INTEGER);
		buildInBridgesByField.put(TypeEnum.LONG, LONG);
		buildInBridgesByField.put(TypeEnum.FLOAT, FLOAT);
		buildInBridgesByField.put(TypeEnum.DATE, DATE_MILLISECOND);
	}

	public static FieldBridge getBridgeByClass(Class clazz, FieldInfo fieldInfo, Map parameters) {
		if( Date.class.isAssignableFrom(clazz) ) {	// include java.sql.Date, Time, Timestamp
			if( StringUtils.isNotBlank(fieldInfo.getResolution()) ) {
				ResolutionEnum resolution = ResolutionEnum.getEnum(fieldInfo.getResolution());
				return getDateField(resolution);
			}
			else {
				return DATE_SECOND;
			}
		}
		else if( Clob.class.isAssignableFrom(clazz) ) {
			return CLOB;
		}
		else if( Blob.class.isAssignableFrom(clazz) ) {	// BlobBridge is ParameterizedBridge, so create an instance everytime
			BlobBridge blobBridge = new BlobBridge();
			blobBridge.setParameterValues(parameters);
			return new String2FieldBridgeAdaptor(blobBridge);
		}
		else {
			return (FieldBridge)builtInBridges.get(clazz.getName());
		}
	}
	
	public static TwoWayFieldBridge getBridgeByField(FieldInfo fieldInfo) {
		if( TypeEnum.DATE.equals(fieldInfo.getType()) ) {
			if( StringUtils.isNotBlank(fieldInfo.getResolution()) ) {
				ResolutionEnum resolution = ResolutionEnum.getEnum(fieldInfo.getResolution());
				return getDateField(resolution);
			}
			else {
				return DATE_SECOND;
			}
		}
		else {
			return (TwoWayFieldBridge)buildInBridgesByField.get(fieldInfo.getType());
		}
	}
	
	public static FieldBridge getCustomFieldBridge(FieldBridgeInfo bridgeInfo) {
		try {
			com.wondersgroup.sh.search.bridge.StringBridge bridge = (com.wondersgroup.sh.search.bridge.StringBridge)ReflectHelper.classForName(bridgeInfo.getClassName()).newInstance();
			if( bridge instanceof ParameterizedBridge ) {
				((ParameterizedBridge)bridge).setParameterValues(bridgeInfo.getParameters());
			}
			return new String2FieldBridgeAdaptor(bridge);
		}
		catch( Exception ex ) {
			throw new SearchException( "不能够实例化" + bridgeInfo.getClassName() + "为FieldBridge。", ex );
		}
	}
	
	/**
	 * Takes in a fieldBridge and will return you a TwoWayFieldBridge instance.
	 *
	 * @param fieldBridge
	 *
	 * @return a TwoWayFieldBridge instance if the Field Bridge is an instance of a TwoWayFieldBridge.
	 *
	 * @throws SearchException if the FieldBridge passed in is not an instance of a TwoWayFieldBridge.
	 */

	public static TwoWayFieldBridge extractTwoWayType(FieldBridge fieldBridge) {
		if ( fieldBridge instanceof TwoWayFieldBridge ) {
			return ( TwoWayFieldBridge ) fieldBridge;
		}
		else {
			throw new SearchException( "FieldBridge passed in is not an instance of " + TwoWayFieldBridge.class.getName() );
		}
	}
	
	public static TwoWayFieldBridge getDateField(ResolutionEnum resolution) {
		if( ResolutionEnum.YEAR.equals(resolution) ) 
			return DATE_YEAR;
		else if( ResolutionEnum.MONTH.equals(resolution) )
			return DATE_MONTH;
		else if( ResolutionEnum.DAY.equals(resolution) )
			return DATE_DAY;
		else if( ResolutionEnum.HOUR.equals(resolution) )
			return DATE_HOUR;
		else if( ResolutionEnum.MINUTE.equals(resolution) )
			return DATE_MINUTE;
		else if( ResolutionEnum.SECOND.equals(resolution) )
			return DATE_SECOND;
		else if( ResolutionEnum.MILLISECOND.equals(resolution))
			return DATE_MILLISECOND;
		else
			throw new IllegalArgumentException( "Unknown Resolution: " + resolution );
	}
}
