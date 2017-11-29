package com.rongji.cms.recommend.engine.dao.scenes.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rongji.cms.recommend.engine.dao.scenes.ScenesDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgsApis;
import com.rongji.cms.recommend.engine.entity.RecmScenes;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.rjskeleton.dao.CommonDaoImpl;

@Repository
public class ScenesDaoImpl extends CommonDaoImpl<RecmScenes> implements ScenesDao {

	@Override
	public RecmScenes selectBySsCode(String ssCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + getEntityName() + " t ");
		sb.append(" where t.ssCode = ?0");
		return getQueryOne(sb.toString(), RecmScenes.class, ssCode);
	}

	@Override
	public List<RecmScenes> selectByBizCode(String bizCode) {
		StringBuilder sb=new StringBuilder();
		sb.append("FROM " + getEntityName() + " t ");
		sb.append(" where t.bizCode = ?0");
		return getQueryList(sb.toString(),RecmScenes.class, bizCode);
	}

}
