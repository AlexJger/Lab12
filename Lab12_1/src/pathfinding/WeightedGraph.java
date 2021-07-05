package pathfinding;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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
		if(start==end)return dijkstraForRandomPoints(mode);
		if(graph.get(start).getNeighbours().isEmpty()||graph.get(end).getNeighbours().isEmpty())
			return "There is no path from " + graph.get(start).getName() + " to " + graph.get(end).getName();
		
		reset();
		//set distance from start to 0
		graph.get(start).setDistanceAndPrevNode(0, null);
		Vertex v = graph.get(start);
		while(graph.get(end).getExplored()==false) {
			v.explore(mode);
			v = nextNodeToExplore();
			}
		
		return printsPath(start, end, mode);
	}
	
	private Vertex nextNodeToExplore() {
		double minValue = Double.POSITIVE_INFINITY;
		Vertex result=null;
		for(Vertex v : graph) {
			if(v.getExplored()==false && v.getDistanceFromStartingNode()<minValue && v.getDistanceFromStartingNode()!=0) {
				minValue=v.getDistanceFromStartingNode();
				result=v;
			}
		} 
		return result;
	}
	
	private double[][] createAdjacencyMatrix(int nodes, int edges){
		adjacencyMatrix = new double[nodes][nodes];
		for(int i=0; i<nodes; i++) {
			for(int j=0; j<nodes; j++) {
				adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		//edges random eintragen
		while(edges>0) {
			//random zelle der matrix [i][j]
			rand = new Random();
			int i = rand.nextInt(nodes);
			int j = rand.nextInt(nodes);
			if(adjacencyMatrix[i][j] == Double.POSITIVE_INFINITY && i != j ) {
				adjacencyMatrix[i][j] = (double) rand.nextInt(1000); //nextDouble() doesn't have a bound....
				adjacencyMatrix[j][i]=adjacencyMatrix[i][j];
				edges --;
			}
		}
		return adjacencyMatrix;
	}
	
	private ArrayList<Vertex> createGraphFromAdjacencyMatrix(double[][] adjMatrix, String[] names){
		//matrix durchgehen, für jeden index (siehe seitenlänge) neuer vertex mit element aus names(passend zu index)
		for(int i=0; i<adjMatrix.length; i++) {
			Vertex v = new Vertex(i, names[i]);
			graph.add(v);
		}
			//vertex --> neighbours hinzufügen; dann zu arraylist graph
		for(int i=0; i<adjMatrix.length; i++) {
			Vertex v = graph.get(i);
			for(int j=0; j<adjMatrix.length; j++) {
				if(adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
					v.addNeighbour(graph.get(j), adjMatrix[i][j]);
				}
			}
		}
		return graph;
	}
	
	private void reset() {
		for (Vertex v : graph) {
			v.reset();
		}
	}
	
	public String toString() {
		String result = "";
		for(Vertex v : graph) {
			result = result + v.getName() + ": " + v.printNeighbours() + "\n";
		}
		return result;
	}
	
	private static String[] parsePossibleNames()  {//throws FileNotFoundException
		NameParser nm = new NameParser();
		String[] namesR = null;
		try {
			HashSet<String> namesSet = nm.parseNames("src\\pathfinding\\WeightedGraphNames.txt");
			namesR = namesSet.toArray(new String[namesSet.size()]);
		} catch (FileNotFoundException e){
			e.printStackTrace();
			
			//throw new FileNotFoundException("File not found");
		}
		return namesR;
	}
	
	private String printsPath(int start, int end, Mode mode) {
		String resultStr = "";
		double pathlength = graph.get(end).getDistanceFromStartingNode();
		
		Vertex prev = (Vertex) graph.get(end);
		resultStr=prev.getName()+"\n";
		while ( prev.getPreviousNode() != null) {
			if(prev.getPreviousNode()!=null) {
			prev = (Vertex) prev.getPreviousNode(); 
			resultStr = prev.getName() + " --> " + resultStr;
			}
		}
		resultStr = "The "+mode.toString()+" path from " + graph.get(start).getName() + " to " + graph.get(end).getName() + ": \n" + resultStr;
		resultStr = resultStr + "The pathlength is: " + pathlength + ". \n";
		
		return resultStr;
	}

	//not used
//	public int getVertexCount() {
//		if(graph!=null) return graph.size();
//		else if(adjacencyMatrix!=null) return adjacencyMatrix.length;
//		else return 0;
//	}
//	
//	public int getEdgeCount() {
//		int counter=0;
//		if(graph!=null) {
//		for(Vertex v: graph) {
//			 HashMap<Vertex, Double> neigh = new HashMap<>();
//			 neigh = v.getNeighbours();
//			 counter+=neigh.size();
//			}
//		} else if(adjacencyMatrix!=null) {
//			for(double[] v : adjacencyMatrix) {
//				for(double weight : v) {
//					if(weight!=Double.POSITIVE_INFINITY) counter++;
//				}
//			}
//		}
//		return counter;
//	}
	
	public enum Mode{
		CHEAPEST, SHORTEST
	}
	
}
