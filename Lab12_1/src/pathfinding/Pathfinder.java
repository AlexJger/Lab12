package pathfinding;
import pathfinding.WeightedGraph.Mode;

public class Pathfinder {
	
	public static void main(String args[]) {
		
		double[][] adjMat = getAdjacencyMatrix();
		
		WeightedGraph graph = new WeightedGraph(adjMat);
		
		WeightedGraph graph2 = new WeightedGraph(5, 10);
		
		System.out.println(graph);
		System.out.println(graph2);
		
		graph.dijkstra(0, 3, Mode.SHORTEST);
		/*System.out.println(graph.dijkstra(0, 4, Mode.CHEAPEST));
		System.out.println(graph.dijkstra(0, 4, Mode.SHORTEST));
		
		WeightedGraph graph2 = new WeightedGraph(30,65);
		System.out.println(graph2.dijkstraForRandomPoints(Mode.CHEAPEST));
		*/
	}
	
	private static double[][] getAdjacencyMatrix(){
		
		double[][] matrix = new double[5][5]; //matrix[index vertex 1][index vertex two]
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				matrix[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		
		matrix[0][1] = 1;
		matrix[1][0] = 1;
		
		matrix[0][3] = 3;
		matrix[3][0] = 3;

		matrix[1][2] = 6;
		matrix[2][1] = 6;

		matrix[3][4] = 2;
		matrix[4][3] = 2;

		matrix[4][1] = 4;
		matrix[1][4] = 4;
		
		return matrix;
	}
	
}
