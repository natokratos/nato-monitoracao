package com.tivit.api.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart {
	private Map<Object,Object> map = null;
	private List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
	private List<Map<Object,Object>> dataPoints = new ArrayList<Map<Object,Object>>();
	
	public Chart() {
	}
	
	public void addDataPoint(String x, String y) {
		map = new HashMap<Object,Object>();
		map.put("x", x);
		map.put("y", y);
		dataPoints.add(map);
	}
	
	public void loadDataPoints() {
		list.add(dataPoints);		
	}
 
	public List<List<Map<Object, Object>>> getDataList() {
		return list;
	}
}
