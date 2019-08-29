package com.tivit.api.mbean;

import java.util.Map;

public interface TivitMonMBean {
	//public Map<Thread, Repository> getThreadsAndRepositories();
	public Map<String, String> getThreadsAndRepositories();
	
	public void setThreadsAndRepositories(String thread, String string);
	
	public Thread getBackgroundThread();

	public Integer getNumberOfSecondsRunning();

	public Long getNumberOfUnixSecondsRunning();

	public String getName();

}
