package com.tivit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Threadpool")
public class ThreadPool {

	@Id
	@Column(name="threadPoolId")
	int threadPoolId;
	@Column(name="acceptCount")
	int acceptCount;
	@Column(name="acceptorThreadCount")
	int acceptorThreadCount;
	@Column(name="acceptorThreadPriority")
	int acceptorThreadPriority;
	@Column(name="connectionCount")
	long connectionCount;
	@Column(name="connectionLinger")
	int connectionLinger;
	@Column(name="currentThreadCount")
	int currentThreadCount;
	@Column(name="currentThreadBusy")
	int currentThreadBusy;
	@Column(name="keepAliveCount")
	int keepAliveCount;
	@Column(name="pollerThreadCount")
	int pollerThreadCount;
	@Column(name="pollerThreadPriority")
	int pollerThreadPriority;
	@Column(name="running")
	boolean running;

	
	public ThreadPool() {
		this.threadPoolId = 0;
		this.acceptCount = 0;
		this.acceptorThreadCount = 0;
		this.acceptorThreadPriority = 0;
		this.connectionCount = 0;
		this.connectionLinger = 0;
		this.currentThreadCount = 0;
		this.currentThreadBusy = 0;
		this.keepAliveCount = 0;
		this.pollerThreadCount = 0;
		this.pollerThreadPriority = 0;
		this.running = false;
	}
	
	public ThreadPool(int threadPoolId, int acceptCount, int acceptorThreadCount, int acceptorThreadPriority,
			long connectionCount, int connectionLinger, int currentThreadCount, int currentThreadBusy,
			int keepAliveCount, int pollerThreadCount, int pollerThreadPriority, boolean running) {
		this.threadPoolId = threadPoolId;
		this.acceptCount = acceptCount;
		this.acceptorThreadCount = acceptorThreadCount;
		this.acceptorThreadPriority = acceptorThreadPriority;
		this.connectionCount = connectionCount;
		this.connectionLinger = connectionLinger;
		this.currentThreadCount = currentThreadCount;
		this.currentThreadBusy = currentThreadBusy;
		this.keepAliveCount = keepAliveCount;
		this.pollerThreadCount = pollerThreadCount;
		this.pollerThreadPriority = pollerThreadPriority;
		this.running = running;
	}


	public int getThreadPoolId() {
		return threadPoolId;
	}
	public int getAcceptCount() {
		return acceptCount;
	}
	public int getAcceptorThreadCount() {
		return acceptorThreadCount;
	}
	public int getAcceptorThreadPriority() {
		return acceptorThreadPriority;
	}
	public long getConnectionCount() {
		return connectionCount;
	}
	public int getConnectionLinger() {
		return connectionLinger;
	}
	public int getCurrentThreadCount() {
		return currentThreadCount;
	}
	public int getCurrentThreadBusy() {
		return currentThreadBusy;
	}
	public int getKeepAliveCount() {
		return keepAliveCount;
	}
	public int getPollerThreadCount() {
		return pollerThreadCount;
	}
	public int getPollerThreadPriority() {
		return pollerThreadPriority;
	}
	public boolean isRunning() {
		return running;
	}

	public void setThreadPoolId(int threadPoolId) {
		this.threadPoolId = threadPoolId;
	}
	public void setAcceptCount(int acceptCount) {
		this.acceptCount = acceptCount;
	}
	public void setAcceptorThreadCount(int acceptorThreadCount) {
		this.acceptorThreadCount = acceptorThreadCount;
	}
	public void setAcceptorThreadPriority(int acceptorThreadPriority) {
		this.acceptorThreadPriority = acceptorThreadPriority;
	}
	public void setConnectionCount(long connectionCount) {
		this.connectionCount = connectionCount;
	}
	public void setConnectionLinger(int connectionLinger) {
		this.connectionLinger = connectionLinger;
	}
	public void setCurrentThreadCount(int currentThreadCount) {
		this.currentThreadCount = currentThreadCount;
	}
	public void setCurrentThreadBusy(int currentThreadBusy) {
		this.currentThreadBusy = currentThreadBusy;
	}
	public void setKeepAliveCount(int keepAliveCount) {
		this.keepAliveCount = keepAliveCount;
	}
	public void setPollerThreadCount(int pollerThreadCount) {
		this.pollerThreadCount = pollerThreadCount;
	}
	public void setPollerThreadPriority(int pollerThreadPriority) {
		this.pollerThreadPriority = pollerThreadPriority;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acceptCount;
		result = prime * result + acceptorThreadCount;
		result = prime * result + acceptorThreadPriority;
		result = prime * result + (int) (connectionCount ^ (connectionCount >>> 32));
		result = prime * result + connectionLinger;
		result = prime * result + currentThreadBusy;
		result = prime * result + currentThreadCount;
		result = prime * result + keepAliveCount;
		result = prime * result + pollerThreadCount;
		result = prime * result + pollerThreadPriority;
		result = prime * result + (running ? 1231 : 1237);
		result = prime * result + threadPoolId;
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
		ThreadPool other = (ThreadPool) obj;
		if (acceptCount != other.acceptCount)
			return false;
		if (acceptorThreadCount != other.acceptorThreadCount)
			return false;
		if (acceptorThreadPriority != other.acceptorThreadPriority)
			return false;
		if (connectionCount != other.connectionCount)
			return false;
		if (connectionLinger != other.connectionLinger)
			return false;
		if (currentThreadBusy != other.currentThreadBusy)
			return false;
		if (currentThreadCount != other.currentThreadCount)
			return false;
		if (keepAliveCount != other.keepAliveCount)
			return false;
		if (pollerThreadCount != other.pollerThreadCount)
			return false;
		if (pollerThreadPriority != other.pollerThreadPriority)
			return false;
		if (running != other.running)
			return false;
		if (threadPoolId != other.threadPoolId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ThreadPool [threadPoolId=" + threadPoolId + ", acceptCount=" + acceptCount + ", acceptorThreadCount="
				+ acceptorThreadCount + ", acceptorThreadPriority=" + acceptorThreadPriority + ", connectionCount="
				+ connectionCount + ", connectionLinger=" + connectionLinger + ", currentThreadCount="
				+ currentThreadCount + ", currentThreadBusy=" + currentThreadBusy + ", keepAliveCount=" + keepAliveCount
				+ ", pollerThreadCount=" + pollerThreadCount + ", pollerThreadPriority=" + pollerThreadPriority
				+ ", running=" + running + "]";
	}
	
}
