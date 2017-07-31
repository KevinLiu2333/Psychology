package com.wonders.db.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("XDB_FILES")
public class Xdb_files {
	//主键
	@Name
    @Column("FID")
	private String fid;
    @Column("USERID")
	private String userid;
	//文件实际名称
	@Column("FILENAMELOCAL")
	private String filenamelocal;
	//服务器上的文件名(主键+文件后缀)
	@Column("FILENAMEREMOTE")
	private String filenameremote;
	//文件上传源路径(客户端)
	@Column("FILEPATHLOCAL")
	private String filepathlocal;
	//文件上传目标路径(服务器端)
	@Column("FILEPATHREMOTE")
	private String filepathremote;
	//文件相对路径(服务器端)
	@Column("FILEPATHRELATIVE")
	private String filepathrelative;
	//文件的MD5编码
	@Column("FILEMD5")
	private String filemd5;
	//文件大小
	@Column("FILELENGTH")
	private String filelength;
	@Column("FILESIZE")
	private String filesize;
	//文件未上传完部分的大小
	@Column("FILEPOS")
	private String filepos;
	//文件已上传完部分的大小
	@Column("POSTEDLENGTH")
	private String postedlength;
	//上传百分比
	@Column("POSTEDPERCENT")
	private String postedpercent;
	//是否已完成(1,完成 0,未完成)
	@Column("POSTCOMPLETE")
	private int postcomplete;
	//上传时间
	@Column("POSTEDTIME")
	private Date postedtime;
	//是否逻辑删除(1,删除 0,未删除)
	@Column("ISDELETED")
	private int isdeleted;
	@Column("BUSSINESSID")
	private int bussinessid;
	@Column("BUSSINESSTYPE")
	private int bussinesstype;
	
	@Column("FILETYPE")
	private String fileType;
	
	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	private String fileSuffix;
	
	public String getFileSuffix() {
		return fileSuffix;
	}


	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}



	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFilenamelocal() {
		return filenamelocal;
	}
	public void setFilenamelocal(String filenamelocal) {
		this.filenamelocal = filenamelocal;
	}
	public String getFilenameremote() {
		return filenameremote;
	}
	public void setFilenameremote(String filenameremote) {
		this.filenameremote = filenameremote;
	}
	public String getFilepathlocal() {
		return filepathlocal;
	}
	public void setFilepathlocal(String filepathlocal) {
		this.filepathlocal = filepathlocal;
	}
	public String getFilepathremote() {
		return filepathremote;
	}
	public void setFilepathremote(String filepathremote) {
		this.filepathremote = filepathremote;
	}
	public String getFilepathrelative() {
		return filepathrelative;
	}
	public void setFilepathrelative(String filepathrelative) {
		this.filepathrelative = filepathrelative;
	}
	public String getFilemd5() {
		return filemd5;
	}
	public void setFilemd5(String filemd5) {
		this.filemd5 = filemd5;
	}
	public String getFilelength() {
		return filelength;
	}
	public void setFilelength(String filelength) {
		this.filelength = filelength;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getFilepos() {
		return filepos;
	}
	public void setFilepos(String filepos) {
		this.filepos = filepos;
	}
	public String getPostedlength() {
		return postedlength;
	}
	public void setPostedlength(String postedlength) {
		this.postedlength = postedlength;
	}
	public int getPostcomplete() {
		return postcomplete;
	}
	public void setPostcomplete(int postcomplete) {
		this.postcomplete = postcomplete;
	}
	public Date getPostedtime() {
		return postedtime;
	}
	public void setPostedtime(Date postedtime) {
		this.postedtime = postedtime;
	}
	
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	public int getBussinessid() {
		return bussinessid;
	}
	public void setBussinessid(int bussinessid) {
		this.bussinessid = bussinessid;
	}
	public int getBussinesstype() {
		return bussinesstype;
	}
	public void setBussinesstype(int bussinesstype) {
		this.bussinesstype = bussinesstype;
	}
	public String getPostedpercent() {
		return postedpercent;
	}
	public void setPostedpercent(String postedpercent) {
		this.postedpercent = postedpercent;
	}
	
	
}
