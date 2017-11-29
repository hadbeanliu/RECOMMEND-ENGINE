package com.rongji.cms.recommend.engine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rongji.cms.recommend.engine.support.TablePrefix;
import com.rongji.rjskeleton.support.Comment;
import com.rongji.rjskeleton.support.id.Sid;

@Entity
@Table(name = TablePrefix.NAME + "ALGORITHM_FLOW")
@Comment("算法流程表")
public class RecmAlgorithmFlow implements Sid {
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="FLOW_CODE")
    private String flowCode;
	
	@Column(name="NAME")
    private String name;
	
	@Column(name="USER_ID")
    private String userId;
	
	@Column(name="SOURCE")
    private String source;
	
	@Column(name="TYPE")
    private String type;
	
	@Column(name="FLOW_DESC")
    private String flowDesc;
	
	@Column(name="ALGORITHM_FLOW")
    private String algorithmFlow;
	
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
}