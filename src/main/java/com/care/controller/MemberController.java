package com.care.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.member_service.*;
import com.care.template.Constant;

@Controller
public class MemberController {
	private MemberService ms;
	public MemberController() {
		System.out.println("MemberController ����");
		String configLocation = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext(configLocation);
		JdbcTemplate template = ctx.getBean("template",JdbcTemplate.class);
		Constant.template = template;
	}
	@RequestMapping("/index")
	public String index() {System.out.println("index ����");
		return "member/index";
	}
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	@RequestMapping(value="user_check", method=RequestMethod.POST)
	public String user_check(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		
		ms = new MUserCheckImpl();
		int result = ms.execute(model);
		if(result == 0 ) {
			//���� ���
			HttpSession session = request.getSession();
			session.setAttribute("userId", request.getParameter("id"));
			System.out.println("��Ʈ�� ���� ���� �մϴ� : "+session.getAttribute("userId"));
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	@RequestMapping("successLogin")
	public String successLogin() {
		return "member/successLogin";
	
	}
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			session.invalidate();
		}
		return "redirect:index";
	}
	@RequestMapping("memberInfo")
	public String memberInfo(Model model) {
		ms = new MemberInfoImpl();
		ms.execute(model);
		return "/member/memberInfo";
	}
	@RequestMapping("info")
	public String info(HttpServletRequest request ,Model model) {
		model.addAttribute("request",request);
		ms = new MemberDataImpl();
		ms.execute(model);
		return "member/info";
	}
	@RequestMapping("/register_form")
	public String register_form() {
		System.out.println("register_form ����");
		return "member/register";
	}
	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model model) {
		System.out.println("register ����");
		model.addAttribute("request",request);
		ms = new MemberRegisterImpl();
		int result = ms.execute(model);
		if(result == 0)
			return "redirect:login";
		else
			return "member/register";
	}
}











