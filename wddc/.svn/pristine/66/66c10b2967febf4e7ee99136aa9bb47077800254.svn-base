package com.wonders.wddc.tiles.switches.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 开关组件实体类
 * @author wonders
 *
 */
@Table("pf_switch_config")
public class SwitchInfoBo {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("switch_id")
	private String id;
	@Column("switch_code")
	private String code;
	@Column("switch_name")
	private String name;
	@Column("switch_value")
	private String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(id))
            return UUID.randomUUID().toString();
        return this.id;
    }
	
	
	
}
