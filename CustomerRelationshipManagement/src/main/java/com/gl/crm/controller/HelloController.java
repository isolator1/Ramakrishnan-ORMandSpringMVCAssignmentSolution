package com.gl.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HelloController {

	@RequestMapping("/")
	public String showMainPage() {
		return "demo";
	}
}
