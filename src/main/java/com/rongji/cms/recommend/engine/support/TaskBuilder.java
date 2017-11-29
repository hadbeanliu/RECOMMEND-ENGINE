package com.rongji.cms.recommend.engine.support;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmService;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmType;
import com.rongji.cms.recommend.engine.service.algorithm.impl.AlgorithmServiceImpl;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.cms.recommend.engine.vo.AlgorithmFlow;
import com.rongji.cms.recommend.engine.vo.Scenes;
import com.rongji.dfish.base.Utils;

public class TaskBuilder {

	private static Gson gson = new Gson();

	public static TaskConfig build(Scenes scenes, AlgorithmFlow flow) {
		
		String bizCode = scenes.getBizCode();
		String ssCode = scenes.getSsCode();
		
		Graph<Algorithm> DAG=gson.fromJson(flow.getAlgorithmFlow(), new TypeToken<Graph<Algorithm>>(){}.getType());
		
		Queue<Algorithm> queue=illegalFlow(DAG);
		if(queue==null)
			return null;
		
		TaskConfig taskConf = new TaskConfig();
		taskConf.setBizCode(bizCode);
		taskConf.setSsCode(ssCode);
		taskConf.setAlgorithmFlow(queue);
		return taskConf;
		
	}
	
	public static Queue<Algorithm> illegalFlow(Graph<Algorithm> graph){
		Queue<Algorithm> flows = new LinkedList<Algorithm>();
		
		for(Vertex v:graph.topoSort())
			flows.add((Algorithm)v.getLabel());		

		if (flows == null || flows.size() == 0)
			return null;

		Queue<Algorithm> queue = new LinkedList<Algorithm>();
		Algorithm first = flows.poll();
		if (!illegal(first))
			return null;
		queue.add(first);
		String tmpOutput = first.getOutput();
		while (!flows.isEmpty()) {
			Algorithm alg = flows.poll();
			if (!illegal(alg))
				return null;

			if (!tmpOutput.contains(alg.getInput()))
				throw new IllegalArgumentException("the input should be "
						+ alg.getInput() + " ,but " + tmpOutput + " found");
			tmpOutput = alg.getOutput();
			queue.add(alg);
		}
		return queue;
	}

	private static boolean illegal(Algorithm alg) {

		if (alg == null)
			return false;

		String input = alg.getInput();
		String output = alg.getOutput();
		String mainClass = alg.getMainClass();
		String jarPath = alg.getJarPath();

		if (Utils.isEmpty(mainClass) || Utils.isEmpty(output)
				|| Utils.isEmpty(input))
			return false;

		if (Utils.isEmpty(jarPath)
				&& !AlgorithmType.SYS_PRESET.equals(alg.getSource()))
			return false;

		return true;
	}

	/**
	 * @param args
	 */
	
	

	
	public static void main(String[] args) {

//		AlgorithmService service=new AlgorithmServiceImpl();
//			Gson g=new Gson();
//			Graph<Algorithm> alg=new Graph<Algorithm>();
//			
//			alg.addVertex(service.getOne("igm"));
//			alg.addVertex(service.getOne("ibcf"));
//			
//			alg.addEdge(0, 1);
//			g.toJson(alg);
		System.out.println("123232wd".matches("\\d*\\w*"));
	}
}

class Param{
	
	private String code;
	private Object value;
	private String desc;
	
	
	
	public Param() {
		super();
	}
	public Param(String code, Object value, String desc) {
		super();
		this.code = code;
		this.value = value;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
}

