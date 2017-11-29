package com.rongji.cms.recommend.engine.support;

import com.rongji.rjskeleton.support.config.RjskeletonPropertiesLoader;
import com.rongji.rjskeleton.support.config.RjskeletonConfig;
import com.rongji.rjskeleton.support.exception.SkeletonException;
import com.rongji.rjskeleton.support.exception.SkeletonStatusCodes;

/**
 * 项目配置
 *
 * @since 2015-6-9
 * @author rjf
 *
 */
public final class RecEngineConfig {

	private static RjskeletonPropertiesLoader CONFIG;
	private static boolean IS_DEV;
	
	/**
	 * 获取配置的值
	 * 
	 * @param key 配置项
	 * @return 默认值由系统提供，认为取到的值即为正确的值
	 */
	public static String getValue(String key) {
		if (IS_DEV) {
			return RjskeletonConfig.getValue(key);
		}
		String value = CONFIG.getProperty(key);
		if (value == null) {
			value = RjskeletonConfig.getValue(key);
		}
		return value;
	}
	
	/**
	 * 由spring设置，程序中无法再设置
	 * 
	 * @param config
	 */
	public void setConfig(RjskeletonPropertiesLoader config) {
		if (CONFIG != null) {
			throw new SkeletonException(SkeletonStatusCodes.CONFIG_ERROR);
		}
		CONFIG = config;
		IS_DEV = isDev();
	}
	
	/**
	 * 判断是否在开发环境
	 * 
	 * @return
	 */
	private boolean isDev() {
		return ! "production".equals(RjskeletonConfig.getProfile());
	}
	
	/**
	 * 获取模版的相对路径
	 * 
	 * @return
	 */
	public static String getBaseTplPath() {
		return getValue("temp.baseTplPath");
	}
}
