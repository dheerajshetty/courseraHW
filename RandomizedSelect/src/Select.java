import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Select {
	public static void main(String[] args) throws Exception {
		File file = new File("kargerMinCut1.txt");
		Scanner scanner = new Scanner(file);
		Graph g = new Graph();
		int mincut = 0;
		int temp1 = 0;
		
		while(scanner.hasNextLine()) {
			Vertex v = new Vertex();
			String line = scanner.nextLine();
			
			String[] temp = line.split(" ");
			
			
			for (String string : temp) {
				v.adjacencyList.add(Integer.parseInt(string.trim()));
			}
			
//			v.adjacencyList.remove(0);
			
			g.vertices.add(v);
		}
		
		mincut = Rcontract(g);
		
		for (int i = 1; i < 100000; i++) {
			temp1 = Rcontract(g);
			if (temp1 < mincut) {
				mincut = temp1;
			}
		}
		
		System.out.println(mincut);
	}
	
	static int Rcontract(Graph g) {
		int count = 0;
		
		while(g.vertices.size() > 2) {
			g = reduce(g);
		}
		
		count = g.vertices.get(1).adjacencyList.size() - 1;
		return count;
	}
	
	static Graph reduce(Graph g) {
	
		int vertexStart = 0;
		int vertexEnd = 0;
		
		Random rand = new Random();
		
		vertexStart = rand.nextInt(g.vertices.size());
		vertexEnd = rand.nextInt(g.vertices.size());
		
		if(g.vertices.get(vertexStart).adjacencyList.get(0) < g.vertices.get(vertexEnd).adjacencyList.get(0)) {
			for ( int element : g.vertices.get(vertexEnd).adjacencyList) {
				if(element != g.vertices.get(vertexStart).adjacencyList.get(0) 
				   && element != g.vertices.get(vertexEnd).adjacencyList.get(0))
					g.vertices.get(vertexStart).adjacencyList.add(element);
			}
			
			for (Vertex v : g.vertices) {
				
				if(v.adjacencyList.get(0) != g.vertices.get(vertexEnd).adjacencyList.get(0)
					&& v.adjacencyList.get(0) != g.vertices.get(vertexStart).adjacencyList.get(0)) {					
					for (int i = 1; i < v.adjacencyList.size(); i++) {

						if(v.adjacencyList.get(i) == g.vertices.get(vertexEnd).adjacencyList.get(0)) {
							v.adjacencyList.set(i, g.vertices.get(vertexStart).adjacencyList.get(0));
						}
					}
					
				}
				
			}
			
			while(g.vertices.get(vertexStart).adjacencyList.contains(g.vertices.get(vertexEnd).adjacencyList.get(0)))
					g.vertices.get(vertexStart).adjacencyList.remove(g.vertices.get(vertexEnd).adjacencyList.get(0));
			g.vertices.remove(vertexEnd);
		}
		else if(g.vertices.get(vertexStart).adjacencyList.get(0) > g.vertices.get(vertexEnd).adjacencyList.get(0)) {
			for ( int element : g.vertices.get(vertexStart).adjacencyList) {
				if(element != g.vertices.get(vertexEnd).adjacencyList.get(0)
				   && element != g.vertices.get(vertexStart).adjacencyList.get(0))
					g.vertices.get(vertexEnd).adjacencyList.add(element);
			}
			
			for (Vertex v : g.vertices) {	
				if(v.adjacencyList.get(0) != g.vertices.get(vertexStart).adjacencyList.get(0)
					&&	v.adjacencyList.get(0) != g.vertices.get(vertexEnd).adjacencyList.get(0)) {
					for (int i = 1; i < v.adjacencyList.size(); i++) {						
						if(v.adjacencyList.get(i) == g.vertices.get(vertexStart).adjacencyList.get(0)) {
							v.adjacencyList.set(i, g.vertices.get(vertexEnd).adjacencyList.get(0));
						}
					}
				}
				
			}
			
			while(g.vertices.get(vertexEnd).adjacencyList.contains(g.vertices.get(vertexStart).adjacencyList.get(0)))
				g.vertices.get(vertexEnd).adjacencyList.remove(g.vertices.get(vertexStart).adjacencyList.get(0));
			
			g.vertices.remove(vertexStart);
		}
		return g;
	}	
}

class Graph {
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
}

class Vertex {
	ArrayList<Integer> adjacencyList = new ArrayList<Integer>();
}