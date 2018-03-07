def shuffleElement(P, K, i):
    cur = i
    L = 0

    for j in range(K):
        cur = P[cur]
        if cur == i:
            if j == 0:
                return cur

            L = (K-j-1)%(j+1)

            break

    for j in range(L):
        cur = P[cur]

    return cur

data = input().split(" ")

Q = int(data[0])
N = int(data[1])
K = int(data[2])

P = input().split(' ')
reverseP = [None]*len(P)
for i in range(len(P)):
    P[i] = int(P[i]) - 1

for i,j in enumerate(P):
    P[i] += 1
    reverseP[j] = i

queries = [None]*Q

p1 = shuffleElement(reverseP, K, N-2)
p2 = shuffleElement(reverseP, K, N-1)

for i in range(Q):
    q = input().split(' ')

    if int(q[p1]) > int(q[p2]):
        queries[i] = 'YES'
    else:
        queries[i] = 'NO'

for i in range(Q):
    print(queries[i])