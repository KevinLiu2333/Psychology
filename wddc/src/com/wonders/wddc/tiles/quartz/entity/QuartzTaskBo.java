package com.wonders.wddc.tiles.quartz.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("tiles_quartz_task")
public class QuartzTaskBo {
	@Name
	@Column("ID")
	private String id;
	@Column("name")
	private String name;
	@Column("excute_time")
	private String excutetime;
	@Column("current_state")
	private String currentstate;//当前状态1：运行中  0：停止
	@Column("job_class")
	private String jobclass;
	@Column("project_name")
	private String projectname;
	
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
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
	public String getExcutetime() {
		return excutetime;
	}
	public void setExcutetime(String excutetime) {
		this.excutetime = excutetime;
	}
	public String getCurrentstate() {
		return currentstate;
	}
	public void setCurrentstate(String currentstate) {
		this.currentstate = currentstate;
	}
	public String getJobclass() {
		return jobclass;
	}
	public void setJobclass(String jobclass) {
		this.jobclass = jobclass;
	}
	
}
