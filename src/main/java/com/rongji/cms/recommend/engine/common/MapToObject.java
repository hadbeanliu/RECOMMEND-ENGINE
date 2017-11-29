package com.rongji.cms.recommend.engine.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import com.rongji.cms.recommend.engine.vo.Algorithm;
/*
@auth dafa

*
*/
public class MapToObject {
	
	
	public static <T> T toObject(Map<String, Object> params,Class<T> clz ){
		for (Method method : clz.getMethods()) {
			System.out.println(method.getName());
		}
		for(Field field:clz.getDeclaredFields()){
			System.out.println(field.getName());
		}

		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		toObject(null, Algorithm.class);
	}

}
