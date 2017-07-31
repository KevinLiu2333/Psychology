package com.wonders.tiles.workflow;

/**
 * 流程常量定义类，包含流程错误信息常量
 *
 */
public class WfConstants {
	
	/** 流程定义错误*/
	public static final String ERROR_WF_CODE = "无流程定义或者存在重复的定义，请检查流程代码是否正确";
	
	/** 无对应角色错误*/
	public static final String ERROR_NO_ROLE = "无对应角色，请检查人员角色关系";
	
	/** 节点定义错误*/
	public static final String ERROR_WF_NODE = "节点定义错误（无起始节点或者没定义节点），请检查流程节点定义";
	
	/** 路由配置错误*/
	public static final String ERROR_ROUTE_PATH = "路由配置错误，无法发送到下一个节点、退回到上一个节点或者找不到路由定义";
	
	/** 案件前置节点不唯一错误*/
	public static final String ERROR_NOT_ONLY = "案件前置节点不唯一，无法确定前置节点";
	
	/** 案件不能回收错误*/
	public static final String ERROR_NOT_CALLBACK = "案件不能回收,下一环节实例已签收或者无下一环节实例";
	
	/** 无流程实例错误*/
	public static final String ERROR_NO_INSTANCE = "无流程实例";
	
	/** 流程实例状态错误*/
	public static final String ERROR_INSTANCE_STATUS = "流程实例状态错误";
	
	/** 无默认路径错误*/
	public static final String ERROR_NO_PATH = "无默认路径，请至少设置一条路径为默认路径";
	
	/** 缺少签收的部门参数*/
	public static final String ERROR_NO_SEND_OUT = "流程触发签收节点，参数列表中缺少签收的部门参数";
	
	/** 缺少签收的部门人员*/
	public static final String ERROR_NO_SEND_OUT_USER = "获取签收部门的用户的为空或者不存在，请检查DefaultRender的相关方法是否正确";

	/** 无用户错误*/
	public static final String ERROR_NO_USER = "无用户，请核查用户信息是否正确";

	/** 无待办用户*/
	public static final String ERROR_NO_TODU_USER = "参数传递过来的部门的过滤或者会签的部门和配置的流程角色包含的人员没有交集，无法设置待办";
	
	/** 案件已经被签收提示*/
	public static final String INFO_HAVE_SIGNIN = "案件已经被重复签收";

    /** 值：是 value=1*/
    public static final String VALUE_YESNO_YES =  "1";

    /** 值：否value=0 */
    public static final String VALUE_YESNO_NO =  "0";
    
    
    /** 操作：启动 */
    public static final String OP_TYPE_START =  "启动";
    /** 操作：签发 */
    public static final String OP_TYPE_SIGNIN =  "签发";
    /** 操作：回退 */
    public static final String OP_TYPE_BACK =  "回退";
    /** 操作：提交 */
    public static final String OP_TYPE_FORWARD =  "提交";
    /** 操作：逻辑删除 */
    public static final String OP_TYPE_DELETE =  "逻辑删除";
    /** 操作：物理删除 */
    public static final String OP_TYPE_TRUNCATE =  "物理删除";
    /** 操作：特送 */
    public static final String OP_TYPE_SPECIAL =  "特送";
    /** 操作：回收 */
    public static final String OP_TYPE_CALLBACK =  "回收";
    /** 操作：恢复 */
    public static final String OP_TYPE_RESUME =  "恢复";
    /** 操作：暂停 */
    public static final String OP_TYPE_SUSPEND =  "暂停";
    /** 操作：回收 */
    public static final String OP_TYPE_FINISH =  "办结";
    

    /** 状态：待办 value=0*/
    public static final String STATUS_SHOW =  "0";
    /** 状态：等待 value=1*/
    public static final String STATUS_WAIT =  "1";
    /** 状态：已完成 value=2 */
    public static final String STATUS_FINISH =  "2";
    /** 状态：已回退 value=3 */
    public static final String STATUS_BACK =  "3";
    /** 状态：已暂停 value=4 */
    public static final String STATUS_STOP =  "4";
    

    /** 节点类型：开始节点 value=0 */
    public static final String NODE_TYPE_START =  "0";

    /** 节点类型：普通节点 value=1*/
    public static final String NODE_TYPECOMMON =  "1";

    /** 节点类型：结束节点 value=9*/
    public static final String NODE_TYPE_END =  "9";
    


    /** 聚合类型：单一聚合 value=0 */
    public static final String JOIN_TYPE_1 =  "1";

    /** 聚合类型：多路聚合 value=1*/
    public static final String JOIN_TYPE_2 =  "2";

    /** 聚合类型：全部聚合 value=3*/
    public static final String JOIN_TYPE_3 =  "3";

    /** 聚合类型：分支的条件不同 value=9*/
    public static final String JOIN_TYPE_9 =  "9";
    


    /** 不会签 value=0*/
    public static final String SENDOUT_0 =  "0";


    /** 会签value=1*/
    public static final String SENDOUT_1 =  "1";


    /** 会签延续 value=3*/
    public static final String SENDOUT_3 =  "3";
    

    /** 节点：开始节点 */
    public static final String NODE_START =  "START";

    /** 节点：结束节点 */
    public static final String NODE_END =  "END";

    /** 节点：设置签收人变量 */
    public static final String SIGN_USER_ID =  "signUserId";

    /** 节点：设置签收日期人变量--值为 日期的long型的值*/
    public static final String SIGN_DATE =  "signDate";

    /** 节点：会签变量,多个的haunted用逗号隔开 ，包含各部门角色ID*/
    public static final String SEND_OUTS =  "sentOuts";

    /** 节点：过滤部门，实际上是一个角色ID，角色下包含部门所有人员 */
    public static final String DEPT_FILTER =  "filterDeptId";

}
