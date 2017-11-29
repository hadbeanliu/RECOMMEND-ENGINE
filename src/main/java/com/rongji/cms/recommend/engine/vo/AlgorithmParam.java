package com.rongji.cms.recommend.engine.vo;

public class AlgorithmParam {
	private String code;
	private String val;
	private String desc;
	
	public AlgorithmParam() {
		super();
	}
	public AlgorithmParam(String code,String val,String desc){
		this.code = code;
		this.val = val;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
