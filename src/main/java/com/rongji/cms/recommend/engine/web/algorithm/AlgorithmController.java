package com.rongji.cms.recommend.engine.web.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rongji.cms.recommend.engine.entity.RecmAlgorithm_;
import com.rongji.cms.recommend.engine.meta.AlgorithmTypeConfig;
import com.rongji.cms.recommend.engine.meta.AlgorithmTypeConfig.TypeMeta;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmService;
import com.rongji.cms.recommend.engine.service.algorithm.AlgorithmType;
import com.rongji.cms.recommend.engine.support.TempUtil;
import com.rongji.cms.recommend.engine.support.config;
import com.rongji.cms.recommend.engine.vo.Algorithm;
import com.rongji.dfish.base.Utils;
import com.rongji.dfish.platform.ums.service.impl.UmsClientJsonImpl;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.support.mvc.RequestMappingDescription;
import com.rongji.rjskeleton.web.controller.CrudAndPageByJsonController;
import com.rongji.rjskeleton.web.controller.datatables.PageEntity;
import com.rongji.rjskeleton.web.controller.datatables.PageInfo;
import com.rongji.rjskeleton.web.controller.datatables.request.DatatablesMetadata;

@Controller
@RequestMapping("/algorithm")
public class AlgorithmController extends CrudAndPageByJsonController<Algorithm> {
	@Autowired
	private AlgorithmService algorithmService;
	
	private AlgorithmTypeConfig typeConfig=new AlgorithmTypeConfig();

	public UmsClientJsonImpl ums = config.getUmsCLient();

	@Override
	protected CommonService<Algorithm, ?> getService() {
		return algorithmService;
	}
	
	private static PageInfo PAGE_INFO = new PageInfo();
	static{
		PAGE_INFO.setLength(5);
		PAGE_INFO.setCheckboxMode(false);
		PAGE_INFO.addColumn(RecmAlgorithm_.id, false, false, false, false);
	}
	@Override
	protected PageInfo getPageInfo() {
		return PAGE_INFO;
	}

	@RequestMapping(method=RequestMethod.GET)
	@RequestMappingDescription("算法管理界面")
	public String index(Model model) {
		String type = getRequest().getParameter("type");
		System.out.println(type);
		type = Utils.notEmpty(type)?type:AlgorithmType.OFFLINE_ALGS;
		model.addAttribute("type", type);
		return TempUtil.getTempPath("engine-algorithm");
	}
	
	@RequestMapping(value="/list")
    @ResponseBody
    public List<Algorithm> list(){
		
		
		return this.algorithmService.getAll();
	}
	
			
//	@Override
//	protected PageEntity<Algorithm> getPage(DatatablesMetadata metadata) {
//		String type = "offline";
//		return algorithmService.getByTypeAndDatatables(type, metadata);
//	}
	@RequestMapping(value="/getIOTable/{category}")
	@ResponseBody
	public String[] getIOTable(@PathVariable("category")String category){
		String input=typeConfig.getInputByName(category);
		String output=typeConfig.getOutputByName(category);
		System.out.println(input+"--"+output);
		return new String[]{input,output};
	}

	@RequestMapping(value="/{type}/editAlg/{id}")
	public String editAlg(Model model, @PathVariable String type, @PathVariable String id) throws Exception {
		String userId = getCurrUserId();
		if(Utils.isEmpty(userId)){
			throw new Exception("用户为空，请先登入！");
		}
		boolean isAdmin = false;
		isAdmin=ums.isRoleMember("04000724", userId);
		List<TypeMeta> category=null;
		System.out.println(type);
		Set<String> types = null;
		if(AlgorithmType.OFFLINE_ALGS.equals(type.toLowerCase())){
			types=typeConfig.getOfflineConfig().keySet();
			category=typeConfig.getCategoris("离线推荐算法");
		}else{
//			types.add(new String[]{AlgorithmType.ONLINE_PLUG_IN,AlgorithmType.getDesc(AlgorithmType.ONLINE_PLUG_IN)});
			types=typeConfig.getOnlineConfig().keySet();
			category=typeConfig.getCategoris("实时推荐算法");
		}
		
		if(category==null)
			category=new ArrayList<AlgorithmTypeConfig.TypeMeta>();
		
		model.addAttribute("types", types);
		model.addAttribute("category",category);
		model.addAttribute("types", types);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("id", id);
		model.addAttribute("type", type);
		return TempUtil.getTempPath("engine-algorithm-edit");
	}
	
