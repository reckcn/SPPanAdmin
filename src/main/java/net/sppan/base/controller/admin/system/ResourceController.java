package net.sppan.base.controller.admin.system;

import java.util.List;

import net.sppan.base.controller.BaseController;
import net.sppan.base.service.IResourceService;
import net.sppan.base.vo.ZtreeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController {
	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping("/tree/{roleId}")
	@ResponseBody
	public List<ZtreeView> tree(@PathVariable Integer roleId){
		List<ZtreeView> list = resourceService.tree(roleId);
		return list;
	}
}
