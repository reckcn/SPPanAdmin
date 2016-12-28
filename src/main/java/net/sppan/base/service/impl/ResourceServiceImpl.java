package net.sppan.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sppan.base.entity.Resource;
import net.sppan.base.mapper.ResourceMapper;
import net.sppan.base.service.IResourceService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

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
	public List<Resource> selectTree() {
		List<Resource> list = new ArrayList<Resource>();
		Wrapper<Resource> wrapper = new EntityWrapper<Resource>();
		wrapper.isNull("parent_id");
		wrapper.orderBy("sort", true);
		List<Resource> root = baseMapper.selectList(wrapper);
		for (Resource authMenu : root) {
			list.add(authMenu);
			list.addAll(getSubMenus(authMenu));
		}
		return list;
	}
	
	/**
	 * 获取菜单的子菜单
	 * @param root
	 * @return
	 */
	private List<Resource> getSubMenus(Resource root){
		List<Resource> list = new ArrayList<Resource>();
		Wrapper<Resource> wrapper = new EntityWrapper<Resource>();
		wrapper.eq("parent_id", root.getId());
		wrapper.orderBy("sort", true);
		List<Resource> one = baseMapper.selectList(wrapper);
		for (Resource authMenu : one) {
			list.add(authMenu);
			list.addAll(getSubMenus(authMenu));
		}
		return list;
	}

	
}
