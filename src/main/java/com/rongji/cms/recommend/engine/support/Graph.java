package com.rongji.cms.recommend.engine.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rongji.cms.recommend.engine.vo.Algorithm;

public class Graph<E> {

	private int maxVertex = 15;
	private int nVertex = 0;
	private Vertex<Algorithm>[] nodes;
	private int[][] adjMat;

	public Graph() {

		nodes = new Vertex[maxVertex];
		adjMat = new int[maxVertex][maxVertex];

		for (int i = 0; i < maxVertex; i++)
			for (int j = 0; j < maxVertex; j++)
				adjMat[i][j] = 0;

	}

	public void addVertex(Algorithm label) {
		nodes[nVertex++] = new Vertex(label);

	}

	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
	}

	public Set<Vertex> getNeighbors(Vertex v) {

		Set<Vertex> nh = new HashSet<Vertex>();
		int index = getIndex(v);
		if (index == -1)
			return null;
		for (int i = 0; i < nVertex; i++)
			if (adjMat[index][i] == 1)
				nh.add(nodes[i]);
		
		return nh;

	}

	public Set<Integer> getNeighborEdges(Vertex v) {

		Set<Integer> nh = new HashSet<Integer>();
		int index = getIndex(v);
		if (index == -1)
			return null;
		for (int i = 0; i < nVertex; i++)
			if (adjMat[index][i] == 1)
				nh.add(i);

		return nh;

	}

	public void allUnVisited() {
		for (int i = 0; i < nVertex; i++)
			nodes[i].setState(VertexState.UNVISITED);
	}

	public void setState(Vertex v, VertexState state) {
		v.setState(state);
	}

	public int getIndex(Vertex v) {

		for (int i = 0; i < nVertex; i++)
			if (nodes[i] == v)
				return i;
		return -1;

	}

	public void dfsHandler(Vertex v, boolean checkCycle, List<Vertex> list) {

		Set<Vertex> neighbors = null;
		if (getIndex(v) == -1)
			throw new NullPointerException("顶点不存在");

		setState(v, VertexState.PASSED);

		neighbors = getNeighbors(v);

		for (Vertex neig : neighbors) {

			if (neig.getState() == VertexState.UNVISITED)
				dfsHandler(neig, checkCycle, list);
			else if (neig.getState() == VertexState.PASSED && checkCycle)
				throw new RuntimeException("存在一个环");

		}
		setState(v, VertexState.VISITED);
		list.add(v);
		// System.out.println(v.getLabel()+":"+v.getState());

	}

	public List<Vertex> topoSort() {
		List<Vertex> topu = new ArrayList<Vertex>();

		allUnVisited();

		for (int i = 0; i < nVertex; i++)
			if (nodes[i].getState() == VertexState.UNVISITED)
				dfsHandler(nodes[i], true, topu);

		Collections.reverse(topu);
		return topu;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Set<Integer> hm = new HashSet<Integer>();
		hm.add(1);
		hm.add(2);

		System.out.println(hm);

	}

}
