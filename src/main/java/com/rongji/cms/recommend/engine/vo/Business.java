package com.rongji.cms.recommend.engine.vo;

import java.util.Date;

import javax.ws.rs.DefaultValue;

import com.rongji.rjskeleton.support.id.Sid;
import com.sun.istack.NotNull;

public class Business implements Sid {
    private String id;
    @NotNull
    private String bizCode;
    private String userId;
    @NotNull
    private String bizName;
    private Date createtsmp;
	@DefaultValue(value="USER_META_TABLE")
    private String userMetaTable = "USER_META_TABLE";
	@DefaultValue(value="USER_META_DESC")
    private String userMetaDesc = "USER_META_DESC";
	@DefaultValue(value="ITEM_META_TABLE")
    private String itemMetaTable = "ITEM_META_TABLE";
	@DefaultValue(value="ITEM_META_DESC")
    private String itemMetaDesc = "ITEM_META_DESC";
	@DefaultValue(value="RECOMMENDABLE_TABLE")
    private String recommendableTable = "RECOMMENDABLE_TABLE";
	@DefaultValue(value="USER_BEHAVIOR_TABLE")
    private String userBehaviorTable = "USER_BEHAVIOR_TABLE";
	@DefaultValue(value="1")
    private Boolean opencollect = true;
    @NotNull
    private String mqUser;
    @NotNull
    private String mqPassword;
    @NotNull
    private String mqHost;
    @NotNull
    private Integer mqPort;
    @NotNull
    private String mqDestination;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBizName() {
		return bizName;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	public Date getCreatetsmp() {
		return createtsmp;
	}
	public void setCreatetsmp(Date createtsmp) {
		this.createtsmp = createtsmp;
	}
	public String getUserMetaTable() {
		return userMetaTable;
	}
	public void setUserMetaTable(String userMetaTable) {
		this.userMetaTable = userMetaTable;
	}
	public String getUserMetaDesc() {
		return userMetaDesc;
	}
	public void setUserMetaDesc(String userMetaDesc) {
		this.userMetaDesc = userMetaDesc;
	}
	public String getItemMetaTable() {
		return itemMetaTable;
	}
	public void setItemMetaTable(String itemMetaTable) {
		this.itemMetaTable = itemMetaTable;
	}
	public String getItemMetaDesc() {
		return itemMetaDesc;
	}
	public void setItemMetaDesc(String itemMetaDesc) {
		this.itemMetaDesc = itemMetaDesc;
	}
	public String getRecommendableTable() {
		return recommendableTable;
	}
	public void setRecommendableTable(String recommendableTable) {
		this.recommendableTable = recommendableTable;
	}
	public String getUserBehaviorTable() {
		return userBehaviorTable;
	}
	public void setUserBehaviorTable(String userBehaviorTable) {
		this.userBehaviorTable = userBehaviorTable;
	}
	public Boolean getOpencollect() {
		return opencollect;
	}
	public void setOpencollect(Boolean opencollect) {
		this.opencollect = opencollect;
	}
	public String getMqUser() {
		return mqUser;
	}
	public void setMqUser(String mqUser) {
		this.mqUser = mqUser;
	}
	public String getMqPassword() {
		return mqPassword;
	}
	public void setMqPassword(String mqPassword) {
		this.mqPassword = mqPassword;
	}
	public String getMqHost() {
		return mqHost;
	}
	public void setMqHost(String mqHost) {
		this.mqHost = mqHost;
	}
	public Integer getMqPort() {
		return mqPort;
	}
	public void setMqPort(Integer mqPort) {
		this.mqPort = mqPort;
	}
	public String getMqDestination() {
		return mqDestination;
	}
	public void setMqDestination(String mqDestination) {
		this.mqDestination = mqDestination;
	}
}