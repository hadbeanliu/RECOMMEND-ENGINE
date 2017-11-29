package com.rongji.cms.recommend.engine.web.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongji.cms.recommend.engine.entity.RecmBusiness_;
import com.rongji.cms.recommend.engine.service.business.BusinessService;
import com.rongji.cms.recommend.engine.support.TempUtil;
import com.rongji.cms.recommend.engine.vo.Business;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.support.mvc.RequestMappingDescription;
import com.rongji.rjskeleton.web.controller.CrudAndPageByJsonController;
import com.rongji.rjskeleton.web.controller.datatables.PageInfo;

@Controller
@RequestMapping("/business")
public class BusinessController extends CrudAndPageByJsonController<Business> {
	@Autowired
	private BusinessService businessService;

	private static PageInfo PAGE_INFO = new PageInfo();
	static{
		PAGE_INFO.addColumn(RecmBusiness_.bizCode, false, false, false, false);
	}
	@Override
	protected PageInfo getPageInfo() {
		return PAGE_INFO;
	}

	@Override
	protected CommonService<Business, ?> getService() {
		return businessService;
	}

	@RequestMapping(method=RequestMethod.GET)
	@RequestMappingDescription("业务界面")
	public String index(Model model) {
		return TempUtil.getTempPath("engine-myrecommend");
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<Business> list(){
		
		return businessService.getAll();
	}
	
	@RequestMapping(value="/{act}/save",method=RequestMethod.POST)
	@Transactional
	public Map<String,Object> save(@PathVariable String act,Business vo){
		Map<String,Object> map = new HashMap<String, Object>();
		if("edit".equals(act.toLowerCase())){
			vo = businessService.updateByFilter(vo, "bizName","mqUser","mqPassword","mqHost","mqPort","mqDestination");
			if(vo!=null){
				map.put("Code", "200");
				map.put("Msg", "更新成功");
				map.put("result", vo);
			}else{
				map.put("Code", "400");
				map.put("Msg", "更新失败");
				map.put("result", vo);
			}
		}else{
			Business exsit = businessService.getByBizCode(vo.getBizCode());
			if(exsit!=null){
				map.put("Code", "202");
				map.put("Msg", "保存失败,未设置算法CODE");
				map.put("result", vo);
			}else{
				vo.setCreatetsmp(new Date());
				vo = businessService.save(vo);
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
		}
		return map;
	}
	
}
