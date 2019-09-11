package com.tivit.api.mbean;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RequestStats implements RequestStatsMBean {

	//private Thread backgroundThread;
	private String threadID;
	private HashMap<String, Measures> measures = new HashMap<String, Measures>();

	public RequestStats() {
		this.threadID = "";
	}

	/*public void status() {
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
	}*/
	
	public String getThreadID() {
		return threadID;
	}
	public HashMap<String, Measures> getMeasures() {
		return measures;
	}
	/*public Thread getBackgroundThread() {
		return backgroundThread;
	}*/

	public void setThreadID(String threadID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		
		this.threadID = sdf.format(new Date()) + String.format("%08d", threadID);
	}
	public void setMeasures(HashMap<String, Measures> measures) {
		this.measures = measures;
	}
	public void addMeasures(String threadID, Measures measures) {
		setThreadID(threadID);
		
		this.measures.put(getThreadID(), measures);
	}
	/*public void setBackgroundThread(Thread backgroundThread) {
		this.backgroundThread = backgroundThread;
	}*/

}
