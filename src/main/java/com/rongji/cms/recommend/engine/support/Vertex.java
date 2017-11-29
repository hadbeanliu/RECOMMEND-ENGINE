package com.rongji.cms.recommend.engine.support;

import java.util.Set;

import com.rongji.cms.recommend.engine.vo.Algorithm;

enum VertexState {
	VISITED, UNVISITED, PASSED;

}

public class Vertex <E>  {
	private int index;
	private Algorithm label;
	private VertexState state;
	private Set<Integer> edges;

	public Vertex(Algorithm label) {
		this.label = label;
		state=VertexState.UNVISITED;
	}
	
	public void setState(VertexState state){
		this.state=state;
	}
	
	public VertexState getState(){
		
		return state;
	}

	public E getLabel() {
		return (E)label;
	}

	public void setLabel(Algorithm label) {
		this.label = label;
	}
	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Set<Integer> getEdges() {
		return edges;
	}

	public void setEdges(Set<Integer> edges) {
		this.edges = edges;
	}
	
}