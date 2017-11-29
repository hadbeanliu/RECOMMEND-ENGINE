package com.rongji.cms.recommend.engine.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientResource {
	private static enum MethodSignal {
		PUT, GET, POST
	}

	public String doSend(String uri,Map<String, String> queue,String METHOD){
		String msg="";
		HttpClient client=new HttpClient();
		
		HttpMethod method=null;
		MethodSignal met = null;
		try{
			met = Enum.valueOf(MethodSignal.class, METHOD.toUpperCase().trim());
		}catch(Exception e){
			throw new IllegalArgumentException("unknow request method:"+METHOD.toUpperCase().trim());
		}
		switch(met){
			
			case GET:method=new GetMethod(uri);break;
			case POST:method=new PostMethod(uri);break;
			case PUT:method=new PutMethod(uri);break;
		}
		
		if(queue!=null&&!queue.isEmpty()){
			NameValuePair[] data=new NameValuePair[queue.size()];
			int i=0;
 			for(Entry<String, String> kv:queue.entrySet()){
 				System.out.println(kv.getValue());
 				data[i]=new NameValuePair(kv.getKey(), kv.getValue());
 				i++;
 			}
 			
			if(MethodSignal.POST.equals(met)){
				((PostMethod)method).setRequestBody(data);
			}
			else{
				method.setQueryString(data);
			}
		}
	
		try {
			System.out.println(method.getURI());
			int status=client.executeMethod(method);
			
			if(status!=HttpStatus.SC_OK)
				return msg=method.getStatusText();
			
			msg=method.getResponseBodyAsString();
			
		}catch (HttpException e){
			System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		}catch (IOException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
		return msg;
	}
	
	private String post(String uri,Map<String,Object> params){
		HttpMethod method=new PostMethod(uri);
//		method.set
		return null;
	}

	/**
	 * @param args
	 * @throws URIException 
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws URIException, UnsupportedEncodingException, FileNotFoundException {

		HttpClientResource client=new HttpClientResource();
		
		String result=client.doSend("http://www.xxbiquge.com/5_5238/10126309.html", null, "get");
		String encode=new String(result.getBytes("ISO8859-1"),"utf-8");
		
		Pattern p=Pattern.compile("<a id=\"pager_next\" href=\"(.*).html[\\s\\S].*>.*</a>");
		Matcher matcher=p.matcher(encode);

		System.out.println("--------------");
		
		
		String[] r= encode.replaceAll("\\w", "").split("\n");
		for(String s:r){
			
			if(s.length()>200){
				System.out.println(s.replaceAll("</>","").replaceAll("&;&;&;&;", "").replaceAll("，|,", ",\n\n"));
				
			}
		}
		
		while(matcher.find()){
			System.out.println(matcher.group(1));
		}

	}
}
