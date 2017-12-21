package com.wonders.wddc.suite.msg.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("MSG_MAIL_LOG")
public class MailLogBo {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ID")
	private String id;
	
	@Column("MAIL_ID")
	private String mailId;
	
	@Column("TO_ADDRESS")
	private String toAddress;
	
	@Column("TO_MORE_ADDRESS")
	private String toMoreAddress;
	
	@Column("MAIL_SUBJECT")
	private String mailSubject;
	
	@Column("MAIL_CONTENT")
	private String mailContent;
	
	@Column("TYPE")
	private String type;
	
	@Column("SEND_TIME")
	private Date sendTime;
	
	public String initUUID() {
        if (Strings.isEmpty(mailId))
            return UUID.randomUUID().toString();
        return this.mailId;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getToMoreAddress() {
		return toMoreAddress;
	}

	public void setToMoreAddress(String toMoreAddress) {
		this.toMoreAddress = toMoreAddress;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}
