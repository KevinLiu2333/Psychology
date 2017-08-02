package com.wonders.wddc.tiles.file.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("TILES_FILE")
public class FwFileBo {
	    @Name
	    @Column("FILE_ID")
		private String fileId; 
	    /**
		 * 文件后缀
		 */
		@Column("FILE_SUFFIX")
	    private String fileSuffix;
	    /**
		 * 业务主键
		 */
		@Column("FILE_BUS_ID")
	    private String fileBusId;
	    /**
		 * 文件业务类型
		 */
		@Column("FILE_BUS_TYPE")
	    private String fileBusType;
	    /**
		 * 文件名称
		 */
		@Column("FILE_NAME")
	    private String fileName;
	    /**
		 * 文件路径
		 */
		@Column("FILE_PATH")
	    private String filePath;
	    /**
		 * 文件路径
		 */
		@Column("FILE_CREATE_TIME")
	    private Date fileCreateTime;
	    /**
		 * 平台代码
		 */
		@Column("PLATFORM_CODE")
	    private String platformCode;
	    /**
		 * 储存类型
		 */
		@Column("SAVE_TYPE")
	    private String saveType;
		
		public String initUUIDfileId() {
			if (Strings.isEmpty(fileId))
				return UUID.randomUUID().toString();
			return this.fileId;
		}

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public String getFileSuffix() {
			return fileSuffix;
		}

		public void setFileSuffix(String fileSuffix) {
			this.fileSuffix = fileSuffix;
		}

		public String getFileBusId() {
			return fileBusId;
		}

		public void setFileBusId(String fileBusId) {
			this.fileBusId = fileBusId;
		}

		public String getFileBusType() {
			return fileBusType;
		}

		public void setFileBusType(String fileBusType) {
			this.fileBusType = fileBusType;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public Date getFileCreateTime() {
			return fileCreateTime;
		}

		public void setFileCreateTime(Date fileCreateTime) {
			this.fileCreateTime = fileCreateTime;
		}

		public String getPlatformCode() {
			return platformCode;
		}

		public void setPlatformCode(String platformCode) {
			this.platformCode = platformCode;
		}

		public String getSaveType() {
			return saveType;
		}

		public void setSaveType(String saveType) {
			this.saveType = saveType;
		}
		
		
}
