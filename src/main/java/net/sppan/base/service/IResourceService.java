package net.sppan.base.service;

import java.util.List;

import net.sppan.base.entity.Resource;
import net.sppan.base.service.support.IBaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IResourceService extends IBaseService<Resource> {
	public List<Resource> selectTree();
}
