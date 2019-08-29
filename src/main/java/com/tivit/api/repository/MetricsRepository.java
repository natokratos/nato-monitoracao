package com.tivit.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tivit.api.entity.Metrics;

public interface MetricsRepository extends JpaRepository<Metrics, Long> {
	public Metrics findByMetricId(int metricId);
	
	public List<Metrics> findAll();
}
