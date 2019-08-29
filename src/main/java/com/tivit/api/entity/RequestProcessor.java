package com.tivit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RequestProcessor")
public class RequestProcessor {
	
	@Id
	@Column(name="requestProcessorId")
	int requestProcessorId;
	
	@Column(name="rpName")
	String rpName;
	@Column(name="maxRequestUri")
	String maxRequestUri;
	
	@Column(name="errorCount")
	int errorCount;

	@Column(name="lastRequestProcessingTime")
	long lastRequestProcessingTime;
	@Column(name="maxTime")
	long maxTime;
	@Column(name="processingTime")
	long processingTime;
	@Column(name="requestCount")
	int requestCount;
	@Column(name="requestProcessingTime")
	long requestProcessingTime;
	
	public RequestProcessor() {
		this.requestProcessorId = 0;
		this.rpName = "";
		this.maxRequestUri = "";
		this.errorCount = 0;
		this.lastRequestProcessingTime = 0;
		this.maxTime = 0;
		this.processingTime = 0;
		this.requestCount = 0;
		this.requestProcessingTime = 0;
	}

	public RequestProcessor(int requestProcessorId, String rpName, String maxRequestUri, int errorCount,
			long lastRequestProcessingTime, long maxTime, long processingTime, int requestCount,
			long requestProcessingTime) {
		this.requestProcessorId = requestProcessorId;
		this.rpName = rpName;
		this.maxRequestUri = maxRequestUri;
		this.errorCount = errorCount;
		this.lastRequestProcessingTime = lastRequestProcessingTime;
		this.maxTime = maxTime;
		this.processingTime = processingTime;
		this.requestCount = requestCount;
		this.requestProcessingTime = requestProcessingTime;
	}

	public int getRequestProcessorId() {
		return requestProcessorId;
	}
	public String getRpName() {
		return rpName;
	}
	public String getMaxRequestUri() {
		return maxRequestUri;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public long getLastRequestProcessingTime() {
		return lastRequestProcessingTime;
	}
	public long getMaxTime() {
		return maxTime;
	}
	public long getProcessingTime() {
		return processingTime;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public long getRequestProcessingTime() {
		return requestProcessingTime;
	}

	public void setRequestProcessorId(int requestProcessorId) {
		this.requestProcessorId = requestProcessorId;
	}
	public void setRpName(String rpName) {
		this.rpName = rpName;
	}
	public void setMaxRequestUri(String maxRequestUri) {
		this.maxRequestUri = maxRequestUri;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public void setLastRequestProcessingTime(long lastRequestProcessingTime) {
		this.lastRequestProcessingTime = lastRequestProcessingTime;
	}
	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}
	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	public void setRequestProcessingTime(long requestProcessingTime) {
		this.requestProcessingTime = requestProcessingTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + errorCount;
		result = prime * result + (int) (lastRequestProcessingTime ^ (lastRequestProcessingTime >>> 32));
		result = prime * result + ((maxRequestUri == null) ? 0 : maxRequestUri.hashCode());
		result = prime * result + (int) (maxTime ^ (maxTime >>> 32));
		result = prime * result + (int) (processingTime ^ (processingTime >>> 32));
		result = prime * result + requestCount;
		result = prime * result + (int) (requestProcessingTime ^ (requestProcessingTime >>> 32));
		result = prime * result + requestProcessorId;
		result = prime * result + ((rpName == null) ? 0 : rpName.hashCode());
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
		RequestProcessor other = (RequestProcessor) obj;
		if (errorCount != other.errorCount)
			return false;
		if (lastRequestProcessingTime != other.lastRequestProcessingTime)
			return false;
		if (maxRequestUri == null) {
			if (other.maxRequestUri != null)
				return false;
		} else if (!maxRequestUri.equals(other.maxRequestUri))
			return false;
		if (maxTime != other.maxTime)
			return false;
		if (processingTime != other.processingTime)
			return false;
		if (requestCount != other.requestCount)
			return false;
		if (requestProcessingTime != other.requestProcessingTime)
			return false;
		if (requestProcessorId != other.requestProcessorId)
			return false;
		if (rpName == null) {
			if (other.rpName != null)
				return false;
		} else if (!rpName.equals(other.rpName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestProcessor [requestProcessorId=" + requestProcessorId + ", rpName=" + rpName + ", maxRequestUri="
				+ maxRequestUri + ", errorCount=" + errorCount + ", lastRequestProcessingTime="
				+ lastRequestProcessingTime + ", maxTime=" + maxTime + ", processingTime=" + processingTime
				+ ", requestCount=" + requestCount + ", requestProcessingTime=" + requestProcessingTime + "]";
	}
	
}
