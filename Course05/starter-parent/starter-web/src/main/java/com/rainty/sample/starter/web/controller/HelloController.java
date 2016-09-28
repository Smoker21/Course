package com.rainty.sample.starter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping("hello")
	public String hello(@RequestParam(value = "userName", required = false, defaultValue = "要你管") final String userName,
			final Model m) {
		m.addAttribute("user", userName);
		return "AssetTypeView";
	}

}
