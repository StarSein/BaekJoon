from sys import stdin, setrecursionlimit
from typing import List, Tuple


setrecursionlimit(int(2e6))


def input():
    return stdin.readline().rstrip()


def solution(m: int, n: int, edges: List[Tuple[int, int]]) -> int:
    if n == 0:
        return 0
    
    dp = [0 for _ in range(n)]
    adj = [-1 for _ in range(n)]
    for v, w in edges:
        adj[v] = w

    visits = set()
    vis_st = []

    def dfs(cur: int) -> int:
        if dp[cur]:
            return dp[cur]
        if adj[cur] == -1:
            dp[cur] = 1
            return 1

        if cur in visits:
            cycle = [cur]
            while vis_st[-1] != cur:
                e = vis_st.pop()
                visits.discard(e)
                cycle.append(e)
            vis_st.pop()
            visits.discard(cur)

            for e in cycle:
                dp[e] = len(cycle)
        else:
            visits.add(cur)
            vis_st.append(cur)
            dfs(adj[cur])
            dp[cur] = 1 + dp[adj[cur]] if dp[cur] == 0 else dp[cur]
            if cur in visits:
                visits.discard(cur)
                vis_st.pop()
        return dp[cur]
    ans = max(dfs(node) for node in range(n))
    return ans


if __name__ == '__main__':
    M, N = map(int, input().split())
    edge_list = list(tuple(map(int, input().split())) for _ in range(M))
    print(solution(M, N, edge_list))
