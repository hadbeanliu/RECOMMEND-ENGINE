package com.rongji.cms.recommend.engine.web.algsapis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rongji.cms.recommend.engine.common.InvokeMethod;
import com.rongji.cms.recommend.engine.entity.RecmAlgsApis_;
import com.rongji.cms.recommend.engine.service.algsapis.AlgsApisService;
import com.rongji.cms.recommend.engine.support.TempUtil;
import com.rongji.cms.recommend.engine.vo.AlgsApis;
import com.rongji.dfish.base.Utils;
import com.rongji.rjskeleton.service.CommonService;
import com.rongji.rjskeleton.web.controller.CrudAndPageByJsonController;
import com.rongji.rjskeleton.web.controller.datatables.PageInfo;

@Controller
@RequestMapping("/api")
public class AlgsApisController extends CrudAndPageByJsonController<AlgsApis> {
	@Autowired
	public AlgsApisService algsApisService;
	
	private static PageInfo PAGE_INFO = new PageInfo();
	static{
		PAGE_INFO.setLength(5);
		PAGE_INFO.setCheckboxMode(false);
		PAGE_INFO.addColumn(RecmAlgsApis_.id, false, false, false, false);
	}
	
	@Override
	protected PageInfo getPageInfo() {
		return PAGE_INFO;
	}

	@Override
	protected CommonService<AlgsApis, ?> getService() {
		return algsApisService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String apiTest(HttpServletRequest request,Model model){
		String ssCode=(String) getRequest().getParameter("ssCode");
		String apiName=(String) getRequest().getParameter("apiName");
		String nmCid = (Utils.isEmpty(apiName)?"":apiName) + "," + (Utils.isEmpty(ssCode)?"":ssCode);
		model.addAttribute("NmCid",nmCid);
		return TempUtil.getTempPath("engine-debug");
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public List<AlgsApis> list(HttpServletRequest request){
		String ssCode = request.getParameter("ssCode");
		List<AlgsApis> list = algsApisService.findBySsCode(ssCode);
		return list;
	}

	@RequestMapping(value="/testApi/{id}")
	@ResponseBody
	public String testApi(@PathVariable String id){
		AlgsApis one = algsApisService.getOne(id);
		if(one==null){
			return "接口不存在";
		}
		String params = getRequest().getParameter("params");
		if(Utils.isEmpty(params)){
			params = one.getParams();
		}
		List<NameValuePair> contextParams = new ArrayList<NameValuePair>();
		if(params.startsWith("{")){
			Map<String, String> map = new Gson().fromJson(params, new TypeToken<Map<String,String>>(){}.getType());
			for (Map.Entry<String, String> entry : map.entrySet()) {  
				contextParams.add(new NameValuePair(entry.getKey(), entry.getValue()));
	        } 
		}else{
			String[] paramsSplit = params.split("&");
			for(String ps:paramsSplit){
				String[] kn = ps.split("=");
				contextParams.add(new NameValuePair(kn[0], kn[1]));
			}
		}
		try {
			String result = InvokeMethod.doInvoke(one.getUri(), contextParams, one.getMethod(), 30000);
			return result;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="/save")
	public AlgsApis save(){
		AlgsApis vo = new AlgsApis();
		vo.setMethod("POST");
		vo.setName("POST测试222");
		vo.setUri("http://abdddc.ads.com");
		vo.setParams("abcfsdfd=&defdfasddd=");
		vo.setSsCode("");
		return algsApisService.save(vo);
	}
}
