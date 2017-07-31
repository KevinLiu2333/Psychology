package com.wondersgroup.sh.search.lucene.config;

import java.util.Map;

import org.dom4j.Element;

public class EntityBridgeInfo extends FieldBridgeInfo {
	protected String field;
	
	public EntityBridgeInfo() {
	}
	
	public EntityBridgeInfo(String className, Map parameters, String field) {
		super(className, parameters);
		this.field = field;
	}

	@Override
	public Element toXml(Element parentEl) {
		Element bridgeEl = super.toXml(parentEl);
		bridgeEl.addAttribute("field", this.field);
		return bridgeEl;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
