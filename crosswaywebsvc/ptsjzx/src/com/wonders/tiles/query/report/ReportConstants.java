package com.wonders.tiles.query.report;

public class ReportConstants {

    /** 数据分隔符 */
    public static final String DATA_SEPARATOR = "#";

    /** 行分组 */
    public static final int ROW_HEAD = 1;

    /** 列分组 */
    public static final int COLUMN_HEAD = 2;

    /** XML-交叉部分命名 */
    public static final String XML_CROSS_NAME = "//group_report/@cross_name";

    /** XML-行分组 */
    public static final String XML_ROW_GROUP = "//row_group";

    /** XML-列分组 */
    public static final String XML_COLUMN_GROUP = "//column_group";

    /** XML-分组名称 */
    public static final String XML_GROUP_NAME = "name";

    /** XML-交叉数据 */
    public static final String XML_DATA = "//data";

    /** 汇总状态标识 */
    public static final String XML_TOTAL_FLAG = "//group_report/@total_flag";
}
