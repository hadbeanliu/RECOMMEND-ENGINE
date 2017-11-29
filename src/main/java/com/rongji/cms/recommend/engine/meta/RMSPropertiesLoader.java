package com.rongji.cms.recommend.engine.meta;


public class RMSPropertiesLoader {
	
	private int computingPort;
	
	private String computingHost;

	public int getComputingPort() {
		return computingPort;
	}

	public void setComputingPort(int computingPort) {
		this.computingPort = computingPort;
	}

	public String getComputingHost() {
		return computingHost;
	}

	public void setComputingHost(String computingHost) {
		this.computingHost = computingHost;
	}

	public String openMQ(){
		
		return getURI()+"/itask/open-mq";
	}
	
	public String createTask(){
		
		return getURI()+"/itask/create/"+System.currentTimeMillis();
	}
	
	
	public String doRec(){
		
		return getURI()+"/taste/doRec";
	}
	
	public String getURI(){
		
		return "http://"+this.computingHost+":"+this.computingPort;
	}
}
