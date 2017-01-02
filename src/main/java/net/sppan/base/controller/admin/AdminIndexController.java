package net.sppan.base.controller.admin;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

	@RequestMapping(value = { "/index", "/" })
	public String index(
			ModelMap map
			) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		map.put("currentUser", user);
		return "admin/index";
	}
}
