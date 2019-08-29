package com.tivit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tivit.api.domain.MemoryType;

@Entity
@Table(name="MemorySpace")
public class MemorySpace {
	
	@Id
	@Column(name="memorySpaceId")
	int memorySpaceId;
	@Column(name="initial")
	long initial;
	@Column(name="max")
	long max;
	@Column(name="used")
	long used;
	@Enumerated(EnumType.STRING)
	@Column(length = 16, name="memoryType")
	MemoryType memoryType;
	
	public MemorySpace() {
		this.memorySpaceId = 0;
		this.initial = 0L;
		this.max = 0L;
		this.used = 0L;
		this.memoryType = MemoryType.HEAP; 
	}
	
	public MemorySpace(int memorySpaceId, long initial, long max, long used, MemoryType memoryType) {
		this.memorySpaceId = memorySpaceId;
		this.initial = initial;
		this.max = max;
		this.used = used;
		this.memoryType = memoryType; 
	}
	
	public int getMemorySpaceId() {
		return memorySpaceId;
	}
	public long getInitial() {
		return initial;
	}
	public long getMax() {
		return max;
	}
	public long getUsed() {
		return used;
	}
	public MemoryType getMemoryType() {
		return memoryType;
	}
	
	public void setMemorySpaceId(int memorySpaceId) {
		this.memorySpaceId = memorySpaceId;
	}
	public void setInitial(long initial) {
		this.initial = initial;
	}
	public void setMax(long max) {
		this.max = max;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public void setMemoryType(MemoryType memoryType) {
		this.memoryType = memoryType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + initial);
		result = (int) (prime * result + max);
		result = prime * result + memorySpaceId;
		result = (int) (prime * result + used);
		result = prime * result + ((memoryType == null) ? 0 : memoryType.hashCode());
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
		MemorySpace other = (MemorySpace) obj;
		if (initial != other.initial)
			return false;
		if (max != other.max)
			return false;
		if (memorySpaceId != other.memorySpaceId)
			return false;
		if (used != other.used)
			return false;
		if (memoryType != other.memoryType)
			return false;		
		return true;
	}
	
	@Override
	public String toString() {
		return "MemorySpace [memorySpaceId=" + memorySpaceId + ", initial=" + initial + ", max=" + max + ", used="
				+ used + ", memoryType=" + memoryType + "]";
	}

}
