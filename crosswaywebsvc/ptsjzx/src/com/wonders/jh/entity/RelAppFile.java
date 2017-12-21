package com.wonders.jh.entity;
import java.util.Date;
import java.util.UUID;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.lang.Strings;

@Table("REL_APP_FILE")
@IocBean
public class RelAppFile {

    @Name
    @Prev(els = @EL("$me.initUUIDId()"))
    @Column("FILE_ID")
	private String fileId; 
    @Column("APPLY_ID")
    private String applyId; 
    @Column("FILE_TYPE")
	private String fileType; 
    @Column("FILE_PATH")
	private String filePath; 
    @Column("FILE_NAME")
	private String fileName; 
    @Column("CREATE_DATE")
	private Date createDate; 
    @Column("REL_FILE_ID")
	private String relFileId;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRelFileId() {
		return relFileId;
	}
	public void setRelFileId(String relFileId) {
		this.relFileId = relFileId;
	} 
    public String initUUIDId() {
		if (Strings.isEmpty(fileId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.fileId;
	}
    
}
