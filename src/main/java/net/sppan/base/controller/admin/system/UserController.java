package net.sppan.base.controller.admin.system;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.User;
import net.sppan.base.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/user/")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = { "/", "index" })
	public String index() {
		return "admin/user/index";
	}

	@RequestMapping(value = { "list" })
	@ResponseBody
	public Page list() {
		Page<User> page = userService.findAll(getPageRequest());
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public boolean edit(User user,String oper) {
		System.out.println(oper);
		System.out.println(user.toString());
		return true;
	}
}
