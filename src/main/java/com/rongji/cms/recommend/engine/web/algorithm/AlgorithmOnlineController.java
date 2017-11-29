package com.rongji.cms.recommend.engine.web.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rongji.cms.recommend.engine.entity.RecmAlgorithm_;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmService;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.web.controller.CrudAndPageByJsonController;
import com.rongji.rjskeleton.web.controller.datatables.PageEntity;
import com.rongji.rjskeleton.web.controller.datatables.PageInfo;
import com.rongji.rjskeleton.web.controller.datatables.request.DatatablesMetadata;

@Controller
@RequestMapping("/algorithm/online")
public class AlgorithmOnlineController extends CrudAndPageByJsonController<Algorithm> {
	@Autowired
	private AlgorithmService algorithmService;


	@Override
	protected CommonService<Algorithm, ?> getService() {
		return algorithmService;
	}
	
	private static PageInfo PAGE_INFO = new PageInfo();
	static{
		PAGE_INFO.setLength(5);
		PAGE_INFO.setCheckboxMode(false);
		PAGE_INFO.addColumn(RecmAlgorithm_.id, false, false, false, false);
	}
	@Override
	protected PageInfo getPageInfo() {
		return PAGE_INFO;
	}

	@Override
	protected PageEntity<Algorithm> getPage(DatatablesMetadata metadata) {
		String type = "online";
		return algorithmService.getByTypeAndDatatables(type, metadata);
	}
}
