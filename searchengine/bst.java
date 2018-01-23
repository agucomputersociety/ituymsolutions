package hackathon1;
 
import java.util.Scanner;

public class bst {
    public static int[] arr;
    public static long mod;
    public static long[][] mem;
    public static long countTrees(int N, int curH, int H) {
    	  
    	  if ( curH > H ) return 0;
    	  
    	  if(mem[curH][N] != -1) {
              return mem[curH][N];
          }
    	  
    	  if (N <=1) { 
    	    return 1; 
    	  } 
    	  else {
    	    long sum = 0; 
    	    long left, right;
    	    int root;

    	    for (root=1; root<=N; root++) { 
    	      left = countTrees(root - 1, curH+1, H); 
    	      right = countTrees(N - root, curH+1, H);	

    	      // number of possible trees with this root == left*right 
    	      sum += (left*right) %mod; 
    	    }
    	 
    	    mem[curH][N] = sum%mod;
    	    
    	    return(mem[curH][N]);
    	  }
    	  
	  } 
    
    public static void main(String[] args) {
        
        mod = (int) Math.pow(10, 9)+7;
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int A = s.nextInt();
        int B = s.nextInt();
        mem = new long[B+1][N+1];
        for (int i = 0; i < mem.length; i++) {
			for (int j = 0; j < mem[i].length; j++) {
				mem[i][j] = -1;
			}
		}
        
        long a = countTrees(N, 0, B-1);
        for (int i = 0; i < mem.length; i++) {
			for (int j = 0; j < mem[i].length; j++) {
				mem[i][j] = -1;
			}
		}
        long b = countTrees(N, 0, A-2);
        System.out.println(Math.floorMod(a-b, mod));
    }
}