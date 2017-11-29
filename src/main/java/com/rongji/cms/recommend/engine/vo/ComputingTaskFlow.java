package com.rongji.cms.recommend.engine.vo;

import java.util.Queue;

public class ComputingTaskFlow {
	
	private String bizCode;
	
	private String ssCode;
	
	private Queue<ComputingTask> algorithmFlow;
	
	private String configId;
	
	
	public String getBizCode() {
		return bizCode;
	}



	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}



	public String getSsCode() {
		return ssCode;
	}



	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}



	public Queue<ComputingTask> getAlgorithmFlow() {
		return algorithmFlow;
	}



	public void setAlgorithmFlow(Queue<ComputingTask> algorithmFlow) {
		this.algorithmFlow = algorithmFlow;
	}



	public String getConfigId() {
		return configId;
	}



	public void setConfigId(String configId) {
		this.configId = configId;
	}



}
