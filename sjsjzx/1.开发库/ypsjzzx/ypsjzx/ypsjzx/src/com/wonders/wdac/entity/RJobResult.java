package com.wonders.wdac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;


@Table("ac_job_result")
public class RJobResult {

	@Column("CHANNEL_ID")
	@Readonly
	private String channelId;
	
	@Column("ID_JOB")
	@Readonly
	private String idJob;
	
	@Column("RESULT_NAME")
	@Readonly
	private String resultName;
	
	@Column("TRANSNAME")
	@Readonly
	private String transName;
	
	@Column("LINES_READ")
	@Readonly
	private int linesRead;
	
	@Column("LINES_WRITTEN")
	@Readonly
	private int linesWritten;
	
	@Column("LINES_UPDATED")
	@Readonly
	private int linesUpdated;
	
	@Column("LINES_INPUT")
	@Readonly
	private int linesInput;
	
	@Column("LINES_OUTPUT")
	@Readonly
	private int linesOutput;
	
	@Column("LINES_REJECTED")
	@Readonly
	private int linesRejected;
	
	@Column("ERRORS")
	@Readonly
	private int errors;
	
	@Column("STARTDATE")
	@Readonly
	private Date startDate;
	
	@Column("ENDDATE")
	@Readonly
	private Date endDate;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIdJob() {
		return idJob;
	}

	public void setIdJob(String idJob) {
		this.idJob = idJob;
	}

	public String getresultName() {
		return resultName;
	}

	public void setresultName(String resultName) {
		this.resultName = resultName;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public int getLinesRead() {
		return linesRead;
	}

	public void setLinesRead(int linesRead) {
		this.linesRead = linesRead;
	}

	public int getLinesWritten() {
		return linesWritten;
	}

	public void setLinesWritten(int linesWritten) {
		this.linesWritten = linesWritten;
	}

	public int getLinesUpdated() {
		return linesUpdated;
	}

	public void setLinesUpdated(int linesUpdated) {
		this.linesUpdated = linesUpdated;
	}

	public int getLinesInput() {
		return linesInput;
	}

	public void setLinesInput(int linesInput) {
		this.linesInput = linesInput;
	}

	public int getLinesOutput() {
		return linesOutput;
	}

	public void setLinesOutput(int linesOutput) {
		this.linesOutput = linesOutput;
	}

	public int getLinesRejected() {
		return linesRejected;
	}

	public void setLinesRejected(int linesRejected) {
		this.linesRejected = linesRejected;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
