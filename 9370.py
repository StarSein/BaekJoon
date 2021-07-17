import sys


def floyd_warshall():
    for k in range(1, n + 1):
        for p in range(1, n + 1):
            for q in range(1, n + 1):
                graph[p][q] = min(graph[p][q], graph[p][k] + graph[k][q])


INF = sys.maxsize
T = int(sys.stdin.readline())
for case in range(T):
    n, m, t = map(int, sys.stdin.readline().split())
    s, g, h = map(int, sys.stdin.readline().split())
    graph = [[INF for i in range(n + 1)] for j in range(n + 1)]
    for road in range(m):
        a, b, d = map(int, sys.stdin.readline().split())
        graph[a][b], graph[b][a] = d, d
    l_cdd = []
    for candidate in range(t):
        l_cdd.append(int(sys.stdin.readline()))
    floyd_warshall()
