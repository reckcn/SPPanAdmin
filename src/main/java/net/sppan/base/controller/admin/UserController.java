package net.sppan.base.controller.admin;

import java.util.List;

import net.sppan.base.entity.User;
import net.sppan.base.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = {"/index","/"})
	public String index(){
		
		return "admin/user/index";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<User> list(){
		List<User> list = userService.selectList(null);
		return list;
	}
}
