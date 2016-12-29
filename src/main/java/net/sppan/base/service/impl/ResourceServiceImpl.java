package net.sppan.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sppan.base.entity.Resource;
import net.sppan.base.mapper.ResourceMapper;
import net.sppan.base.service.IResourceService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import net.sppan.base.vo.Navs;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 资源表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper, Resource> implements IResourceService {
	
	@Override
	public List<Navs> selectTree(Integer roleId) {
		List<Navs> list = new ArrayList<Navs>();
		Wrapper<Resource> wrapper = new EntityWrapper<Resource>();
		wrapper.isNull("parent_id");
		wrapper.orderBy("sort", true);
		List<Resource> root = baseMapper.selectList(wrapper);
		wrapper.addFilter("id in(select s_id from tb_resources_role where r_id = {0})", roleId);
		Navs navs;
		for (Resource authMenu : root) {
			navs = new Navs();
			navs.setTitle(authMenu.getName());
			navs.setIcon(authMenu.getIcon());
			navs.setHref(authMenu.getSourceUrl());
			navs.setChildren(getSubMenus(authMenu,roleId));
			list.add(navs);
		}
		return list;
	}
	
	/**
	 * 获取菜单的子菜单
	 * @param root
	 * @return
	 */
	private List<Navs> getSubMenus(Resource root,Integer roleId){
		List<Navs> list = new ArrayList<Navs>();
		Wrapper<Resource> wrapper = new EntityWrapper<Resource>();
		wrapper.eq("parent_id", root.getId());
		wrapper.orderBy("sort", true);
		wrapper.addFilter("id in(select s_id from tb_resources_role where r_id = {0})", roleId);
		List<Resource> one = baseMapper.selectList(wrapper);
		Navs navs;
		for (Resource authMenu : one) {
			navs = new Navs();
			navs.setTitle(authMenu.getName());
			navs.setIcon(authMenu.getIcon());
			navs.setHref(authMenu.getSourceUrl());
			navs.setChildren(getSubMenus(authMenu,roleId));
			list.add(navs);
		}
		return list;
	}
	
}
