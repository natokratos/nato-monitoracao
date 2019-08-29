package com.tivit.api.mbean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TivitMon implements TivitMonMBean {

	private Thread backgroundThread;
	private Integer numberOfSecondsRunning;
	private Long numberOfUnixSecondsRunning;
	private String name;
	//private HashMap<Thread, Repository> maps;
	private Map<String, String> maps = new HashMap<String, String>();
	
	public TivitMon() {
		this.backgroundThread = null;
		this.numberOfSecondsRunning = 0;
	    this.numberOfUnixSecondsRunning = System.currentTimeMillis() / 1000L;
		this.name = "Teste TivitMon - " + (new Date().toString());
	}

	public void status() {
		this.backgroundThread = new Thread();
		this.numberOfSecondsRunning = 0;
	    this.numberOfUnixSecondsRunning = System.currentTimeMillis() / 1000L;
		this.name = "Teste TivitMon - " + (new Date().toString());
		//this.maps = new HashMap<Thread, Repository>();
		//this.maps = new HashMap<String, Class<? extends TransferRepository>>();
		
		// We will use a background thread to update the metrics
		this.backgroundThread = new Thread(() -> {
	           try {
	               while (true) {
	                   // Every second we update the metrics
	                   numberOfSecondsRunning += 1;
	                   numberOfUnixSecondsRunning += 1;
	                   this.name = "Teste TivitMon - " + (new Date().toString());
	                   //switchStatus = !switchStatus;
	                   Thread.sleep(1000L);
	               }
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
		});

		this.backgroundThread.setName("backgroundThread");
		this.backgroundThread.start();
	}
	
	//public Map<Thread, Repository> getThreadsAndRepositories() {
	public Map<String, String> getThreadsAndRepositories() {
		return maps;
	}

	//public void setThreadsAndRepositories(Thread thread, Repository repository) {
	public void setThreadsAndRepositories(String thread, String repository) {
		this.maps.put(thread, repository);
	}
	
	public Thread getBackgroundThread() {
		return backgroundThread;
	}

	public void setBackgroundThread(Thread backgroundThread) {
		this.backgroundThread = backgroundThread;
	}

	public Integer getNumberOfSecondsRunning() {
		return numberOfSecondsRunning;
	}

	public void setNumberOfSecondsRunning(Integer numberOfSecondsRunning) {
		this.numberOfSecondsRunning = numberOfSecondsRunning;
	}

	public Long getNumberOfUnixSecondsRunning() {
		return numberOfUnixSecondsRunning;
	}

	public void setNumberOfUnixSecondsRunning(Long numberOfUnixSecondsRunning) {
		this.numberOfUnixSecondsRunning = numberOfUnixSecondsRunning;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
