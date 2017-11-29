package com.rongji.cms.recommend.engine.vo;

import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmType;
import com.rongji.rjskeleton.support.id.Sid;

public class AlgorithmFlow implements Sid {
	private String id;
    private String flowCode;
    private String name;
    private String source;
    private String sourceDesc;
    private String flowDesc;
    private String algorithmFlow;
    private String userId;
    private String type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlowCode() {
		return flowCode;
	}
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceDesc() {
		return AlgorithmType.getReSourceDesc(source);
	}
	public String getFlowDesc() {
		return flowDesc;
	}
	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}
	public String getAlgorithmFlow() {
		return algorithmFlow;
	}
	public void setAlgorithmFlow(String algorithmFlow) {
		this.algorithmFlow = algorithmFlow;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
}