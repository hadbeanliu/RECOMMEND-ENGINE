package com.rongji.cms.recommend.engine.support;

import java.util.Set;

import com.rongji.cms.recommend.engine.vo.Algorithm;

public class AlgorithmWithEdge {
	
	
	private Algorithm alg;
	
	private Set<Integer> edges;
	

	public AlgorithmWithEdge(Algorithm alg, Set<Integer> edges) {
		super();
		this.alg = alg;
		this.edges = edges;
	}

	public Algorithm getAlg() {
		return alg;
	}

	public void setAlg(Algorithm alg) {
		this.alg = alg;
	}

	public Set<Integer> getEdges() {
		return edges;
	}

	public void setEdges(Set<Integer> edges) {
		this.edges = edges;
	}
	
	

}
