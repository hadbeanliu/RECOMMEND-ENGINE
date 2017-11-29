package com.rongji.cms.recommend.engine.service.scenes;

import java.util.List;

import com.rongji.cms.recommend.engine.entity.RecmScenes;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.rjskeleton.service.CommonService;

public interface ScenesService extends CommonService<Scenes, RecmScenes> {
	
	public Scenes getBySsCode(String ssCode);
	
	public List<Scenes> getByBizCode(String bizCode);

}
