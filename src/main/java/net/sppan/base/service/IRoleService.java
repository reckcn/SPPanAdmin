package net.sppan.base.service;

import net.sppan.base.entity.Role;
import net.sppan.base.service.support.IBaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IRoleService extends IBaseService<Role,Integer> {

	void saveOrUpdate(Role role);
	
}
