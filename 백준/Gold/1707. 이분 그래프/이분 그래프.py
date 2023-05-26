from sys import stdin, setrecursionlimit
from typing import List, Tuple

setrecursionlimit(100000)


def input():
    return stdin.readline().rstrip()


def read_input():
    V, E = map(int, input().split())
    edges = [tuple(map(int, input().split())) for _ in range(E)]
    return V, E, edges


def solution(V: int, E: int, edges: List[Tuple[int, int]]) -> str:

    def dfs(cur: int) -> bool:
        ret = True
        for nex in graph[cur]:
            if check[nex] == check[cur]:
                return False
            if check[nex] == -1:
                check[nex] = 1 - check[cur]
                ret &= dfs(nex)
        return ret

    graph = [[] for _ in range(V + 1)]
    for a, b in edges:
        graph[a].append(b)
        graph[b].append(a)

    check = [-1 for _ in range(V + 1)]

    for i in range(1, V + 1):
        if check[i] == -1:
            check[i] = 1
            if not dfs(i):
                return "NO"
    return "YES"


def main():
    K = int(input())
    for _ in range(K):
        print(solution(*read_input()))


if __name__ == '__main__':
    main()
