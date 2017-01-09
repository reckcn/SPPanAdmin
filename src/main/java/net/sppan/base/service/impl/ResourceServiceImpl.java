package net.sppan.base.service.impl;

import net.sppan.base.dao.IResourceDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Resource;
import net.sppan.base.service.IResourceService;
import net.sppan.base.service.support.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements IResourceService {
	
	@Autowired
	private IResourceDao resourceDao;

	@Override
	public IBaseDao<Resource, Integer> getBaseDao() {
		return this.resourceDao;
	}
	
}
