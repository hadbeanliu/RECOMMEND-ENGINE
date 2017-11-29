package com.rongji.cms.recommend.engine.web.scenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rongji.cms.recommend.engine.dao.algsapis.AlgsApisDao;
import com.rongji.cms.recommend.engine.entity.RecmAlgsApis;
import com.rongji.cms.recommend.engine.entity.RecmScenes_;
import com.rongji.cms.recommend.engine.meta.RMSPropertiesLoader;
import com.rongji.cms.recommend.engine.service.algsapis.AlgsApisService;
import com.rongji.cms.recommend.engine.service.scenes.ScenesService;
import com.rongji.cms.recommend.engine.support.TempUtil;
import com.rongji.cms.recommend.engine.vo.AlgsApis;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.support.mvc.RequestMappingDescription;
import com.rongji.rjskeleton.web.controller.CrudAndPageByJsonController;
import com.rongji.rjskeleton.web.controller.datatables.PageInfo;

@Controller
@RequestMapping("/scenes")
public class ScenesController extends CrudAndPageByJsonController<Scenes> {
	@Autowired
	private ScenesService scenesService;
	
	@Autowired
	private RMSPropertiesLoader pros;
	
	@Autowired
	private AlgsApisService apiService;

	private static PageInfo PAGE_INFO = new PageInfo();
	static{
		PAGE_INFO.setLength(5);
		PAGE_INFO.setCheckboxMode(false);
		PAGE_INFO.addColumn(RecmScenes_.id, false, false, false, false);
		
	}
	@Override
	protected PageInfo getPageInfo() {
		return PAGE_INFO;
	}
	
	@Override
	protected CommonService<Scenes, ?> getService() {
		return scenesService;
	}

	@RequestMapping(method=RequestMethod.GET)
	@RequestMappingDescription("场景界面")
	public String index(Model model) {
		return TempUtil.getTempPath("engine-myrecommend");
	}
	
	
	@RequestMapping("/createSns")
	public String index(Model model,@RequestParam("bizCode")String bizCode){
		
		return TempUtil.getTempPath("engine-recommendedscene");
		
	}
	
	@RequestMapping("/getSs/{bizCode}")
	public List<Scenes> getScenesBy(@PathVariable String bizCode){
		
		return this.scenesService.getByBizCode(bizCode);
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request){
		
		Gson g=new Gson();
		Map<String, String[]> params=request.getParameterMap();
		Scenes ss=new Scenes();
		StringBuffer apiParam=new StringBuffer();
		
		List<Map<String, String>> flowCode=new ArrayList<Map<String,String>>();
		List<Map<String, String>> sysArgs=new ArrayList<Map<String,String>>();
		
		List<AlgsApis> api=new ArrayList<AlgsApis>();
		AlgsApis dorec=new AlgsApis();
		AlgsApis ts=new AlgsApis();
		
		AlgsApis opMQ=new AlgsApis();
		
		
		
		for(String key:params.keySet()){
			System.out.println(key+":"+params.get(key)[0]);
			if(key.startsWith("paramName")){
				
				char last=key.charAt(key.length()-1);
				Map<String, String> env=new HashMap<String, String>();
				env.put("paramName", params.get(key)[0]);
				env.put("paramDesc", params.get("paramDesc_"+last)[0]);
				sysArgs.add(env);
				apiParam.append(params.get(key)[0]).append("=&");
				continue;
			}
			
			if(key.startsWith("pathflowCode")){
				
				char last=key.charAt(key.length()-1);
				Map<String, String> env=new HashMap<String, String>();
				
				String offCode=params.get("offflowCode_"+last)[0];
				String onCode=params.get("onflowCode_"+last)[0];
				env.put("pathflowCode", params.get(key)[0]);
				if(!offCode.equals("undefined"))
					env.put("offflowCode", offCode);
				
				if(!onCode.equals("undefined"))
					env.put("onflowCode", onCode);
				
				flowCode.add(env);
			}
			
			
			
		}
		
		String ssCode=params.get("ssCode")[0];
		String ssName=params.get("ssName")[0];
		String bizCode=params.get("bizCode")[0];
		ss.setApiParam(apiParam.toString());
		ss.setSsCode(ssCode);
		ss.setFlowCode(g.toJson(flowCode));
		ss.setSsArgs(g.toJson(sysArgs));
		ss.setSsName(ssName);
		ss.setBizCode(bizCode);
		
		dorec.setMethod("get");
		dorec.setParams(apiParam.toString());
		dorec.setUri(pros.doRec());
		dorec.setSsCode(ssCode);
		dorec.setName("获取推荐数据");
//		dorec.setId(System.currentTimeMillis()+"");
		
		ts.setMethod("get");
		ts.setParams("ssCode="+ssCode);
		ts.setUri("http://127.0.0.1:8080/engine/task/start.json");
		ts.setSsCode(ssCode);
		ts.setName("启动任务");
		
		opMQ.setMethod("get");
		opMQ.setParams("bizCode="+bizCode);
		opMQ.setUri("http://127.0.0.1:8080/engine/task/openMQ.json");
		opMQ.setSsCode(ssCode);
		opMQ.setName("启动日志收集");
		
//		ts.setId(System.currentTimeMillis()+"");
		api.add(dorec);
		api.add(ts);
		
		
		
		this.apiService.save(api);
		this.scenesService.save(ss);
		
		
				
		return "success";
		
	}
	

}