	@RequestMapping(value="/{type}/addAlg")
	public String addAlg(Model model, @PathVariable String type) throws Exception {
		String userId = getCurrUserId();
		if(Utils.isEmpty(userId)){
			throw new Exception("用户为空，请先登入！");
		}
		boolean isAdmin = false;
		isAdmin=ums.isRoleMember("04000724", userId);
		
		List<TypeMeta> category=null;
		System.out.println(type);
		Set<String> types = null;
		if(AlgorithmType.OFFLINE_ALGS.equals(type.toLowerCase())){
			types=typeConfig.getOfflineConfig().keySet();
			category=typeConfig.getCategoris("离线推荐算法");
		}else{
//			types.add(new String[]{AlgorithmType.ONLINE_PLUG_IN,AlgorithmType.getDesc(AlgorithmType.ONLINE_PLUG_IN)});
			types=typeConfig.getOnlineConfig().keySet();
			category=typeConfig.getCategoris("实时推荐算法");
		}
		
		if(category==null)
			category=new ArrayList<AlgorithmTypeConfig.TypeMeta>();
		
		model.addAttribute("types", types);
		model.addAttribute("category",category);
		model.addAttribute("isAdmin", isAdmin);
		
//		model.addAttribute("input",typeConfig.getInputByName(category.get(0).getInput()));
//		model.addAttribute("output",typeConfig.getOutputByName(category[0]));
		return TempUtil.getTempPath("engine-algorithm-add");
	}
	
	@RequestMapping(value = "/save")
	@Transactional
	public Map<String,Object> save(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = getCurrUserId();
		if(Utils.isEmpty(userId)){
			throw new Exception("用户为空，请先登入！");
		}
		String method = request.getParameter("method");
		if(method.equals("NEW")){
			Algorithm exist = algorithmService.getByAlgCode(request.getParameter("algCode"));
			if(exist==null){
				Algorithm vo = AlgorithmMethod.getParseFormRequest(request);
				if(Utils.notEmpty(vo.getAlgCode())){
					vo.setUserId(getCurrUserId());
					vo = algorithmService.save(vo);
					if(vo!=null){
						map.put("Code", "200");
						map.put("Msg", "保存成功");
						map.put("result", vo);
					}else{
						map.put("Code", "400");
						map.put("Msg", "保存失败");
						map.put("result", vo);
					}
				}else{
					vo = null;
					map.put("Code", "202");
					map.put("Msg", "保存失败,未设置算法CODE");
					map.put("result", vo);
				}
			}else{
				map.put("Code", "202");
				map.put("Msg", "保存失败,算法CODE已存在");
			}
		}else{
			Algorithm exist = algorithmService.getOne(request.getParameter("id"));
			Algorithm vo = AlgorithmMethod.getParseFormRequest(request);
			if(exist==null){
				vo.setUserId(getCurrUserId());
				vo.setSysArgs(vo.getArgs());
				vo = algorithmService.save(vo);
			}else{
				vo.setSysArgs(exist.getArgs());
				vo.setUserId(getCurrUserId());
				vo = algorithmService.update(vo);
			}
			if(vo!=null){
				map.put("Code", "200");
				map.put("Msg", "保存成功");
				map.put("result", vo);
			}else{
				map.put("Code", "400");
				map.put("Msg", "保存失败");
				map.put("result", vo);
			}
		}
		return map;
	}
}
