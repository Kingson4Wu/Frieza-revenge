package com.kxw.restassured.server.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/test") 
public class RestAssuredController {

	
	
	@RequestMapping("/bb")
	public ModelAndView enterUserGroup() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bb");
		return mav;
	}
	
	
	@RequestMapping("/cc")
	@ResponseBody
	public Map queryUserGroup(@RequestParam(value = "groupName") String groupName,
			@RequestParam(value = "queryPage", defaultValue = "1") int queryPage) {
		Map result = new HashMap();

		
		return result;
	}
	
	@RequestMapping("/lotto")
	@ResponseBody
	public String lotto() {
		String json="{\"lotto\":{\"lottoId\":5,\"winning-numbers\":[2,45,34,23,7,5,3], \"winners\":[{\"winnerId\":23,\"numbers\":[2,45,34,23,3,5]},{\"winnerId\":54, \"numbers\":[52,3,12,11,18,22]}]}}";
		return json;
	}
	
	
	@RequestMapping("/price")
	@ResponseBody
	public String price() {
		String json="{\"price\":12.1199999999999992184029906638897955417633056640625 }";
		return json;
	}
	
	@RequestMapping("/product")
	@ResponseBody
	public String product() {
		String json="{\"lotto\":{\"lottoId\":5,\"winning-numbers\":[2,45,34,23,7,5,3], \"winners\":[{\"winnerId\":23,\"numbers\":[2,45,34,23,3,5]},{\"winnerId\":54, \"numbers\":[52,3,12,11,18,22]}]}}";
		return json;
	}
}
