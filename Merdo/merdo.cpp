#include <cstdio>
#include <iostream>
#include <vector>

using namespace std;

const int MAXN = 2002;
const int MAXK = 202;

const long long int MOD = 1e9 + 7;

int N, K;

long long int res[MAXN]; // results array, res[i] is the number of subtrees with i number of nodes
long long int dn[MAXN][MAXK]; // dn[i][j] stores number of subtrees with j nober of nodes rooted with node i

vector<int> graph[MAXN];

void dfs( int node, int previous ){ // We are at node numbered "node" and preceding node of this node is "previous"
	
	dn[node][1] = 1LL;
	
	for( auto adj : graph[node] ) // "adj" will be a child of this node
		if( adj != previous ){ // Infinite recursion and wrong result control
			
			dfs(adj, node); // Calculate results of nodes above "node"
			
			long long int tmp[MAXK]; // tmp array stores current situation of dn[node]
			
			for( int i=1 ; i<=K ; i++ ) // Copying dn[node] into tmp array
				tmp[i] = dn[node][i];
			
			for( int i=1 ; i<=K ; i++ ) // We will update dn[node][i] in the 1...K order of i values
				for( int j=1 ; j<i ; j++ ) // We can obtain i number of nodes by results of "adj"
					dn[node][i] = (dn[node][i] + ((tmp[j] * dn[adj][i-j]) % MOD)) % MOD; // We can use j nodes from subtree of "adj" and i-j nodes from subtree of "node"(excluding subtree of "adj")
		}
	
	for( int i=1 ; i<=K ; i++ ) // Updating original result array with number of different subtrees that are rooted with "node"
		res[i] = (res[i] + dn[node][i]) % MOD;
}

int main(){
	
	
	scanf("%d%d", &N, &K);
	
	for( int a, b, i=1 ; i<N ; i++ ){ // Constructing the tree
		scanf("%d%d", &a, &b);
		graph[a].push_back(b);
		graph[b].push_back(a);
	}
	
	dfs(1, 0); // Starting from node number 1(it will be root of the tree)
	
	for( int i=1 ; i<=K ; i++ )
		printf("%lld ", res[i]);
	
	return 0;
}