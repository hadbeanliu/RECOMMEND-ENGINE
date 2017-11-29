package com.rongji.cms.recommend.engine.vo;

import javax.persistence.Column;

import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmType;
import com.rongji.rjskeleton.support.id.Sid;
import com.sun.istack.NotNull;

public class Algorithm implements Sid {
    private String id;
    @NotNull
    @Column(unique=true)
    private String algCode;
    private String userId;
    private String name;
    private String args;
    private Integer status;
    private String algDesc;
    private String source;
    private String input;
    private String output;
    private String sysArgs;
    private String mainClass;
    private String jarPath;
/*    算法类型*/
	private String type;
	/*算法类别*/
	private String category;
	/** 获取算法类型名称*/
	private String typeDesc;
	/** 获取状态名称
	* String[] 第一个字符串是datatable里面用到，第二个字符串是form表单里面用到的
	*/
	private String[] stDesc;//
	/** 获取来源名称*/
	private String sourceDesc;//
	public String getId() {
		return algCode;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlgCode() {
		return algCode;
	}
	public void setAlgCode(String algCode) {
		this.algCode = algCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlgDesc() {
		return algDesc;
	}
	public void setAlgDesc(String algDesc) {
		this.algDesc = algDesc;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getSysArgs() {
		return sysArgs;
	}
	public void setSysArgs(String sysArgs) {
		this.sysArgs = sysArgs;
	}
	public String getMainClass() {
		return mainClass;
	}
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	public String getJarPath() {
		return jarPath;
	}
	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return AlgorithmType.getDesc(type);
	}
	public String[] getStDesc() {
		return AlgorithmType.getStatusDesc(status);
	}
	public String getSourceDesc() {
		return AlgorithmType.getReSourceDesc(source);
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
	
	
	
	
}