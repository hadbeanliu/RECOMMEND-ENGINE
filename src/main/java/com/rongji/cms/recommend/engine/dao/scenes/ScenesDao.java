package com.rongji.cms.recommend.engine.dao.scenes;

import java.util.List;

import com.rongji.cms.recommend.engine.entity.RecmScenes;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.rjskeleton.dao.CommonDao;

public interface ScenesDao extends CommonDao<RecmScenes> {

	
	public RecmScenes selectBySsCode(String ssCode);
	
	public List<RecmScenes> selectByBizCode(String bizCode);
}
