package net.sppan.base.controller.admin;

import java.util.List;

import net.sppan.base.controller.BaseController;
import net.sppan.base.service.IResourceService;
import net.sppan.base.vo.Navs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController{
	
	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping("/menu")
	@ResponseBody
	public List<Navs> menu(){
		List<Navs> list = resourceService.selectTree();
		return list;
	}
	

}
