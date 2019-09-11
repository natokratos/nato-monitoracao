package com.tivit.api.mbean;

public class Measures {
	
	private String name;
	private Long initialTime;
	private Long finaltime;
	
	public String getName() {
		return name;
	}
	public Long getInitialTime() {
		return initialTime;
	}
	public Long getFinaltime() {
		return finaltime;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setInitialTime(Long initialTime) {
		this.initialTime = initialTime;
	}
	public void setFinaltime(Long finaltime) {
		this.finaltime = finaltime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finaltime == null) ? 0 : finaltime.hashCode());
		result = prime * result + ((initialTime == null) ? 0 : initialTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Measures other = (Measures) obj;
		if (finaltime == null) {
			if (other.finaltime != null)
				return false;
		} else if (!finaltime.equals(other.finaltime))
			return false;
		if (initialTime == null) {
			if (other.initialTime != null)
				return false;
		} else if (!initialTime.equals(other.initialTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Measure [name=" + name + ", initialTime=" + initialTime + ", finaltime=" + finaltime + "]";
	}
	
}
