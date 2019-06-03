package com.care.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.userlog_service.UserLogServiceImpl;
import com.care.userlog_service.UserService;


@Controller
public class UserLogController {
	private UserService us;
	/*//2�� Ǯ�� ����
	@RequestMapping("/userlog")
	public String userlog(Model model) {
		System.out.println("userlog ����");
		us = new UserLogServiceImpl();
		us.execute(model);
		return "userlog/userlog";
	}
	*/
	//3�� Ǯ��
	@RequestMapping("/userlog")
	public String userlog(Model model,HttpServletRequest request) {
		System.out.println("userlog ����");
		model.addAttribute("request",request);
		us = new UserLogServiceImpl();
		us.execute(model);
		return "userlog/userlog";
	}
}
