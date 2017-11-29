package com.rongji.cms.recommend.engine.web.algorithmflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.rongji.cms.recommend.engine.entity.RecmAlgorithmFlow_;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmService;
import com.rongji.cms.recommend.engine.service.algorithmflow.AlgorithmFlowService;
import com.rongji.cms.recommend.engine.support.Graph;
import com.rongji.cms.recommend.engine.support.TaskBuilder;
import com.rongji.cms.recommend.engine.support.TempUtil;
import com.rongji.cms.recommend.engine.support.Vertex;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.cms.recommend.engine.vo.AlgorithmFlow;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.support.mvc.RequestMappingDescription;
import com.rongji.rjskeleton.web.controller.CrudAndPageByJsonController;
import com.rongji.rjskeleton.web.controller.datatables.PageInfo;

@Controller
@RequestMapping("/algorithmflow")
public class AlgorithmFlowController extends
		CrudAndPageByJsonController<AlgorithmFlow> {
	@Autowired
	private AlgorithmFlowService algorithmFlowService;
	
	@Autowired
	private AlgorithmService algorithmService;

	private static PageInfo PAGE_INFO = new PageInfo();
	static {
		PAGE_INFO.setLength(5);
		PAGE_INFO.setCheckboxMode(false);
		PAGE_INFO.addColumn(RecmAlgorithmFlow_.id, false, false, false, false);
	}

	@Override
	protected PageInfo getPageInfo() {
		return PAGE_INFO;
	}

	@Override
	protected CommonService<AlgorithmFlow, ?> getService() {
		return algorithmFlowService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@RequestMappingDescription("用户收货地址管理界面")
	public String index(Model model) {
		return TempUtil.getTempPath("engine-algorithm");
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<AlgorithmFlow> list(Model model) {

		List<AlgorithmFlow> list = algorithmFlowService.getAll();

		return list;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest req, Model model) {
		
		
		Gson g=new Gson();
		String flowCode=req.getParameter("flowCode");
		String flow=req.getParameter("flow");
		String desc=req.getParameter("desc");
		if(flow==null)
			throw new NullPointerException("flow cant be null");
		
		List<Vertex<Algorithm>> flows=g.fromJson(flow, new TypeToken<List<Vertex<Algorithm>>>() {}.getType());
		
		
		System.out.println(flows.size());
		Graph<Algorithm> graph=new Graph<Algorithm>();
		
		for(Vertex<Algorithm> v:flows){
			String algCode=v.getLabel().getAlgCode();
			String args=v.getLabel().getArgs();
			Algorithm alg=algorithmService.getOne(algCode);
			alg.setArgs(args);
			graph.addVertex(alg);
			int index=v.getIndex();
			Set<Integer> edges=v.getEdges();
			if(edges!=null)
			for(Integer e:edges){
				graph.addEdge(index, e);
			}
			
		}
		
		System.out.println(g.toJson(graph));
		if(TaskBuilder.illegalFlow(graph)!=null){
			
			AlgorithmFlow algFlow=new AlgorithmFlow();
			algFlow.setFlowCode(flowCode);
			algFlow.setFlowDesc(desc);
			algFlow.setAlgorithmFlow(g.toJson(graph));
			algFlow.setId(flowCode);
			algFlow.setType("offline");
			algorithmFlowService.save(algFlow);
		}
		
		
//		Map<String, String[]> list = req.getParameterMap();
//
//		Graph<Algorithm> flow = new Graph<Algorithm>();
//		int i = 0;
//		while (true) {
//			Map<String, String> param = getMapWithString(".*_" + i, list);
//			if (param.isEmpty())
//				break;
//			String algCode = param.get("algCode_" + i);
//			String index = param.get("index_" + i);
//			String[] dir = param.get("dir_" + i).split(",");
//			Map<String, Object> args = new HashMap<String, Object>();
//			args.put("", "");
//		}

		return "success";
	}

	// @RequestMapping(value="/get/{id}")
	// @ResponseBody
	// public String get

	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public List<Vertex> get(@PathVariable String id, Model model) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (id == null || "".equals(id)) {

			return null;
		}

		AlgorithmFlow flow = algorithmFlowService.getOne(id);
		model.addAttribute("desc", flow.getFlowDesc());
		System.out.println(flow.getFlowDesc());
		if (flow != null) {
			Graph<Algorithm> graph = new Gson().fromJson(flow.getAlgorithmFlow(), Graph.class);
			List<Vertex> topo = graph.topoSort();
			for (Vertex v : topo) {
				v.setEdges(graph.getNeighborEdges(v));
			}
			return topo;
		}

		return null;
	}

	@RequestMapping(value = "/{type}/edit/{id}")
	public String edit(@PathVariable String id, @PathVariable String type,
			Model model) {

		model.addAttribute("id", id);

		return TempUtil.getTempPath("engine-algorithm-flow-edit");
	}

	private Map<String, String> getMapWithString(String regex,
			Map<String, String[]> req) {

		Map<String, String> map = new HashMap<String, String>();
		for (Entry<String, String[]> entry : req.entrySet()) {
			if (entry.getKey().matches(regex)) {
				map.put(entry.getKey(), entry.getValue()[0]);
			}
		}

		return map;
	}
}
