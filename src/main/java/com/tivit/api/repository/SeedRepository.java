package com.tivit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tivit.api.domain.SeedType;
import com.tivit.api.entity.Seed;

public interface SeedRepository extends JpaRepository<Seed, Long> {
	public Seed findBySeedId(SeedType seedId);
}