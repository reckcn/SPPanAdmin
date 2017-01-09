package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Resource;

import org.springframework.stereotype.Repository;

@Repository
public interface IResourceDao extends IBaseDao<Resource, Integer> {

}
