package com.wonders.wddc.suite.msg.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("MSG_MAIL_CONFIG")
public class MailConfigBo {
	/**
	 * 主键
	 */
	@Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("MAIL_ID")
    private String mailId;
	/**
	 * 发送名称
	 */
	@Column("MAIL_NAME")
	private String mailName;
	/**
	 * host地址
	 */
	@Column("HOST_ADD")
	private String hostAdd;
	/**
	 * port地址
	 */
	@Column("PORT_ADD")
	private String portAdd;
	/**
	 * 发送邮件登录用户名
	 */
	@Column("USER_NAME")
	private String userName;
	/**
	 * 发送邮件用户密码
	 */
	@Column("PASSWORD")
	private String password;
	/**
	 * 发送用户名称
	 */
	@Column("SEND_NAME")
	private String sendName;
	/**
	 * 创建时间
	 */
	@Column("CREATE_TIME")
	private Date createTime;
	/**
	 * 状态标识1：在使用，0：已删除
	 */
	@Column("flag")
	private boolean flag;
	/**
	 * 生成UUID主键
	 * @return
	 */
	public String initUUID() {
        if (Strings.isEmpty(mailId))
            return UUID.randomUUID().toString();
        return this.mailId;
    }
	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getHostAdd() {
		return hostAdd;
	}
	public void setHostAdd(String hostAdd) {
		this.hostAdd = hostAdd;
	}
	public String getPortAdd() {
		return portAdd;
	}
	public void setPortAdd(String portAdd) {
		this.portAdd = portAdd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
