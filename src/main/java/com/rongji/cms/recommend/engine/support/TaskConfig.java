package com.rongji.cms.recommend.engine.support;

import java.util.Queue;

import com.rongji.cms.recommend.engine.vo.Algorithm;


public class TaskConfig {
	
	private String configId;
	
	private String ssCode;
	
	private String bizCode;
	
	private Queue<Algorithm> algorithmFlow;
	
	public String getSsCode() {
		return ssCode;
	}

	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public Queue<Algorithm> getAlgorithmFlow() {
		return algorithmFlow;
	}

	public void setAlgorithmFlow(Queue<Algorithm> algorithmFlow) {
		this.algorithmFlow = algorithmFlow;
	}
}
