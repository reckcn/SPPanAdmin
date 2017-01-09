package net.sppan.base.service.impl;

import net.sppan.base.dao.IUserDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.User;
import net.sppan.base.service.IUserService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public IBaseDao<User, Integer> getBaseDao() {
		return this.userDao;
	}
	
}
