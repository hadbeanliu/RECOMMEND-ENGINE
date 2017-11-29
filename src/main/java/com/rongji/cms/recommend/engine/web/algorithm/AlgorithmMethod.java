package com.rongji.cms.recommend.engine.web.algorithm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.cms.recommend.engine.vo.AlgorithmParam;
import com.rongji.dfish.base.Utils;

public class AlgorithmMethod {
	private static Gson gson = new Gson();
	
	public static Algorithm getParseFormRequest(HttpServletRequest request){
		Algorithm vo = new Algorithm();
		//获取基本信息
		String method = request.getParameter("method");
		String id = request.getParameter("id");
		String algCode = request.getParameter("algCode");
		String algDesc = request.getParameter("algDesc");
		String source = request.getParameter("source");
		String input = request.getParameter("input");
		String output = request.getParameter("output");
		String sysArgs = request.getParameter("sysArgs");
		String mainClass = request.getParameter("mainClass");
		String jarPath = request.getParameter("jarPath");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		//获取参数args
//		Map parameterMap = request.getParameterMap();
		List<AlgorithmParam> argsList = new ArrayList<AlgorithmParam>();
		String rowNum = request.getParameter("paramRow");
		int paramRow = Integer.parseInt(rowNum);
		for(int i=0;i<paramRow;i++){
			String nameKey = "paramCode_"+i;
			String valueKey = "paramValue_"+i;
			String descKey = "paramDesc_"+i;
			String code = request.getParameter(nameKey);
			String value = request.getParameter(valueKey);
			String desc = request.getParameter(descKey);
			if(Utils.notEmpty(code)&&Utils.notEmpty(value)){
				AlgorithmParam customProps = new AlgorithmParam(code,value,Utils.notEmpty(desc)?desc:"");
				argsList.add(customProps);
			}
		}
		vo.setAlgCode(algCode);
		vo.setAlgDesc(algDesc);
		vo.setArgs(gson.toJson(argsList));
		vo.setInput(input);
		vo.setJarPath(jarPath);
		vo.setMainClass(mainClass);
		vo.setOutput(output);
		vo.setSource(source);
		vo.setStatus(Utils.notEmpty(status)?Integer.parseInt(status):1);
		if(method.equals("NEW")){
			vo.setSysArgs(vo.getArgs());
		}else{
			vo.setId(id);
			vo.setSysArgs(sysArgs);
		}
		vo.setType(type);
		return vo;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
