package com.tivit.api.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tivit.api.entity.Chart;
import com.tivit.api.entity.Metrics;
import com.tivit.api.repository.MetricsRepository;

@Service
public class DashBoardService {
	public static final int MAX_USERS_MOST_FOLLOWERS = 5;
	
	@Autowired
	MetricsRepository metricsRepository;
	
	public List<List<Map<Object, Object>>> getData() {
		Chart chart = new Chart();
		List<Metrics> metrics = metricsRepository.findAll();
		
		for(Metrics m : metrics) {
			chart.addDataPoint(String.valueOf(m.getThreading().getCurrentThreadCPUTime()), (new Date()).toString());
		}
		
		chart.loadDataPoints();

		return chart.getDataList();
	}
}
