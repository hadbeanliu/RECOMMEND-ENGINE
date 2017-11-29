package com.rongji.cms.recommend.engine.meta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.google.gson.Gson;

public class AlgorithmTypeConfig {
	
	private static String DEFAULT_MAPPING_FILE="algorithm-table-mapping.xml";
	
	private Map<String, List<TypeMeta>> offlineConfig=new HashMap<String, List<TypeMeta>>();
	private Map<String, List<TypeMeta>> onlineConfig=new HashMap<String, List<TypeMeta>>();
	
	public AlgorithmTypeConfig(){
		build();
	}
	
	public void build(){
		
		SAXBuilder build=new SAXBuilder();
		
		try {
			Document doc=build.build(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_MAPPING_FILE));
			
			Element root=doc.getRootElement();
			
			List<Element> types=root.getChildren("type");
			for(Element t:types){
				List<TypeMeta> meta=new ArrayList<AlgorithmTypeConfig.TypeMeta>();
				String type=t.getAttributeValue("name");
				String mode=t.getAttributeValue("mode");
				List<Element> category =t.getChildren("category");
				if(category.size()==0)
					continue;
				if(mode.equals("offline"))
				 offlineConfig.put(type, meta);
				else onlineConfig.put(type, meta);
				
				for(Element c:category){
					String name=c.getAttributeValue("name");
					String input=c.getAttributeValue("input");
					String output=c.getAttributeValue("output");
					meta.add(new TypeMeta(name, input, output));
					
				}
				
				
			}
			
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void setOfflineConfig(Map<String, List<TypeMeta>> offlineConfig) {
		this.offlineConfig = offlineConfig;
	}

	public void setOnlineConfig(Map<String, List<TypeMeta>> onlineConfig) {
		this.onlineConfig = onlineConfig;
	}

	public String toString(){
		
		
		
		return super.toString();
	}
	
	
	
	
public	class TypeMeta{
		private String name;
		private String input;
		private String output;
		
		public TypeMeta(String name,String input,String output){
			
			this.name=name;
			this.input=input;
			this.output=output;
			
		}
		
		public void setName(String name){
			this.name=name;
		}
		public void setOutput(String output){
			this.output=output;
		}
		public void setInput(String input){
			this.input=input;
		}
		
		public String getName(){
			return this.name;
		}
		public String getOutput(){
			return this.output;
		}
		public String getInput(){
			return this.input;
		}
		
		
	}
	
	
	public Map<String, List<TypeMeta>> getOfflineConfig(){
		return this.offlineConfig;
	}
	
	public Map<String, List<TypeMeta>> getOnlineConfig(){
		return this.onlineConfig;
	}
	

	public String getOutputByName(String name){
		
		for(List<TypeMeta> l:this.offlineConfig.values()){
			for(TypeMeta m:l){
				if(m.getName().equals(name))
					return m.getOutput();
				
			}
			
		}
		for(List<TypeMeta> l:this.onlineConfig.values()){
			for(TypeMeta m:l){
				if(m.getName().equals(name))
					return m.getOutput();
				
			}
			
		}
		
		
		return null;
	}
	
	public String getInputByName(String name){
		
		for(List<TypeMeta> l:this.offlineConfig.values()){
			for(TypeMeta m:l){
				if(m.getName().equals(name))
					return m.getInput();
				
			}
			
		}
		for(List<TypeMeta> l:this.onlineConfig.values()){
			for(TypeMeta m:l){
				if(m.getName().equals(name))
					return m.getInput();
			}
			
		}
				
		return null;
	}
	
	public List<TypeMeta> getCategoris(String type){
		if(offlineConfig.containsKey(type)){
			
			return offlineConfig.get(type);
		}
		if(onlineConfig.containsKey(type)){
			
			return onlineConfig.get(type);
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(1/(30*1.0/15374));

	}

}
