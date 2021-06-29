import WeightedGraph.Mode;

public class Pathfinder {
	
	public static void main(String args[]) {
		
		double[][] adjMat = getAdjacencyMatrix();
		
		WeightedGraph graph = new WeightedGrahp(adjMat);
		System.out.println(graph);
		System.out.println(graph.dijkstra(0, 4, Mode.CHEAPEST));
		System.out.println(graph.dijkstra(0, 4, Mode.SHORTEST));
		
		WeightedGraph graph2 = new WeightedGraph(30,65);
		System.out.println(graph2.dijkstraForRandomPoints(Mode.CHEAPEST));
		
	}
	
	private static double[][] getAdjacencyMatrix(){
		
	}
	
}
