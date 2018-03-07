package hackathon1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class mirror {
	 
	
    static final int INF= Integer.MIN_VALUE;
    
    class AdjListNode
    {
        private int v;
        private int weight;
        AdjListNode(int _v, int _w) { v = _v;  weight = _w; }
        int getV() { return v; }
        int getWeight()  { return weight; }
    }
 
    class Graph
    {
        private int V;
        private LinkedList<AdjListNode>adj[];
        Graph(int v)
        {
            V=v;
        }
        
       
        void topologicalSortUtil(int v, Boolean visited[], Stack stack)
        {
            visited[v] = true;
            Integer i;
 
            for(int t = v+1; t < V; t++){
        		
                if (!visited[t])
                    topologicalSortUtil(t, visited, stack);
            }
            stack.push(new Integer(v));
        }
 
        int[] shortestPath(int s)
        {
            Stack stack = new Stack();
            int dist[] = new int[V];
 
            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;
 
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);
 
            for (int i = 0; i < V; i++)
                dist[i] = INF;
            dist[s] = 0;
 
            while (stack.empty() == false)
            {
                int u = (int)stack.pop();
 
                if (dist[u] != INF)
                {
                	for(int t = u+1; t < V; t++){
                		
                		if (allPaths[u][t] > 0 && dist[t] < dist[u] + allPaths[u][t])
                            dist[t] = dist[u] + allPaths[u][t];
                	}
                    
                }
            }
 
            return dist;
        }
    }
 

    Graph newGraph(int number)
    {
        return new Graph(number);
    }

    private static int numberOfOverlaps(String a, String b) {
        int score = 0;
        
        int min = Math.min(a.length(), b.length()); //min = 4
        for (int len = min; len >= 1; len--) {
            int startInA = a.length() - len;
            boolean ok = true;
            for(int i = 0; i < len; i++) {
                if(a.charAt(startInA + i) != b.charAt(i)) {
                    ok = false;
                    break;
                }
            }
            if(ok) {
                score = len;
                break;
            }
        }
        return score;
    }

	private static int maxValue(int[] numbers) {
	    int max = numbers[0];
	    for (int i = 0; i < numbers.length; i++) {
			if(max < numbers[i])
				max = numbers[i];
		}
				
		return max;
	}
	public static int[][] allPaths;
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		
		int N = Integer.valueOf(s.nextLine());
		
		String[] A;
		HashSet<String> set = new HashSet<String>();

		for (int i = 0; i < N; i++) {
			set.add(s.nextLine());
		}
		
		A = set.toArray(new String[set.size()]);

		Arrays.sort(A);
		N = A.length;
		mirror t = new mirror();
		
        Graph g = t.newGraph(N);
        allPaths = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				int score = numberOfOverlaps(A[i], A[j]);
				if(score > 0){
					allPaths[i][j] = score;
				} else {
					allPaths[i][j] = -1;
				}
			}
		}
		int highest = Integer.MIN_VALUE; 
		for (int i = 0; i < N; i++) {
			int[] dist = g.shortestPath(i);
			if(highest < maxValue(dist))
				highest = maxValue(dist);
		}
		
		System.out.print(highest);
		
	}
}
