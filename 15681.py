import sys
from collections import deque

sys.setrecursionlimit(100_003)
input = sys.stdin.readline
HEAD = 0


def solution():
    make_tree(r, HEAD)
    
    while len(q_query):
        query = q_query.popleft()
        print(dp[query])


def make_tree(current: int, parent: int) -> int:
    for idx, adjacent in enumerate(adjacents[current]):
        if adjacent != parent:
            child = adjacent
            dp[current] += make_tree(child, current)
    return dp[current]


if __name__ == '__main__':
    n, r, q = map(int, input().split())
    adjacents = [[] for node in range(n+1)]
    for edge in range(n-1):
        u, v = map(int, input().split())
        adjacents[u].append(v)
        adjacents[v].append(u)
    q_query = deque([int(input()) for query in range(q)])
    dp = [1] * (n + 1)
    solution()
