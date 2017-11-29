package com.rongji.cms.recommend.engine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rongji.cms.recommend.engine.support.TablePrefix;
import com.rongji.rjskeleton.support.Comment;
import com.rongji.rjskeleton.support.id.Sid;


@Entity
@Table(name = TablePrefix.NAME + "ALGS_APIS")
@Comment("API表")
public class RecmAlgsApis implements Sid {
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="NAME")
	private String name;
	@Column(name="METHOD")
	private String method;
	@Column(name="URI")
	private String uri;
	@Column(name="PARAMS")
	private String params;
	@Column(name="SS_CODE")
	private String ssCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
}
