package com.rongji.cms.recommend.engine.support;

/**
 * 模版相关操作
 *
 * @since 2016-1-14
 * @author rjf
 *
 */
public class TempUtil {
	
	/**
	 * 获取模版路径
	 * 
	 * @param tempName
	 * @return
	 */
	public static String getTempPath(String tempName) {
		return RecEngineConfig.getBaseTplPath() + tempName;
	}

}
