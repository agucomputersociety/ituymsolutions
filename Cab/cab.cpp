#include <cstdio>
#include <cstring>
#include <iostream>

using namespace std;

const int MAXL = 30;
const int MAXN = 2*(1e5+15);

struct SegmentTree{
	
	int seg[MAXN*4];
	int val[MAXN*4];
	
	SegmentTree(){ // Initializing tree
		memset(seg, 0, sizeof seg);
		memset(val, -1, sizeof val);
	}
	
	void push( int k, int b, int e ){ // Transferring lazy values of the node number k to its children
		
		if( val[k] != -1 ){
			val[k*2] = val[k*2+1] = val[k];
			val[k] = -1;
			seg[k] = val[k*2]*(e-b+1);
		}
	}
	
	void assign( int k, int b, int e, int l, int r, int v ){ // Assigning values of elements in range [l, r] to v -> O(log(N))
		
		push(k, b, e);
		
		if( b > r || e < l )
			return;
		
		if( b >= l && e <= r ){
			val[k] = v;
			seg[k] = v*(e-b+1);
			return;
		}
		
		push(k, b, e);
		assign(k*2, b, (b+e) >> 1, l, r, v);
		assign(k*2+1, ((b+e) >> 1) + 1, e, l, r, v);
		seg[k] = seg[k*2] + seg[k*2+1];
	}
	
	int sum( int k, int b, int e, int l, int r ){ // Summing numbers in range [l, r] -> O(log(N))
		
		push(k, b, e);
		
		if( b > r || e < l )
			return 0;
		if( b >= l && e <= r )
			return seg[k];
		
		push(k, b, e);
		
		return sum(k*2, b, (b+e) >> 1, l, r) + sum(k*2+1, ((b+e) >> 1) + 1, e, l, r);
	}
	
};

int N, Q;

SegmentTree tree[MAXL]; // We have separate segment trees for evey index number of bits

int main(){
	
	scanf("%d", &N);
	
	for( int num, i=1 ; i<=N ; i++ ){
		
		scanf("%d", &num);
		
		for( int j=0 ; j<MAXL ; j++ )
			if( num & (1 << j) )
				tree[j].assign(1, 1, N, i, i, 1);
	}
	
	scanf("%d", &Q);
	
	for( int t, a, b, c, i=0 ; i<Q ; i++ ){
		
		scanf("%d%d%d", &t, &a, &b);
		
		if( t == 1 ){
			
			long long int sum = 0;
			
			for( int j=0 ; j<MAXL ; j++ )
				sum += (long long int )tree[j].sum(1, 1, N, a, b) * (1LL << j); // Summing every bit separately -> O(MAXL*log(N))
			
			printf("%lld\n", sum);
		}
		else if( t == 2 ){
			
			scanf("%d", &c);
			
			tree[c-1].assign(1, 1, N, a, b, 1); // O(log(N))
		}
		else if( t == 3 ){
			
			scanf("%d", &c);
			
			tree[c-1].assign(1, 1, N, a, b, 0); // O(log(N))
		}
	}
	
	return 0;
}