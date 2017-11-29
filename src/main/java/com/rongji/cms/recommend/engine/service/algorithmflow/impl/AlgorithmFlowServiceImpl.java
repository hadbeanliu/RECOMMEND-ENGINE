package com.rongji.cms.recommend.engine.service.algorithmflow.impl;

import org.springframework.stereotype.Service;

import com.rongji.cms.recommend.engine.dao.algorithmflow.AlgorithmFlowDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgorithmFlow;
import com.rongji.cms.recommend.engine.service.algorithmflow.AlgorithmFlowService;
import com.rongji.cms.recommend.engine.vo.AlgorithmFlow;
import com.rongji.rjskeleton.service.CommonServiceImpl;

@Service
public class AlgorithmFlowServiceImpl extends
		CommonServiceImpl<AlgorithmFlow, RecmAlgorithmFlow, AlgorithmFlowDao> implements
		AlgorithmFlowService {

}
