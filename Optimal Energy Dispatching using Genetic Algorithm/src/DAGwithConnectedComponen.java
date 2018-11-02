//Name: Sajib Sen
//UID: 00658162
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DAGwithConnectedComponen {
	public int clock = 1;
	public static int count2 = 0;
	static Stack<Integer> st = new Stack<>();

	private Map<Integer, ArrayList<Integer>> adjListHashMap;
	public static int[] indegree;

	public void Indegree(int vertices) {
		DAGwithConnectedComponen.indegree = new int[vertices + 1];
	}

	
	public static boolean[] visited;

	public void Visited(int vertices) {
		DAGwithConnectedComponen.visited = new boolean[vertices + 1];
	}

	public static int[] extra;

	public void Extra(int vertices) {
		DAGwithConnectedComponen.extra = new int[vertices + 1];
	}

	public static int[] longestPath;

	public void LongestPath(int vertices) {
		DAGwithConnectedComponen.longestPath = new int[vertices + 1];
	}

	public DAGwithConnectedComponen(int vertices) {
		adjListHashMap = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i <= vertices; i++) {
			ArrayList<Integer> neighbour = new ArrayList<Integer>();
			adjListHashMap.put(i, neighbour);

		}

	}

	public void addEdge(int v, int w) {
		if (v > adjListHashMap.size() || w > adjListHashMap.size())
			return;
		else {
			(adjListHashMap.get(v)).add(w);
			indegree[w]++;
			extra[w]++;
		}

	}

	public ArrayList<Integer> getNeighbour(int v) {
		if (v > adjListHashMap.size())
			return null;
		return new ArrayList<Integer>(adjListHashMap.get(v));
	}
	public static int[] prevalue= new int [20];
	public static int[] postvalue= new int [20];
	public void Previsit(int v) {
		prevalue[v] = clock;
		clock++;
	}

	public void Postvisit(int v) {
		postvalue[v] = clock;
		clock++;
	}
	public static int[] active_lines= new int[14];
	public static String d="";
	public void initializeDag() {
	
		
		
		int source = 1, dest = 1;
		
		 Scanner dag = null;
			try {
				dag= new Scanner(new File("C:\\Users\\Sen\\eclipse-workspace\\GA_Project\\directed_graph_test.txt")); //text file location
			}
			catch(Exception e) {
				System.out.println("Could not find file");
			}
		int number_vertices = dag.nextInt();

		

		this.Indegree(number_vertices);// indegree values of vertices
		this.Extra(number_vertices);// copy of indegree values
		this.LongestPath(number_vertices);
		this.Visited(number_vertices);// longest path calculation
		

		
		
		
		int linesection;
		int itr=0;
		int random1= (int)(4+Math.random()*13);
		
		int random2= (int)(4+Math.random()*13);
		while(random2==random1) {
			random2= (int)(4+Math.random()*13);
		}

		int count=0;
		while (source != 0 || dest != 0) {
			
			source = dag.nextInt();
			dest = dag.nextInt();
			linesection=dag.nextInt();
			count++;
		
			if(!((random1==linesection||random2==linesection||linesection==0) )) //if linesection falls one of the random value
			{ 
				if(count%2!=0 && linesection!=0 ) {
				active_lines[itr]=linesection;
				itr++;
				}
				this.addEdge(source, dest);
			}
//			

		}
	
		
		
          
		int k1 = 0;
		for (int k = 0; k < number_vertices + 1; k++) {
			prevalue[k] = 0;
			postvalue[k] = 0;
			visited[k] = false;
		}
		
					visited[1] = true;
				
					st.add(1);
					this.Previsit(1);
					if (st.size()!=0) {
					
						k1 = st.peek();
					ArrayList<Integer> edges = this.getNeighbour(k1);
					
					
					
					if (edges.size() != 0) {
						for (int j = 1; j <= edges.size(); j++) {
							if (visited[edges.get(j - 1)]==false) {
								st.add(edges.get(j - 1));
								DFS(this, edges.get(j - 1), visited);
								
								
							}
							
							
						}
						this.Postvisit(1);
						
					st.pop();count2++;
					}
					else
						{
						this.Postvisit(1);
						
					st.pop();count2++;
					}
					
				}
				

		

	
	
		dag.close();

	}
	
public static  void DFS(DAGwithConnectedComponen g1, int v, boolean[] visited) {
		
		visited[v] = true;
		g1.Previsit(v);
		ArrayList<Integer> edges = g1.getNeighbour(v);
		
		
		if (edges.size() != 0) {
			for (int j = 1; j <= edges.size(); j++) {
				if (visited[edges.get(j - 1)]==false) {
					st.add(edges.get(j - 1));
					  DFS(g1, edges.get(j - 1), visited);
				}
				
				
				
				}
			g1.Postvisit(v);
			
			st.pop();
			count2++;

			}
		
		else
			{g1.Postvisit(v);
			
		st.pop();
		count2++;
		}
		return;

	}

	}

