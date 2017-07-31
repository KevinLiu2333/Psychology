package com.wonders.smrz.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("JK_SMRZ_ITEM_BANK")
public class SmrzItemBank {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("ITEM_BANK_ID")
    private String bankId;
    /**
     *字段名称
     */
    @Column("COL_NAME")
    private String colName;
    /**
     *问题
     */
    @Column("QUESTION")
    private String question;
   
    /**
     *优先级
     */
    @Column("PRIORITY")
    private int priority;
    


    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(bankId))
            return UUID.randomUUID().toString();
        return this.bankId;
    }



	public String getBankId() {
		return bankId;
	}



	public void setBankId(String bankId) {
		this.bankId = bankId;
	}



	public String getColName() {
		return colName;
	}



	public void setColName(String colName) {
		this.colName = colName;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}

	
    


}
