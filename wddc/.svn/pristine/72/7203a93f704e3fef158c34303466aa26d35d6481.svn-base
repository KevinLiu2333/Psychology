package com.wonders.sjfw.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;
import org.nutz.lang.Strings;

/**
 * 申请项配置信息.
 */
@Table("PF_FW_CONFIG")
@View("V_ZY_ITEM")
public class FwConfig {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("FW_CONFIG_ID")
    private String fwConfigId;
    /**
     * 主键
     */
    @Column("ZY_ITEM_ID")
    private String zyItemId;
    /**
     * 主键
     */
    @Column("FW_INFO_ID")
    private String fwInfoId;
    /**
     * 类型1:查询条件;2:返回结果;3:固定where条件;5:分组条件
     */
    @Column("CONFIG_TYPE")
    private String configType;
    /**
     * 验证条件
     */
    @Column("PARAM_VALID")
    private String paramValid;

    @Column("FW_APPLY_ID")
    private String fwApplyId;

    /**
     * 结果计算
     */
    @Column("OP_TYPE")
    private String opType;
    /**
     * 结果说明
     */
    @Column("OP_VALUE")
    private String opValue;
    /**
	 * 字段配置主键 colCfgId
	 */
	@Column("COL_CFG_ID")
	@Readonly
	private Integer colCfgId;
	
	/**
	 * 表中字段英文名
	 */
	@Column("COL_NAME")
	@Readonly
	private String colName;
	
	/**
	 * 表中字段注释
	 */
	@Column("COL_COMMENT")
	@Readonly
	private String colComment;

	/**
	 * 字段类型名称对应DATA_TYPE
	 */
	@Column("COL_TYPE_NAME")
	@Readonly
	private String colTypeName;
	
	/**
	 * colType 字段类型加长度
	 */
	@Column("COL_TYPE")
	@Readonly
	private String colType;
	
	/**
	 * 字段编辑类型
	 */
	@Column("EDIT_TYPE")
	@Readonly
	private String editType;
	
	//以下字段为字段配置表附加信息
	
	/**
	 * 关联表配置，表配置主键 themeId
	 */
	@Column("THEME_ID")
	@Readonly
	private Integer themeId;
	
	/**
	 * 字段与字典关联，字典主键dicId 
	 */
	@Column("DIC_ID")
	@Readonly
	private String dicId;
	
	/**
	 * 字段有效性
	 */
	@Column("VALIDITY")
	@Readonly
	private String validity;
	
	/**
	 * 字段是否是主键
	 */
	@Column("IS_PK")
	@Readonly
	private String isPk;
	
	/**
	 * 字段排序
	 */
	@Column("ORDER_NUM")
	@Readonly
	private Integer orderNum;



    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwConfigId))
            return UUID.randomUUID().toString();
        return this.fwConfigId;
    }

    public String getFwConfigId() {
        return fwConfigId;
    }

    public void setFwConfigId(String fwConfigId) {
        this.fwConfigId = fwConfigId;
    }

    public String getZyItemId() {
        return zyItemId;
    }

    public void setZyItemId(String zyItemId) {
        this.zyItemId = zyItemId;
    }

    public String getFwInfoId() {
        return fwInfoId;
    }

    public void setFwInfoId(String fwInfoId) {
        this.fwInfoId = fwInfoId;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getParamValid() {
        return paramValid;
    }

    public void setParamValid(String paramValid) {
        this.paramValid = paramValid;
    }

    public String getFwApplyId() {
        return fwApplyId;
    }

    public void setFwApplyId(String fwApplyId) {
        this.fwApplyId = fwApplyId;
    }

   

	public Integer getColCfgId() {
		return colCfgId;
	}

	public void setColCfgId(Integer colCfgId) {
		this.colCfgId = colCfgId;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColComment() {
		return colComment;
	}

	public void setColComment(String colComment) {
		this.colComment = colComment;
	}

	public String getColTypeName() {
		return colTypeName;
	}

	public void setColTypeName(String colTypeName) {
		this.colTypeName = colTypeName;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpValue() {
		return opValue;
	}

	public void setOpValue(String opValue) {
		this.opValue = opValue;
	}
    
}
