package pathfinding;

import pathfinding.WeightedGraph.Mode;

public interface Graph {
	
	public int getVertexCount();
	public int getEdgeCount();
	
	public String dijkstra(int startIndex, int endIndex, Mode mode);
	public String dijkstraForRandomPoints(Mode mode);
}
