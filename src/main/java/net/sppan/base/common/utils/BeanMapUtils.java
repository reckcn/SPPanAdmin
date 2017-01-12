package net.sppan.base.common.utils;

import java.util.HashSet;
import java.util.Set;

import net.sppan.base.entity.Resource;
import net.sppan.base.entity.Role;
import net.sppan.base.entity.User;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

/**
 * 用于处理hibernate双向关联导致循环，json序列号后是引用地址，js无法解析问题
 * @author SPPan
 *
 */
public class BeanMapUtils {
	
	/**
	 * 用户处理
	 * @param page
	 */
	public static void copy(Page<User> page){
		User resultUser;
		for (User user : page.getContent()) {
			resultUser = new User();
			BeanUtils.copyProperties(user, resultUser, "roles");
			Set<Role> resultRoleList = new HashSet<Role>();
			Role resultRole;
			for (Role role : user.getRoles()) {
				resultRole = new Role();
				BeanUtils.copyProperties(role, resultRole,"users","resources");
				Set<Resource> resultResourceList = new HashSet<Resource>();
				Resource resultResource;
				for (Resource resource : role.getResources()) {
					resultResource = new Resource();
					BeanUtils.copyProperties(resource, resultResource,"parent","roles","child");
					resultResourceList.add(resultResource);
				}
				resultRole.setResources(resultResourceList);
				resultRoleList.add(resultRole);
			}
			user.setRoles(resultRoleList);
		}
	}

}
