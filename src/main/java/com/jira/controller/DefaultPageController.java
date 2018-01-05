package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
  

@Controller
public class DefaultPageController {
	@RequestMapping("/")
    public String index(HttpServletRequest request) {
		request.getSession().removeAttribute("totalNum");
		 request.getSession().removeAttribute("successNum");
		 request.getSession().removeAttribute("failedTotalNum");
		 request.getSession().removeAttribute("failedList");
		 request.getSession().removeAttribute("noLdapInfo");

        return "myTest";
    }
}
