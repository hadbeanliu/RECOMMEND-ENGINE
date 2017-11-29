package com.rongji.cms.recommend.engine.vo;

import java.util.Map;

public class ComputingTask {


	private String algCode;
	private String clazz;
	private String jar;
	private Map<String,String> args;
	private Map<String, String> defautArgs;
	public String getAlgCode() {
		return algCode;
	}
	public void setAlgCode(String algCode) {
		this.algCode = algCode;
	}
	
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getJar() {
		return jar;
	}
	public void setJar(String jar) {
		this.jar = jar;
	}
	public Map<String, String> getArgs() {
		return args;
	}
	public void setArgs(Map<String, String> args) {
		this.args = args;
	}
	public Map<String, String> getDefautArgs() {
		return defautArgs;
	}
	public void setDefautArgs(Map<String, String> defautArgs) {
		this.defautArgs = defautArgs;
	}
	
	
	

}
