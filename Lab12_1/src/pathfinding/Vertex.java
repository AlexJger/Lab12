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
		//neighbours.clear();
		this.explored = false;
		this.distanceFromStartingNode = Double.POSITIVE_INFINITY;
		this.previousNode = null;
		
	}
	
	public void explore(Mode mode) {
		if(mode==Mode.CHEAPEST) {
			this.explored=true;
			for(Vertex v : neighbours.keySet()) {
				if(v.getExplored()==false && v.getDistanceFromStartingNode()>neighbours.get(v)+this.getDistanceFromStartingNode())
				v.setDistanceAndPrevNode((neighbours.get(v)+this.getDistanceFromStartingNode()), this);
			}
		}
		if(mode==Mode.SHORTEST) {
			this.explored=true;
			for(Vertex v : neighbours.keySet()) {
				if(v.getExplored()==false && v.getDistanceFromStartingNode()>this.getDistanceFromStartingNode())
					v.setDistanceAndPrevNode((this.distanceFromStartingNode+1), this);
			}
		}
	}
	
	public void addNeighbour(Node node, double distance) {
		neighbours.put((Vertex)node, distance);
	}
	
	public boolean setDistanceAndPrevNode(double shortestDistanceCanddate, Node prevNodeCandidate) {
		this.distanceFromStartingNode=shortestDistanceCanddate;
		this.previousNode= (Vertex) prevNodeCandidate;
		return true;
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
