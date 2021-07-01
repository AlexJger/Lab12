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
		neighbours.clear();
		explored = false;
		distanceFromStartingNode = Double.POSITIVE_INFINITY;
		previousNode = null;
		
	}
	
	public void explore(Mode mode) {
		explored = true;
		if (mode == Mode.CHEAPEST) {
			for (Vertex v : neighbours.keySet()) {
				v.setDistanceAndPrevNode(distanceFromStartingNode + neighbours.get(v), getVertex() /*vertex itself, the one that was set to explored*/);
				
			}
		}
		if (mode == Mode.SHORTEST) {
			for (Vertex v : neighbours.keySet()) {
				v.setDistanceAndPrevNode(distanceFromStartingNode ++, getVertex()/*vertex itself, the one that was set to explored*/);
				
			}
		}
		
	}
	
	public void setExplored(boolean bool) {
		explored = bool;
	}
	
	public void addNeighbour(Node node, double distance) {
		neighbours.put((Vertex)node, distance);
	}
	
	public boolean setDistanceAndPrevNode(double shortestDistanceCandidate, Node prevNodeCandidate) {
		if(shortestDistanceCandidate < distanceFromStartingNode) {
			this.distanceFromStartingNode = shortestDistanceCandidate;
			this.previousNode = (Vertex) prevNodeCandidate;
			return true;
		}else {	
			return false;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		Vertex v = (Vertex) o;
		return index == v.getIndex();
	}

	//not sure if this is clever... it basically creates a new vertex, will it behave like the one it is?
	public Vertex getVertex() {
		Vertex v = new Vertex(getIndex(), getName());
		v.neighbours = getNeighbours();
		v.explored = getExplored();
		v.setDistanceAndPrevNode(getDistanceFromStartingNode(), getPreviousNode());
		return v;
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
	
	public String printNeighbours() {
		String result = "";
		for (Vertex v : neighbours.keySet()) {
			result = result + v.getName() + " (" + neighbours.get(v) + ")  ";
		}
		return result;
	}
	
	public double getDistanceFromStartingNode() {
		return distanceFromStartingNode;
	}
}
