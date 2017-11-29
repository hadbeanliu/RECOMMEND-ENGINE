package com.rongji.cms.recommend.engine.service.algsapis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongji.cms.recommend.engine.dao.algsapis.AlgsApisDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgsApis;
import com.rongji.cms.recommend.engine.service.algsapis.AlgsApisService;
import com.rongji.cms.recommend.engine.service.scenes.ScenesService;
import com.rongji.cms.recommend.engine.vo.AlgsApis;
import com.rongji.rjskeleton.service.CommonServiceImpl;

@Service
public class AlgsApisServiceImpl extends CommonServiceImpl<AlgsApis, RecmAlgsApis, AlgsApisDao> implements AlgsApisService {
	@Autowired
	public AlgsApisDao dao;
	@Autowired
	public ScenesService scenesService;
	
	@Override
	public List<AlgsApis> findBySsCode(String ssCode) {
		List<AlgsApis> list = convertToVos(dao.findBySsCode(ssCode));
		return list;
	}

}
