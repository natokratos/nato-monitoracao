package com.tivit.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tivit.api.service.DashBoardService;

@Controller
@RequestMapping("/")
public class DashBoardController {
	@Autowired
	private DashBoardService dashboardService;
 
	@GetMapping("dashboard")
	public String showGraph(ModelMap modelMap) {
		List<List<Map<Object, Object>>> dataList = dashboardService.getData();
		modelMap.addAttribute("dataPointsList", dataList);
		return "Chart";
	}
}
