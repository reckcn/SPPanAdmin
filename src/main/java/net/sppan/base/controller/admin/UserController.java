package net.sppan.base.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@RequestMapping(value = {"/index","/"})
	public String index(){
		
		return "admin/user/index";
	}
}
