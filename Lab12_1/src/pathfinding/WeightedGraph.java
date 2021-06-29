package pathfinding;
import java.util.ArrayList;
import java.util.Random;

//import sun.security.provider.certpath.Vertex;

public class WeightedGraph implements Graph {
	
	private double[][] adjacencyMatrix;
	private Random rand;
	private ArrayList<Vertex> graph = new ArrayList<Vertex>();
	private static String[] names = parsePossibleNames();
	
	public WeightedGraph(int nodes, int edges) {
		rand = new Random();
		adjacencyMatrix = createAdjacencyMatrix(nodes, edges);
		graph = createGraphFromAdjacencyMatrix(adjacencyMatrix, names);
	}
	
	public WeightedGraph(double[][] adjacencyMatrix) {
		rand = new Random();
		this.adjacencyMatrix = adjacencyMatrix;
		graph = createGraphFromAdjacencyMatrix(adjacencyMatrix, names);
	}
	
	@Override
	public String dijkstraForRandomPoints(Mode mode) {
		int start = rand.nextInt(graph.size());
		int end = rand.nextInt(graph.size());
		return dijkstra(start, end, mode);
	}
	
	public String dijkstra(int start, int end, Mode mode) {
		
	}
	
	private Vertex nextNodeToExplore() {
		
	}
	
	private double[][] createAdjacencyMatrix(int nodes, int edges){
		
	}
	
	private ArrayList<Vertex> createGraphFromAdjacencyMatrix(double[][] adjMatrix, String[] names){
		
	}
	
	private void reset() {
		
	}
	
	public String toString() {
		
	}
	
	private static String[] parsePossibleNames() {
		
	}
	
	public int getVertexCount() {
		
	}
	
	public int getEdgeCount() {
		
	}
	
	public enum Mode{
		CHEAPEST, SHORTEST
	}
	
}
