package net.sppan.base.controller.admin;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Role;
import net.sppan.base.service.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController{
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = {"/index","/"})
	public String index(
			@RequestParam(required=false) Integer pn,
			ModelMap map){
		Page<Role> page = getPage(pn);
		roleService.selectPage(page);
		map.put("paging", page);
		return "admin/role/index";
	}

}
