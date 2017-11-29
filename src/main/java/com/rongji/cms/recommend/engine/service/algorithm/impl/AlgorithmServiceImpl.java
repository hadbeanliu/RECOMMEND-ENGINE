package com.rongji.cms.recommend.engine.service.algorithm.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rongji.cms.recommend.engine.dao.algorithm.AlgorithmDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgorithm;
import com.rongji.cms.recommend.engine.entity.RecmAlgorithm_;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmService;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmType;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.rjskeleton.service.CommonServiceImpl;
import com.rongji.rjskeleton.web.controller.datatables.PageEntity;
import com.rongji.rjskeleton.web.controller.datatables.request.DatatablesMetadata;

@Service
public class AlgorithmServiceImpl extends
		CommonServiceImpl<Algorithm, RecmAlgorithm, AlgorithmDao> implements
		AlgorithmService {
	@Autowired
	public AlgorithmDao algorithmDao;
	@Override
	public PageEntity<Algorithm> getByTypeAndDatatables(final String type,
			final DatatablesMetadata metadata) {
		return getByDatatables(metadata, new Specification<RecmAlgorithm>() {
			@Override
			public Predicate toPredicate(Root<RecmAlgorithm> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate tq = null;
				if(AlgorithmType.OFFLINE_ALGS.equals(type.toLowerCase())){
					Predicate tq1 = cb.equal(root.get(RecmAlgorithm_.type), AlgorithmType.OFFLINE_RECOMMEND);
					Predicate tq2 = cb.equal(root.get(RecmAlgorithm_.type), AlgorithmType.QUALITY_CONTROL);
					Predicate tq3 = cb.equal(root.get(RecmAlgorithm_.type), AlgorithmType.EFFECT);
					tq = cb.or(tq1,tq2,tq3);
				}else{
					Predicate tq1 = cb.equal(root.get(RecmAlgorithm_.type), AlgorithmType.ONLINE_ALGS);
					Predicate tq2 = cb.equal(root.get(RecmAlgorithm_.type), AlgorithmType.REAL_TIME_RECOMMEND);
					tq = cb.or(tq1,tq2);
				}
				Predicate[] where = createDatatablesQuery(metadata, root, query, cb);
				if (where != null && where.length > 0) {
					Predicate[] w = new Predicate[where.length+1];
					w[0] = tq;
					for (int i = 0; i < where.length; i++) {
						w[i+1] = where[i];
					}
					query.where(w);
					
				} else {
					query.where(tq);
				}
				return null;
			}
		});
	}

	@Override
	public Algorithm getByAlgCode(String algCode) {
		List<RecmAlgorithm> ralgs = algorithmDao.getByAlgCode(algCode);
		if(ralgs.size()>0){
			return convertToVo(ralgs.get(0));
		}
		return null;
	}

}
