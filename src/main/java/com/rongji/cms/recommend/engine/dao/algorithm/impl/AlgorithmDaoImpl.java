package com.rongji.cms.recommend.engine.dao.algorithm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rongji.cms.recommend.engine.dao.algorithm.AlgorithmDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgorithm;
import com.rongji.rjskeleton.dao.CommonDaoImpl;

@Repository
public class AlgorithmDaoImpl extends CommonDaoImpl<RecmAlgorithm> implements AlgorithmDao {

	@Override
	public List<RecmAlgorithm> getByAlgCode(String algCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + getEntityName() + " t");
		sb.append(" where t.algCode = ?0");
		return getQueryList(sb.toString(), RecmAlgorithm.class, algCode);
	}

}
