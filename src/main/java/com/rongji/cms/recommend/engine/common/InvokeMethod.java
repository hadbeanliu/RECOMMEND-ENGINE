package com.rongji.cms.recommend.engine.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.rongji.dfish.base.Utils;

public class InvokeMethod {
	public static String POST = "post";
	public static String GET = "get";
	/**
	 * 
	 * @param uri
	 * @param postContent
	 * @param method
	 * @param timeOut
	 * @return
	 * @throws Exception
	 */
	public static String doInvoke(String uri, List<NameValuePair> contextParams,String method, long timeOut) throws Exception {
		HttpUriRequest reqMethod = null;
		if(Utils.notEmpty(method) && POST.equals(method.toLowerCase())){
			HttpPost postMethod = new HttpPost(uri);
			reqMethod = postMethod;
			postMethod.setEntity(new UrlEncodedFormEntity((List<? extends org.apache.http.NameValuePair>) contextParams,"UTF-8"));
		}else{
			for(int i = 0; i < contextParams.size(); i++){
				NameValuePair nvp = contextParams.get(i);
				String str = nvp.getName()+"="+nvp.getValue();
				if(i == 0){
					uri += "?" + str;
					continue;
				}
				uri += "&" + str;
			}
			HttpGet getMethod = new HttpGet(uri);
			reqMethod = getMethod;
		}

		String result = null;
		HttpClient client = new SSLClient();
		
		System.out.println(reqMethod.getURI());
		try {
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, (int) timeOut);
			HttpConnectionParams.setSoTimeout(params, (int) timeOut);
			HttpResponse response = client.execute(reqMethod);
			reqMethod.addHeader("Content-Type", "application/x-www-form-urlencoded");
			reqMethod.addHeader("Accept-Language", "zh-cn");
			reqMethod.addHeader("Accept-Encoding", "gzip, deflate");

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new java.net.ConnectException("response code " + response.getStatusLine().getStatusCode()
						+ " for URL " + uri);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			client.getConnectionManager().shutdown();
		}
		return result;
	}
	
	public static void main(String[] args){
		String uri = "http://www.mricezxu.com/engine/business/v_get/abc.json";
		List<org.apache.commons.httpclient.NameValuePair> contextParams = new ArrayList<NameValuePair>();
		contextParams.add(new NameValuePair("wd","杭州"));
		try {
			String res = doInvoke(uri, contextParams, "get", 30000);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
