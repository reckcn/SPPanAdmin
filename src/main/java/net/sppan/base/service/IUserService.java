package net.sppan.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.sppan.base.entity.User;
import net.sppan.base.service.support.IBaseService;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IUserService extends IBaseService<User, Integer> {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUserName(String username);

	/**
	 * 增加或者修改用户
	 * @param user
	 */
	void saveOrUpdate(User user);

	/**
	 * 给用户分配角色
	 * @param id 用户ID
	 * @param roleIds 角色Ids
	 */
	void grant(Integer id, String[] roleIds);

	/**
	 * 根据关键字获取分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<User> findAllByLike(String searchText, PageRequest pageRequest);

	/**
	 * 修改用户密码
	 * @param user
	 * @param oldPassword
	 * @param password1
	 * @param password2
	 */
	void updatePwd(User user, String oldPassword, String password1, String password2);

}
