package com.wondersgroup.sh.search.task;

import java.util.Map;

public interface ParameterizedTask {
	void setParameterValues(Map<String, String> parameters);
}
