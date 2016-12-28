package net.sppan.base.service.impl;

import net.sppan.base.entity.RoleUser;
import net.sppan.base.mapper.RoleUserMapper;
import net.sppan.base.service.IRoleUserService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色映射表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {
	
}
