package com.rongji.cms.recommend.engine.service.scenes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongji.cms.recommend.engine.dao.scenes.ScenesDao;
import com.rongji.cms.recommend.engine.entity.RecmScenes;
import com.rongji.cms.recommend.engine.service.scenes.ScenesService;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.rjskeleton.service.CommonServiceImpl;

@Service
public class ScenesServiceImpl extends
		CommonServiceImpl<Scenes, RecmScenes, ScenesDao> implements
		ScenesService {

	@Autowired
	private ScenesDao dao;
	
	@Override
	public Scenes getBySsCode(String ssCode) {
		
		return convertToVo(this.dao.selectBySsCode(ssCode));
	}

	@Override
	public List<Scenes> getByBizCode(String bizCode) {
		
		return convertToVos(this.dao.selectByBizCode(bizCode));
	}

}
