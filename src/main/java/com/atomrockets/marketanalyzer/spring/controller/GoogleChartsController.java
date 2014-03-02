package com.atomrockets.marketanalyzer.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

@Controller
@RequestMapping("/gcharts")
public class GoogleChartsController {

	@RequestMapping(value = "/piechart", method = RequestMethod.GET)
	public String drawPieChart(ModelMap model) {
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "Mac", "Mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "Windows", "Windows");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "Linux", "Linux");
		Slice s4 = Slice.newSlice(15, Color.newColor("01A1DB"), "Others", "Others");
		
		PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);
		pieChart.setTitle("Google Pie Chart", Color.BLACK, 15);
		pieChart.setSize(720, 360);
		pieChart.setThreeD(true);
		
		model.addAttribute("pieURL", pieChart.toURLString());
		
		//this should be the name of the jsp
		return "GPieChart";
	}
}
