package com.rongji.cms.recommend.engine.service.algsapis;

import java.util.List;

import com.rongji.cms.recommend.engine.entity.RecmAlgsApis;
import com.rongji.cms.recommend.engine.vo.AlgsApis;
import com.rongji.rjskeleton.service.CommonService;

public interface AlgsApisService extends CommonService<AlgsApis, RecmAlgsApis> {
	public List<AlgsApis> findBySsCode(String ssCode);
}
