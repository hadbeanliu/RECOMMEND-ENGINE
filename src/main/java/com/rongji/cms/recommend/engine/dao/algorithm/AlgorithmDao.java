package com.rongji.cms.recommend.engine.dao.algorithm;

import java.util.List;

import com.rongji.cms.recommend.engine.entity.RecmAlgorithm;
import com.rongji.rjskeleton.dao.CommonDao;

public interface AlgorithmDao extends CommonDao<RecmAlgorithm> {
	/**
	 * 通过算法CODE查询算法
	 * @param algCode
	 * @return
	 */
	public List<RecmAlgorithm> getByAlgCode(String algCode);
}
