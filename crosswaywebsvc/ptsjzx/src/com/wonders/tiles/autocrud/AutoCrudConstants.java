package com.wonders.tiles.autocrud;

public class AutoCrudConstants {
    /** 查询字段前缀 */
    public static final String QUERY_COLUMN = "Q_";
    public static final String QUERY_AND = "AND ";
    public static final String QUERY_PAGE = "PROWNUM";// 分页增加字段

    /** 字段类型 */
    public static final int COLUMN_TYPE_CHAR = 1;// 字符型

    public static final int COLUMN_TYPE_NUMBER = 2;// 数字型

    public static final int COLUMN_TYPE_DATE = 3;// 时间型

    public static final int COLUMN_TYPE_DIC = 4;// 字典型

    /** 时间字段类型-前缀 */
    public static final String DATE_START = "_S";// 开始时间

    public static final String DATE_END = "_E";// 截至时间

    /** 是否 */
    public static final String FLAG_YES = "1";

    public static final String FLAG_NO = "0";
    
    /** 有效性 */
    public static final String VALID_VALIDITY = "1"; //有效
    
    public static final String VALID_INVALIDITY = "0"; //无效
    
    /** 每页默认记录数*/
    public static final int DEFAULT_PAGE_SIZE = 10; 
    
    /** SYS_APP_COLUMNS中tableId的字段colID*/
    public static final String TABLE_ID_COL = "1001001";
    
    /** SYS_APP_COLUMNS中nameLetter字段的colID*/
    public static final String TABLE_NAMELETTER_COL = "1001005";
}
