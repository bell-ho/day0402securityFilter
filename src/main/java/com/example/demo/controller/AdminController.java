package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.db.DBManager;

@Controller
public class AdminController {
	
	@GetMapping("/admin/member")
	public void listMember(Model model) {
		model.addAttribute("list",DBManager.selectMemberAll());
	}
}
