package net.sppan.base.service.impl;

import java.util.Date;

import net.sppan.base.dao.IRoleDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Role;
import net.sppan.base.service.IRoleService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public IBaseDao<Role, Integer> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public void saveOrUpdate(Role role) {
		if(role.getId() != null){
			Role dbRole = find(role.getId());
			dbRole.setUpdateTime(new Date());
			dbRole.setName(role.getName());
			dbRole.setDescription(role.getDescription());
			dbRole.setUpdateTime(new Date());
			dbRole.setStatus(0);
			update(dbRole);
		}else{
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			save(role);
		}
	}
	
}
