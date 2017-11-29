package com.rongji.cms.recommend.engine.vo;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.rongji.rjskeleton.support.id.Sid;

public class Scenes implements Sid {
    private String id;
    @Column(unique=true)
    private String ssCode;
    private String userId;
    private String bizCode;
    @Length(max=20,min=3)
    private String ssName;
    private String ssArgs;
    private String flowName;
    private Integer status=0;
    private String weightArgs;
    private String period="";
    private String flowCode;
    @NotBlank
    @NotNull
    private String apiParam;
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
	public String getApiParam() {
		return apiParam;
	}
	public void setApiParam(String apiParam) {
		this.apiParam = apiParam;
	}
	
	
}