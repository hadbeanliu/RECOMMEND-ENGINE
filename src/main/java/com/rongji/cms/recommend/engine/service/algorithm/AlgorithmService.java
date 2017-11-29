package com.rongji.cms.recommend.engine.service.algorithm;

import com.rongji.cms.recommend.engine.entity.RecmAlgorithm;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.web.controller.datatables.PageEntity;
import com.rongji.rjskeleton.web.controller.datatables.request.DatatablesMetadata;

public interface AlgorithmService extends CommonService<Algorithm, RecmAlgorithm> {
	/**
	 * 根据算法类型获取翻页数据
	 * @param type 数据类型
	 * @param metadata
	 * @return
	 */
	PageEntity<Algorithm> getByTypeAndDatatables(String type, DatatablesMetadata metadata);
	/**
	 * 通过算法CODE找到该算法
	 * @param algCode
	 * @return
	 */
	public Algorithm getByAlgCode(String algCode);
}
