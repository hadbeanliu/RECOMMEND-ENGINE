package com.rongji.cms.recommend.engine.dao.algsapis;

import java.util.List;

import com.rongji.cms.recommend.engine.entity.RecmAlgsApis;
import com.rongji.rjskeleton.dao.CommonDao;

public interface AlgsApisDao extends CommonDao<RecmAlgsApis> {
	public List<RecmAlgsApis> findBySsCode(String ssCode);
}
