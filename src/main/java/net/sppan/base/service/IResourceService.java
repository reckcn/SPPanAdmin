package net.sppan.base.service;

import java.util.List;

import net.sppan.base.entity.Resource;
import net.sppan.base.service.support.IBaseService;
import net.sppan.base.vo.Navs;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IResourceService extends IBaseService<Resource> {
	
	/**
	 * 获取权限树
	 * @return
	 */
	public List<Navs> selectTree(Integer roleId);
	
}
