import java.util.ArrayList;
import java.util.List;

public class GraphImplementation implements Graph{
	private int[][]matrix;
	private int size;
	
	/**
	 * Constructor
	 * @param vertices This is the number of vertices
	 */
	public GraphImplementation(int vertices) {
		matrix = new int[vertices][vertices];
		size = vertices;
		
	}

	/**
	 * This method is to add a directed edge between two vertices from source vertex to target vertex
	 * @param v1 This is the source vertex
	 * @param v2 This is the target vertex
	 */
	@Override
	public void addEdge(int v1, int v2) throws Exception {
		//throw an exception when the vertices are out of bound
		if(v1 < 0 || v1 > size-1 || v2 < 0 || v2 > size-1)
			throw new Exception();
		else
			matrix[v1][v2] = 1;
		
	}

	/**
	 * This method is to print a list of vertices in topological ordering
	 * @return This returns the list
	 */
	@Override
	public List<Integer> topologicalSort() {
		List<Integer> topological = new ArrayList<Integer>();
		//create a new array to store each vertex's In-Degree (number of incoming edges)
		int sum[] = new int[size];
		
		for(int i = 0; i< size; i++)
			for(int j = 0; j < size; j++)
				sum[i] += matrix[j][i];
		
		//check if this graph is a DAG (Directed Acyclic Graph)
		if(findZero(sum) != -1) {
			for (int count = 0; count < size; count++) {
				//declare an variable to store the first vertex with In-Degree is 0
				int in_degree0 = findZero(sum);
				topological.add(in_degree0);
				sum[in_degree0] = -1;
				
				//Reduce In-Degree of all vertices adjacent to it by 1				
				for (int newSum = 0; newSum < size; newSum++)
					sum[newSum] -= matrix[in_degree0][newSum];
			}
		}else
			System.out.println("Topological Sorting is not possible fot this graph which is not a DAG (Directed Acyclic Graph).\n");
		
		return topological;
	}

	/**
	 * This method is to find the first vertex with In-Degree (number of incoming edges) is 0
	 * @param sum This is the array that contains each vertex's In-Degree
	 * @return This returns the index and it returns -1 when this graph is not a DAG
	 */
	private int findZero(int[] sum) {
		for(int i = 0; i < size; i++)
			if(sum[i] == 0)
				return i;
		
		return -1;
	}

	/**
	 * This method is to find a list of vertex IDs that are the destination of the edge originating from the source vertex
	 * @param vertex This is the source vertex
	 * @return This returns the list
	 */
	@Override
	public List<Integer> neighbors(int vertex) throws Exception {

		if(vertex < 0 || vertex > size)
			throw new Exception();
		
		List<Integer> neighbors = new ArrayList<Integer>();
		
		for(int i = 0; i < size; i++) {
			if(matrix[vertex][i] == 1)
				neighbors.add(i);					
		}
		
		return neighbors;
	}
	
	
}
