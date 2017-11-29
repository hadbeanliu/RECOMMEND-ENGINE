package com.rongji.cms.recommend.engine.dao.algsapis.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rongji.cms.recommend.engine.dao.algsapis.AlgsApisDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgsApis;
import com.rongji.rjskeleton.dao.CommonDaoImpl;

@Repository
public class AlgsApisDaoImpl extends CommonDaoImpl<RecmAlgsApis> implements AlgsApisDao {

	@Override
	public List<RecmAlgsApis> findBySsCode(String ssCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + getEntityName() + " t ");
		sb.append(" where t.ssCode = ?0");
		return getQueryList(sb.toString(), RecmAlgsApis.class, ssCode);
	}
	
}
