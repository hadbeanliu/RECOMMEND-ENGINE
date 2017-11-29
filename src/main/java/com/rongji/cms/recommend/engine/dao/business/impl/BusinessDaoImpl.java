package com.rongji.cms.recommend.engine.dao.business.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rongji.cms.recommend.engine.dao.business.BusinessDao;
import com.rongji.cms.recommend.engine.entity.RecmBusiness;
import com.rongji.rjskeleton.dao.CommonDaoImpl;

@Repository
public class BusinessDaoImpl extends CommonDaoImpl<RecmBusiness> implements BusinessDao{
	@Override
	public List<RecmBusiness> getByBizCode(String bizCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + getEntityName() + " t");
		sb.append(" where t.bizCode = ?0");
		return getQueryList(sb.toString(), RecmBusiness.class, bizCode);
	}
	
}