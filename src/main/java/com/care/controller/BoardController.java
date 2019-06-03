package com.care.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.board_service.BListServiceImpl;
import com.care.template.Constant;
import com.care.board_service.*;

@Controller
public class BoardController {

	private BoardService board;
	public BoardController() {
		System.out.println("BoardController 角青");
		String configLocation = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext(configLocation);
		JdbcTemplate template = ctx.getBean("template",JdbcTemplate.class);
		Constant.template = template;
	}
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list() 角青");
		board = new BListServiceImpl();
		board.execute(model);
		return "board/list";
	}
	@RequestMapping("/write_view")
	public String write_view() {
		return "board/write_view";
	}
	@RequestMapping(value="/write_save", method=RequestMethod.POST)
	public String write_save(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		board = new BWriteSaveServiceImpl();
		board.execute(model);
		return "redirect:list";
	}
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		model.addAttribute("request",request);
		board = new BContentViewServiceImpl();
		board.execute(model);
		return "board/content_view";
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(HttpServletRequest request,Model model) {
		model.addAttribute("request",request);
		board=new BModifyServiceImpl();
		board.execute(model);
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,Model model) {
		model.addAttribute("request",request);
		board=new BDeleteServiceImpl();
		board.execute(model);
		return "redirect:list";
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("reply_view 角青");
		model.addAttribute("request",request);
		board = new BReplyViewServiceImpl();
		board.execute(model);
		return "board/reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("reply 角青");
		model.addAttribute("request",request);
		board = new BReplyServiceImpl();
		board.execute(model);
		return "redirect:list";
	}
}
