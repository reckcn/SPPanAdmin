package net.sppan.base.mapper;

import java.util.List;

import net.sppan.base.entity.User;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 获取用户所有权限键
	 * @param username
	 * @return
	 */
	List<String> selectResource(@Param("username") String username);

}