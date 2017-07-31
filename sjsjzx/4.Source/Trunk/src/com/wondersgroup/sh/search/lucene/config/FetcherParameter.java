package com.wondersgroup.sh.search.lucene.config;

import java.io.Serializable;
import java.util.Map;

public class FetcherParameter implements Serializable {
	private static final long serialVersionUID = -8093773231754846554L;
	private String fetcherId;
	private String name;
	private String indexId;
	private String parentIndexId;
	private String datasourceId;
	
	private String tableName;
	private String select;
	private String condition;
	private Map<String, String> fields;
	
	private String timestampField;
	private String syncHours;
	private boolean hasSynchro;
	
	private boolean needRefresh;
	private String updateTimeField;
	
	public FetcherParameter() {
	}

	public String getFetcherId() {
		return fetcherId;
	}

	public void setFetcherId(String fetcherId) {
		this.fetcherId = fetcherId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getTimestampField() {
		return timestampField;
	}

	public void setTimestampField(String timestampField) {
		this.timestampField = timestampField;
	}

	public String getSyncHours() {
		return syncHours;
	}

	public void setSyncHours(String syncHours) {
		this.syncHours = syncHours;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public boolean isHasSynchro() {
		return hasSynchro;
	}

	public void setHasSynchro(boolean hasSynchro) {
		this.hasSynchro = hasSynchro;
	}

	public String getParentIndexId() {
		return parentIndexId;
	}

	public void setParentIndexId(String parentIndexId) {
		this.parentIndexId = parentIndexId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNeedRefresh() {
		return needRefresh;
	}

	public void setNeedRefresh(boolean needRefresh) {
		this.needRefresh = needRefresh;
	}

	public String getUpdateTimeField() {
		return updateTimeField;
	}

	public void setUpdateTimeField(String updateTimeField) {
		this.updateTimeField = updateTimeField;
	}
}
