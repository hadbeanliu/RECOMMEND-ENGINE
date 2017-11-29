package com.rongji.cms.recommend.engine.web.taskManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.rongji.cms.recommend.engine.common.HttpClientResource;
import com.rongji.cms.recommend.engine.meta.ComputingSystem;
import com.rongji.cms.recommend.engine.meta.RMSPropertiesLoader;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmService;
import com.rongji.cms.recommend.engine.service.algorithmflow.AlgorithmFlowService;
import com.rongji.cms.recommend.engine.service.business.BusinessService;
import com.rongji.cms.recommend.engine.service.scenes.ScenesService;
import com.rongji.cms.recommend.engine.support.Graph;
import com.rongji.cms.recommend.engine.support.TaskBuilder;
import com.rongji.cms.recommend.engine.support.TaskConfig;
import com.rongji.cms.recommend.engine.support.TempUtil;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.cms.recommend.engine.vo.AlgorithmFlow;
import com.rongji.cms.recommend.engine.vo.AlgorithmParam;
import com.rongji.cms.recommend.engine.vo.Business;
import com.rongji.cms.recommend.engine.vo.ComputingTask;
import com.rongji.cms.recommend.engine.vo.ComputingTaskFlow;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.dfish.base.Utils;

@Controller
@RequestMapping(value = "/task")
public class TMRContraller {
	
	@Autowired
	private RMSPropertiesLoader pros;

	@Autowired
	private ScenesService scenesService;
	
	@Autowired
	private BusinessService bizService;

	@Autowired
	private AlgorithmFlowService algFlowService;
	
	private Gson gson=new Gson();
	
	
	@RequestMapping(value="/api")
	public String apiTest(HttpServletRequest request,Model model){
		
		String id=(String)request.getAttribute("id");
		String apiName=(String)request.getAttribute("apiName");
		
		if(id==null)
			return TempUtil.getTempPath("engine-debug");
		
		Scenes ss=scenesService.getOne(id);
		List<String> params=new ArrayList<String>();
		StringBuffer pathParam=new StringBuffer();
		for(String p:ss.getApiParam().split(","))
			pathParam.append(p.split(":")[0]).append("=&");
		
		String uri="";
		
		
		
		return TempUtil.getTempPath("engine-debug");
		
	}
	
	
	@RequestMapping(value="/openMQ")
	public String openMQ(@RequestParam("bizCode")String bizCode,Model model){
		
		Business biz=bizService.getOne(bizCode);
		
		String host=biz.getMqHost();
		int port =biz.getMqPort();
		String distiny=biz.getMqDestination();
		
		String user=biz.getMqUser();
		String pwd=biz.getMqPassword();
		
		Map<String, Object> mqConf=new HashMap<String, Object>();
		mqConf.put(ComputingSystem.MESSAGE_ACTIVITY_HOST, host);
		mqConf.put(ComputingSystem.MESSAGE_ACTIVITY_PORT, port);
		mqConf.put(ComputingSystem.MESSAGE_ACTIVITY_USER, user);
		mqConf.put(ComputingSystem.MESSAGE_ACTIVITY_PASSWORD, pwd);
		mqConf.put(ComputingSystem.MESSAGE_ACTIVITY_DIST, distiny);
		mqConf.put(ComputingSystem.COMPUTING_ID, biz.getBizCode());
		
		
		HttpClientResource client=new HttpClientResource();
		
		Map<String, String> queue=new HashMap<String, String>();
		
		queue.put("config", new Gson().toJson(mqConf));
		
		return client.doSend(pros.openMQ(), queue, "POST");
		
	}
	
//	@RequestMapping(value="/doRec")
//	public String doRec(HttpServletRequest request,Model model){
//		
//	
//		
//		Map<String, String> queue=new HashedMap();
//		
//		
//		
//		
//		return null;
//	}
	
	

	@RequestMapping(value="/start")
	public String start(@RequestParam(value="ssCode") String ssCode,Model model){
		System.out.println(ssCode);
		System.out.println("----------->>"+pros.createTask());
		
		String message="";
		try{
		Scenes scn=this.scenesService.getBySsCode(ssCode);
		
		System.out.println(scn==null);
		if(scn==null)
			return null;
//		switch(scn.getStatus()){
//			
//			case 0:message="任务正在执行中！"; break;
//		
//			case 1:message="任务正在等待队列中，无法启动任务";	break;
//		
//			case 2: message="该场景未配置算法流程";break;
//		
//			case 3: message="算法流程不合理"; break;
//		
//		}
		Gson g=new Gson(); 
		
		String flowCode=scn.getFlowCode();

		if(Utils.isEmpty(flowCode))
			message="该场景未配置算法流程";
		
		List<Map<String, String>> flows=g.fromJson(flowCode, new com.google.gson.reflect.TypeToken<List<Map<String, String>>>(){}.getType());
		if(flows.size()==0)
			return message="该场景未配置算法流程";
		String offflowCode=flows.get(0).get("offflowCode");
		AlgorithmFlow flow=this.algFlowService.getOne(offflowCode);
		ComputingTaskFlow taskFlow=new ComputingTaskFlow();
		
		System.out.println(flow);
		Queue<ComputingTask> tasks=new LinkedList<ComputingTask>();
		TaskConfig task=TaskBuilder.build(scn, flow);
		for(Algorithm alg:task.getAlgorithmFlow()){
			System.out.println("insert alg:::"+alg.getMainClass());
			ComputingTask metaTask=new ComputingTask();
			metaTask.setClazz(alg.getMainClass());
			metaTask.setJar(alg.getJarPath());
			metaTask.setAlgCode(alg.getAlgCode());
			System.out.println(alg.getArgs());
			List<AlgorithmParam> params=gson.fromJson(alg.getArgs(), new TypeToken<List<AlgorithmParam>>(){}.getType());
			Map<String, String> args=exectorArgs(params);
			List<AlgorithmParam> sysParams=gson.fromJson(alg.getSysArgs(), new TypeToken<List<AlgorithmParam>>(){}.getType());
			Map<String, String> sysArgs=exectorArgs(sysParams);
			metaTask.setDefautArgs(sysArgs);
			metaTask.setArgs(args);
			tasks.add(metaTask);
		}
		taskFlow.setAlgorithmFlow(tasks);
		taskFlow.setBizCode(scn.getBizCode());
		taskFlow.setSsCode(scn.getSsCode());
		ClientResource client=new ClientResource("http://192.168.16.111:9999/itask/start?tsmp="+System.currentTimeMillis());
		Representation rsp=client.post(gson.toJson(taskFlow));
		System.out.println("任务返回结果："+rsp.getText());
		
		}catch(Exception e){
			e.printStackTrace();
			message=e.getMessage();
			model.addAttribute("msg", message);
			
		}finally{
			
			System.out.println(message);
		}
		
		return message;
	}
	@Autowired
	private AlgorithmService service;
	
	@RequestMapping("/test")
	public void test(){
		
		Gson g=new Gson();
		Graph<Algorithm> alg=new Graph<Algorithm>();
		
		alg.addVertex(service.getOne("igm"));
		
		alg.addEdge(0, 1);
		System.out.println(g.toJson(alg));
		
		Graph<Algorithm> DAG=g.fromJson(g.toJson(alg), new TypeToken<Graph<Algorithm>>(){}.getType());

	}
	
	private Map<String,String> exectorArgs(List<AlgorithmParam> params){
		Map<String, String> args=new HashMap<String, String>();

		for(AlgorithmParam param:params){
			args.put(param.getCode(), param.getVal());
		}
		return args;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
	}

}
