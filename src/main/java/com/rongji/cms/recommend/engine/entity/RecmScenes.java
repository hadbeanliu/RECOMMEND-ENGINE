package com.rongji.cms.recommend.engine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rongji.cms.recommend.engine.support.TablePrefix;
import com.rongji.rjskeleton.support.Comment;
import com.rongji.rjskeleton.support.id.Sid;

@Entity
@Table(name = TablePrefix.NAME + "SCENES")
@Comment("场景表")
public class RecmScenes implements Sid {
	@Id
	@Column(name="ID")
    private String id;
	
	@Column(name="SS_CODE")
    private String ssCode;

	@Column(name="USER_ID")
    private String userId;

	@Column(name="BIZ_CODE")
    private String bizCode;

	@Column(name="SS_NAME")
    private String ssName;

	@Column(name="SS_ARGS")
    private String ssArgs;

	@Column(name="FLOW_NAME")
    private String flowName;

	@Column(name="STATUS")
    private Integer status;

	@Column(name="WEIGHT_ARGS")
    private String weightArgs;

	@Column(name="PERIOD")
    private String period;

	@Column(name="FLOW_CODE")
    private String flowCode;
	
	@Column(name="API_PARAM")
	private String apiParam;
	
	
	
	public String getApiParam() {
		return apiParam;
	}
	public void setApiParam(String apiParam) {
		this.apiParam = apiParam;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getSsName() {
		return ssName;
	}
	public void setSsName(String ssName) {
		this.ssName = ssName;
	}
	public String getSsArgs() {
		return ssArgs;
	}
	public void setSsArgs(String ssArgs) {
		this.ssArgs = ssArgs;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getWeightArgs() {
		return weightArgs;
	}
	public void setWeightArgs(String weightArgs) {
		this.weightArgs = weightArgs;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getFlowCode() {
		return flowCode;
	}
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
}