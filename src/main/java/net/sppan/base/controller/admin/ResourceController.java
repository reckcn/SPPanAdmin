package net.sppan.base.controller.admin;

import java.util.ArrayList;
import java.util.List;

import net.sppan.base.controller.BaseController;
import net.sppan.base.entity.Resource;
import net.sppan.base.service.IResourceService;
import net.sppan.base.vo.Navs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController{
	
	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping("/menu")
	@ResponseBody
	public List<Navs> menu(){
		Wrapper<Resource> wrapper = new EntityWrapper<Resource>();
		List<Resource> list = resourceService.selectList(wrapper);
		List<Navs> navsList = new ArrayList<Navs>();
		Navs navs;
		for (Resource resource : list) {
			navs = new Navs();
			
		}
		return null;
	}
	

}
