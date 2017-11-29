package com.rongji.cms.recommend.engine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rongji.cms.recommend.engine.support.TablePrefix;
import com.rongji.rjskeleton.support.Comment;
import com.rongji.rjskeleton.support.id.Sid;

@Entity
@Table(name = TablePrefix.NAME + "ALGORITHM")
@Comment("算法表")
public class RecmAlgorithm implements Sid {
	@Id
	@Column(name="ID")
    private String id;
	@Column(name="ALG_CODE")
    private String algCode;
	
	@Column(name="USER_ID")
    private String userId;
	
	@Column(name="NAME")
    private String name;
	
	@Column(name="ARGS")
    private String args;
	
	@Column(name="STATUS")
    private Integer status;
	
	@Column(name="ALG_DESC")
    private String algDesc;
	
	@Column(name="SOURCE")
    private String source;
	
	@Column(name="INPUT")
    private String input;
	
	@Column(name="OUTPUT")
    private String output;
	
	@Column(name="SYS_ARGS")
    private String sysArgs;
	
	@Column(name="MAIN_CLASS")
    private String mainClass;
	
	@Column(name="JAR_PATH")
    private String jarPath;
	
	@Column(name="TYPE")
	private String type;
	@Column(name="CATEGORY")
	private String category;
	
	public String getId() {
		return id;
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
}