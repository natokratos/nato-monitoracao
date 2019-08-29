package com.tivit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tivit.api.domain.SeedType;

@Entity
@Table(name="Seed")
public class Seed {
	@Id
	@Enumerated(EnumType.ORDINAL)
	@Column(name="seedId")	
	private SeedType seedId;
	@Column(name="description")	
	private String description;
	@Column(name="value")	
	private int value;

	public Seed() {
		this.seedId = SeedType.EMPTY;
		this.description = "";
		this.value = 0;
	}
	
	public Seed(SeedType seedId, String description, int value) {
		this.seedId = seedId;
		this.description = description;
		this.value = value;
	}

	public SeedType getSeedId() {
		return seedId;
	}
	public String getDescription() {
		return description;
	}
	public int getValue() {
		return value;
	}
	
	public void setSeedId(SeedType seedId) {
		this.seedId = seedId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + seedId.ordinal();
		result = prime * result + value;
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
		Seed other = (Seed) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (seedId != other.seedId)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seeds [seedId=" + seedId + ", description=" + description + ", value=" + value + "]";
	}
	
}

