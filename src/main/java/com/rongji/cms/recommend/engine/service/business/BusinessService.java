package com.rongji.cms.recommend.engine.service.business;

import com.rongji.cms.recommend.engine.entity.RecmBusiness;
import com.rongji.cms.recommend.engine.vo.Business;
import com.rongji.rjskeleton.service.CommonService;

public interface BusinessService extends CommonService<Business, RecmBusiness> {

	public Business getByBizCode(String bizCode);

}
