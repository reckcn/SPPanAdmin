package net.sppan.base.controller.admin;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.User;
import net.sppan.base.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = {"/index","/"})
	public String index(
			@RequestParam(required=false) Integer pn,
			ModelMap map){
		Page<User> page = getPage(pn);
		userService.selectPage(page);
		map.put("paging", page);
		return "admin/user/index";
	}
}
