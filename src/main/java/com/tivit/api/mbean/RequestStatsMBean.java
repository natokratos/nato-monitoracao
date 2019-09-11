package com.tivit.api.mbean;

import java.util.HashMap;

public interface RequestStatsMBean {

	public String getThreadID();

	public HashMap<String, Measures> getMeasures();

	public void setThreadID(String threadID);

	public void setMeasures(HashMap<String, Measures> measures);
}
