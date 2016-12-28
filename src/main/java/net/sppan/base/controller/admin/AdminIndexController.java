package net.sppan.base.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sppan.base.controller.BaseController;

@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

	@RequestMapping(value = { "/index", "/" })
	public String index() {
		return "admin/index";
	}
}
