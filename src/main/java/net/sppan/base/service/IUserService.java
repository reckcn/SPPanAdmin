package net.sppan.base.service;

import net.sppan.base.entity.User;
import net.sppan.base.service.support.IBaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IUserService extends IBaseService<User> {

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);
	
}
