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
		return "";
	}
	
	private Vertex nextNodeToExplore() {
		Vertex dummy = new Vertex(0, "fhhefbekrjf");
		return dummy;
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
				edges --;
			}
		}
		return adjacencyMatrix;
	}
	
	private ArrayList<Vertex> createGraphFromAdjacencyMatrix(double[][] adjMatrix, String[] names){
		//matrix durchgehen, für jeden index (siehe seitenlänge) neuer vertex mit element aus names(passend zu index)
		for(int i=0; i<adjMatrix.length; i++) {
			Vertex v = new Vertex(i, names[i]);
			
			//vertex --> neighbours hinzufügen; dann zu arraylist graph
			for(int j=0; j<adjMatrix.length; j++) {
				if(adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
					Vertex nb = new Vertex(j, names[j]);
					v.addNeighbour(nb, adjMatrix[i][j]);
				}
			}
			graph.add(v);
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
			HashSet<String> namesSet = nm.parseNames("C:\\Users\\Wolfsmond\\Documents\\Studium\\Medieninformatik_Studium\\2.Sem\\Informatik2\\Labs\\Lab12\\lab12-gitRepo\\Lab12\\WeightedGraphNames.txt");
			namesR = namesSet.toArray(new String[namesSet.size()]);
		} catch (Exception e){
			e.printStackTrace();

			//throw new FileNotFoundException("File not found");
		}
		//System.out.println(namesR[0]);
		return namesR;
	}
	
	public int getVertexCount() {
		return graph.size();
	}
	
	public int getEdgeCount() {
		return 0;
	}
	
	public enum Mode{
		CHEAPEST, SHORTEST
	}
	
}
