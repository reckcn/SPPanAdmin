package net.sppan.base.service.impl;

import net.sppan.base.common.utils.MD5Utils;
import net.sppan.base.entity.User;
import net.sppan.base.mapper.UserMapper;
import net.sppan.base.service.IUserService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 用户账户表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public User login(String username, String password) {
		User condition = new User();
		condition.setUserName(username);
		User user = baseMapper.selectOne(condition);
		
		Assert.notNull(user, "用户不存在");
		
		Assert.isTrue(MD5Utils.md5(password).equals(user.getPassword()), "密码不正确");
		return user;
	}
	
}
