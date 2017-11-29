package com.rongji.cms.recommend.engine.service.algorithm;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmType {
	/** 系统预设*/
	public static final String SYS_PRESET = "0";
	/** 自定义*/
	public static final String USER_DEFINED = "1";
	/** 未知*/
	public static final String NOT_DEFINED = "2";
	
	/** 离线算法*/
	public static final String OFFLINE_ALGS = "offline";
	/** 质检算法 */
	public static final String ONLINE_ALGS = "online";
	
	/** 离线推荐算法 */
	public static final String OFFLINE_RECOMMEND = "离线推荐算法";
	/** 质检算法 */
	public static final String QUALITY_CONTROL = "Q";
	/** 效果算法 */
	public static final String EFFECT = "I";
	/** 在线插件 */
	public static final String ONLINE_PLUG_IN = "N";
	/** 实时推荐算法 */
	public static final String REAL_TIME_RECOMMEND = "实时推荐算法";

	//算法状态
	/** 审核未通过 */
	public static final int AUDIT_NOT_PASS = 0;
	/** 审核通过 */
	public static final int AUDIT_PASS = 1;
	/** 草稿 */
	public static final int DRAFT = 2;
	/** 审核中 */
	public static final int PENDING_AUDIT = 3;
	
	private static final Map<String, String> resouce = new HashMap<String, String>();
	private static final Map<String, String> titles = new HashMap<String, String>();
	private static final Map<Integer, String[]> algStatus = new HashMap<Integer, String[]>();
	
	static{
		resouce.put("0","系统预设");
		resouce.put("1","自定义");
	}
	static{
		titles.put("F", "离线推荐算法");
		titles.put("Q", "质检算法");
		titles.put("I", "效果算法");
		titles.put("N", "在线插件");
		titles.put("A", "实时推荐算法");
	}
	static{
		algStatus.put(0, new String[]{"审核未通过 ","不可使用"});
		algStatus.put(1, new String[]{"审核通过 ","正常可使用"});
		algStatus.put(2, new String[]{"草稿","未提交审核"});
		algStatus.put(3, new String[]{"审核中 ","待审核"});
	}
	
	/**
	 * 获取算法类型的描述
	 * 
	 * @param type 算法类型
	 * @return 不存在返回{@literal null}
	 */
	public static String getDesc(String type) {
		return titles.get(type);
	}
	/**
	 * 获取状态名称
	 * @param status
	 * @return String[] 第一个字符串是datatable里面用到，第二个字符串是form表单里面用到的
	 */
	public static String[] getStatusDesc(int status) {
		return algStatus.get(status);
	}
	
	/**
	 * 获取来源名称
	 * @param source
	 * @return
	 */
	public static String getReSourceDesc(String source){
		return resouce.get(source);
	}
}
