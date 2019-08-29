package com.tivit.api.entity;

import javax.management.MBeanAttributeInfo;
import javax.management.ObjectName;
import javax.management.RuntimeMBeanException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Threading")
public class Threading {

	@Id
	@Column(name="threadingId")
	int threadingId;
	
	@Column(name="currentThreadCPUTime")
	long currentThreadCPUTime;
	@Column(name="currentThreadUserTime")
	long currentThreadUserTime;
	@Column(name="daemonThreadCount")
	int daemonThreadCount;
	@Column(name="peakThreadCount")
	int peakThreadCount;
	@Column(name="threadCount")
	int threadCount;
	@Column(name="totalStartedThreadCount")
	long totalStartedThreadCount;
	
	public Threading() {
		this.threadingId = 0;
		this.currentThreadCPUTime = 0;
		this.currentThreadUserTime = 0;
		this.daemonThreadCount = 0;
		this.peakThreadCount = 0;
		this.threadCount = 0;
		this.totalStartedThreadCount = 0;
	}
	public Threading(int threadingId, long currentThreadCPUTime, long currentThreadUserTime, int daemonThreadCount,
			int peakThreadCount, int threadCount, long totalStartedThreadCount) {
		this.threadingId = threadingId;
		this.currentThreadCPUTime = currentThreadCPUTime;
		this.currentThreadUserTime = currentThreadUserTime;
		this.daemonThreadCount = daemonThreadCount;
		this.peakThreadCount = peakThreadCount;
		this.threadCount = threadCount;
		this.totalStartedThreadCount = totalStartedThreadCount;
	}
	
	public int getThreadingId() {
		return threadingId;
	}
	public long getCurrentThreadCPUTime() {
		return currentThreadCPUTime;
	}
	public long getCurrentThreadUserTime() {
		return currentThreadUserTime;
	}
	public int getDaemonThreadCount() {
		return daemonThreadCount;
	}
	public int getPeakThreadCount() {
		return peakThreadCount;
	}
	public int getThreadCount() {
		return threadCount;
	}
	public long getTotalStartedThreadCount() {
		return totalStartedThreadCount;
	}

	public void setThreadingId(int threadingId) {
		this.threadingId = threadingId;
	}
	public void setCurrentThreadCPUTime(long currentThreadCPUTime) {
		this.currentThreadCPUTime = currentThreadCPUTime;
	}
	public void setCurrentThreadUserTime(long currentThreadUserTime) {
		this.currentThreadUserTime = currentThreadUserTime;
	}
	public void setDaemonThreadCount(int daemonThreadCount) {
		this.daemonThreadCount = daemonThreadCount;
	}
	public void setPeakThreadCount(int peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	public void setTotalStartedThreadCount(long totalStartedThreadCount) {
		this.totalStartedThreadCount = totalStartedThreadCount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (currentThreadCPUTime ^ (currentThreadCPUTime >>> 32));
		result = prime * result + (int) (currentThreadUserTime ^ (currentThreadUserTime >>> 32));
		result = prime * result + daemonThreadCount;
		result = prime * result + peakThreadCount;
		result = prime * result + threadCount;
		result = prime * result + threadingId;
		result = prime * result + (int) (totalStartedThreadCount ^ (totalStartedThreadCount >>> 32));
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
		Threading other = (Threading) obj;
		if (currentThreadCPUTime != other.currentThreadCPUTime)
			return false;
		if (currentThreadUserTime != other.currentThreadUserTime)
			return false;
		if (daemonThreadCount != other.daemonThreadCount)
			return false;
		if (peakThreadCount != other.peakThreadCount)
			return false;
		if (threadCount != other.threadCount)
			return false;
		if (threadingId != other.threadingId)
			return false;
		if (totalStartedThreadCount != other.totalStartedThreadCount)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Threading [threadingId=" + threadingId + ", currentThreadCPUTime=" + currentThreadCPUTime
				+ ", currentThreadUserTime=" + currentThreadUserTime + ", daemonThreadCount=" + daemonThreadCount
				+ ", peakThreadCount=" + peakThreadCount + ", threadCount=" + threadCount + ", totalStartedThreadCount="
				+ totalStartedThreadCount + "]";
	}
	
}
