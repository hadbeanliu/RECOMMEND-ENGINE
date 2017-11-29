package com.rongji.cms.recommend.engine.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongji.cms.recommend.engine.dao.business.BusinessDao;
import com.rongji.cms.recommend.engine.entity.RecmBusiness;
import com.rongji.cms.recommend.engine.service.business.BusinessService;
import com.rongji.cms.recommend.engine.vo.Business;
import com.rongji.rjskeleton.service.CommonServiceImpl;

@Service
public class BusinessServiceImpl extends CommonServiceImpl<Business, RecmBusiness, BusinessDao> implements BusinessService {
	@Autowired
	public BusinessDao businessDao;
	
	@Override
	public Business getByBizCode(String bizCode) {
		List<RecmBusiness> ralgs = businessDao.getByBizCode(bizCode);
		if(ralgs.size()>0){
			return convertToVo(ralgs.get(0));
		}
		return null;
	}

}
