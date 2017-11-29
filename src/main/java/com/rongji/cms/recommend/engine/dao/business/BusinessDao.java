package com.rongji.cms.recommend.engine.dao.business;

import java.util.List;

import com.rongji.cms.recommend.engine.entity.RecmBusiness;
import com.rongji.rjskeleton.dao.CommonDao;

public interface BusinessDao extends CommonDao<RecmBusiness> {
	public List<RecmBusiness> getByBizCode(String bizCode);
}
