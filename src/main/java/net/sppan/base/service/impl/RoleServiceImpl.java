package net.sppan.base.service.impl;

import net.sppan.base.entity.Role;
import net.sppan.base.mapper.RoleMapper;
import net.sppan.base.service.IRoleService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
	
}
