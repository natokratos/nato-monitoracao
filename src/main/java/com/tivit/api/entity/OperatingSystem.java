package com.tivit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OperatingSystem")
public class OperatingSystem {
	
	@Id
	@Column(name="operatingSystemId")
	int operatingSystemId;
	
	@Column(name="name")
	String name;
	@Column(name="arch")
	String arch;
	@Column(name="version")
	String version;
	
	@Column(name="processCPUTime")
	long processCPUTime;
	@Column(name="systemCPULoad")
	double systemCPULoad;
	@Column(name="processCPULoad")
	double processCPULoad;
	@Column(name="systemLoadAverage")
	double systemLoadAverage;
	
	public OperatingSystem() {
		this.operatingSystemId = 0;
		this.name = "";
		this.arch = "";
		this.version = "";
		this.processCPUTime = 0;
		this.systemCPULoad = 0;
		this.processCPULoad = 0;
		this.systemLoadAverage = 0.0;
	}

	public OperatingSystem(int operatingSystemId, String name, String arch, String version, long processCPUTime,
			double systemCPULoad, double processCPULoad, double systemLoadAverage) {
		this.operatingSystemId = operatingSystemId;
		this.name = name;
		this.arch = arch;
		this.version = version;
		this.processCPUTime = processCPUTime;
		this.systemCPULoad = systemCPULoad;
		this.processCPULoad = processCPULoad;
		this.systemLoadAverage = systemLoadAverage;
	}

	public int getOperatingSystemId() {
		return operatingSystemId;
	}
	public String getName() {
		return name;
	}
	public String getArch() {
		return arch;
	}
	public String getVersion() {
		return version;
	}
	public long getProcessCPUTime() {
		return processCPUTime;
	}
	public double getSystemCPULoad() {
		return systemCPULoad;
	}
	public double getProcessCPULoad() {
		return processCPULoad;
	}
	public void setOperatingSystemId(int operatingSystemId) {
		this.operatingSystemId = operatingSystemId;
	}
	public double getSystemLoadAverage() {
		return systemLoadAverage;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setArch(String arch) {
		this.arch = arch;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setProcessCPUTime(long processCPUTime) {
		this.processCPUTime = processCPUTime;
	}
	public void setSystemCPULoad(double systemCPULoad) {
		this.systemCPULoad = systemCPULoad;
	}
	public void setProcessCPULoad(double processCPULoad) {
		this.processCPULoad = processCPULoad;
	}
	public void setSystemLoadAverage(double systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arch == null) ? 0 : arch.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + operatingSystemId;
		long temp;
		temp = Double.doubleToLongBits(processCPULoad);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (processCPUTime ^ (processCPUTime >>> 32));
		temp = Double.doubleToLongBits(systemCPULoad);		
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(systemLoadAverage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		OperatingSystem other = (OperatingSystem) obj;
		if (arch == null) {
			if (other.arch != null)
				return false;
		} else if (!arch.equals(other.arch))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operatingSystemId != other.operatingSystemId)
			return false;
		if (processCPULoad != other.processCPULoad)
			return false;
		if (processCPUTime != other.processCPUTime)
			return false;
		if (systemCPULoad != other.systemCPULoad)
			return false;
		if (Double.doubleToLongBits(systemLoadAverage) != Double.doubleToLongBits(other.systemLoadAverage))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperatingSystem [operatingSystemId=" + operatingSystemId + ", name=" + name + ", arch=" + arch
				+ ", version=" + version + ", processCPUTime=" + processCPUTime + ", systemCPULoad=" + systemCPULoad
				+ ", processCPULoad=" + processCPULoad + ", systemLoadAverage=" + systemLoadAverage + "]";
	}
	
}
