package com.tivit.api.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Metrics")
public class Metrics {

	@Id
	@Column(name="metricId")
	int metricId;
	
	@Column(name="appName")
	String appName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="operatingSystemId")
	private OperatingSystem operatingSystem;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="threadingId")
	private Threading threading;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="threadPoolId")
	private ThreadPool threadPool;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="heapMemorySpaceId")
	private MemorySpace heapMemorySpace;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="nonHeapMemorySpaceId")
	private MemorySpace nonHeapMemorySpace;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="requestProcessorId")
	private RequestProcessor requestProcessor;
	
	@Column(name="creationDate")
	Date creationDate;
	
	public Metrics() {
		this.metricId = 0;
		this.appName = "";
		this.operatingSystem = null;
		this.threading = null;
		this.threadPool = null;
		this.heapMemorySpace = null;
		this.nonHeapMemorySpace = null;		
		this.requestProcessor = null;
		this.creationDate = null;
	}

	public Metrics(int metricId, String appName, OperatingSystem operatingSystem, Threading threading, 
			ThreadPool threadPool, MemorySpace heapMemorySpace, MemorySpace nonHeapMemorySpace, 
			RequestProcessor requestProcessor, Date creationDate) {
		this.metricId = metricId;
		this.appName = appName;
		this.operatingSystem = operatingSystem;
		this.threading = threading;
		this.threadPool = threadPool;
		this.heapMemorySpace = heapMemorySpace;
		this.nonHeapMemorySpace = nonHeapMemorySpace;
		this.requestProcessor = requestProcessor;
		this.creationDate = creationDate;
	}

	public int getMetricId() {
		return metricId;
	}
	public String getAppName() {
		return appName;
	}
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}
	public Threading getThreading() {
		return threading;
	}
	public ThreadPool getThreadPool() {
		return threadPool;
	}
	public MemorySpace getHeapMemorySpace() {
		return heapMemorySpace;
	}
	public MemorySpace getNonHeapMemorySpace() {
		return nonHeapMemorySpace;
	}
	public RequestProcessor getRequestProcessor() {
		return requestProcessor;
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public void setThreading(Threading threading) {
		this.threading = threading;
	}
	public void setThreadPool(ThreadPool threadPool) {
		this.threadPool = threadPool;
	}
	public void setHeapMemorySpace(MemorySpace heapMemorySpace) {
		this.heapMemorySpace = heapMemorySpace;
	}
	public void setNonHeapMemorySpace(MemorySpace nonHeapMemorySpace) {
		this.nonHeapMemorySpace = nonHeapMemorySpace;
	}
	public void setRequestProcessor(RequestProcessor requestProcessor) {
		this.requestProcessor = requestProcessor;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((heapMemorySpace == null) ? 0 : heapMemorySpace.hashCode());
		result = prime * result + metricId;
		result = prime * result + ((nonHeapMemorySpace == null) ? 0 : nonHeapMemorySpace.hashCode());
		result = prime * result + ((operatingSystem == null) ? 0 : operatingSystem.hashCode());
		result = prime * result + ((requestProcessor == null) ? 0 : requestProcessor.hashCode());
		result = prime * result + ((threadPool == null) ? 0 : threadPool.hashCode());
		result = prime * result + ((threading == null) ? 0 : threading.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metrics other = (Metrics) obj;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (heapMemorySpace == null) {
			if (other.heapMemorySpace != null)
				return false;
		} else if (!heapMemorySpace.equals(other.heapMemorySpace))
			return false;
		if (metricId != other.metricId)
			return false;
		if (nonHeapMemorySpace == null) {
			if (other.nonHeapMemorySpace != null)
				return false;
		} else if (!nonHeapMemorySpace.equals(other.nonHeapMemorySpace))
			return false;
		if (operatingSystem == null) {
			if (other.operatingSystem != null)
				return false;
		} else if (!operatingSystem.equals(other.operatingSystem))
			return false;
		if (requestProcessor == null) {
			if (other.requestProcessor != null)
				return false;
		} else if (!requestProcessor.equals(other.requestProcessor))
			return false;
		if (threadPool == null) {
			if (other.threadPool != null)
				return false;
		} else if (!threadPool.equals(other.threadPool))
			return false;
		if (threading == null) {
			if (other.threading != null)
				return false;
		} else if (!threading.equals(other.threading))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Metrics [metricId=" + metricId + ", appName=" + appName + ", operatingSystem=" + operatingSystem
				+ ", threading=" + threading + ", threadPool=" + threadPool + ", heapMemorySpace=" + heapMemorySpace
				+ ", nonHeapMemorySpace=" + nonHeapMemorySpace + ", requestProcessor=" + requestProcessor
				+ ", creationDate=" + creationDate + "]";
	}

}
