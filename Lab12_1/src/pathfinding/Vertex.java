package pathfinding;

import java.util.HashMap;
import pathfinding.WeightedGraph.Mode;

public class Vertex implements Node {
	
	private int index;
	private String name;
	private HashMap<Vertex, Double> neighbours = new HashMap<>();
	
	private boolean explored;
	private double distanceFromStartingNode;
	private Vertex previousNode;
	
	public Vertex(int index, String name) {
		this.index = index;
		this.name = name;
		this.explored = false;
		this.distanceFromStartingNode = Double.POSITIVE_INFINITY;
		this.previousNode = null;
	}
	
	public void reset() {
		
	}
	
	public void explore(Mode mode) {
		
	}
	
	public void addNeighbour(Node node, double distance) {
		
	}
	
	public boolean setDistanceAndPrevNode(double shortestDistanceCanddate, Node prevNodeCandidate) {
		
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex) o;
		return index == v.getIndex();
	}

	public HashMap<Vertex, Double> getNeighbours(){
		return neighbours;
	}
	
	public Node getPreviousNode() {
		return previousNode;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean getExplored() {
		return explored;
	}
	
	public String getName() {
		return name;
	}
	
	public double getDistanceFromStartingNode() {
		return distanceFromStartingNode;
	}
}
