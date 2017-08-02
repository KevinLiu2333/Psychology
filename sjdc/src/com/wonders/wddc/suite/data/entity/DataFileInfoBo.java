package com.wonders.wddc.suite.data.entity;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 文件缓存配置信息实体类
 * @author wonders
 *
 */
@Table("suite_datafile_info")
public class DataFileInfoBo {

	@Name
	@Column("id")
	private String id;
	
	@Column("name")
	private String name;
	
	@Column("file_path")
	private String filepath;
	
	@Column("file_type")
	private String filetype;//文件类型：1：json、2：xml
	
	@Column("file_name")
	private String filename;

	@Column("update_time")
	private Date updatetime;//更新时间
	private List<DataFileLinkBo> fileList;
	private List<DataFileCustomJsonBo> customList;

	

	public List<DataFileCustomJsonBo> getCustomList() {
		return customList;
	}

	public void setCustomList(List<DataFileCustomJsonBo> customList) {
		this.customList = customList;
	}

	public List<DataFileLinkBo> getFileList() {
		return fileList;
	}

	public void setFileList(List<DataFileLinkBo> fileList) {
		this.fileList = fileList;
	}

	private List<MultStatInfoBo> datas;
	
	public List<MultStatInfoBo> getDatas() {
		return datas;
	}

	public void setDatas(List<MultStatInfoBo> datas) {
		this.datas = datas;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	

		
	
}
