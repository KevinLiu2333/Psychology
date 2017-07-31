package com.wonders.smrz.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("JK_SMRZ_ITEM")
public class SmrzItem {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("ITEM_ID")
    private String itemId;
    /**
     *题库主键
     */
    @Column("BANK_ID")
    private String bankId;
    /**
     *身份照
     */
    @Column("ZJHM")
    private String zjhm;
   
    /**
     *姓名
     */
    @Column("XM")
    private String xm;
    /**
     *问题
     */
    @Column("QUSTION")
    private String question;
    /**
     *答案
     */
    @Column("ANSWER")
    private String answer;
    /**
     *创建时间
     */
    @Column("CREATE_TIME")
    private Date createTime;
    /**
     *答案是否正确
     */
    @Column("IS_OK")
    private String isOk;
    /**
     *生成批次
     */
    @Column("CREATE_NUM")
    private String createNum;
    

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(itemId))
            return UUID.randomUUID().toString();
        return this.itemId;
    }
    
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public String getCreateNum() {
		return createNum;
	}
	public void setCreateNum(String createNum) {
		this.createNum = createNum;
	}

}
