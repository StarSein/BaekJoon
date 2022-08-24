import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    n, m, K = map(int, input().split())

    jewel = [-1] * (n + 1)
    for i in range(K):
        jewel[int(input())] = i

    graph = [[] for _ in range(n + 1)]
    for i in range(m):
        a, b, c = map(int, input().split())
        graph[a].append((b, c))
        graph[b].append((a, c))

    visit = [[False for col in range(1 << K)] for row in range(n + 1)]

    dq = deque()
    dq.append((1, 0, 0))
    visit[1][0] = True

    ans = 0
    while dq:
        node, mask, numJewel = dq.popleft()

        if node == 1 and numJewel > ans:
            ans = numJewel

        for nextNode, limit in graph[node]:
            if numJewel <= limit:
                if not visit[nextNode][mask]:
                    dq.append((nextNode, mask, numJewel))
                    visit[nextNode][mask] = True

                j = jewel[nextNode]
                if j != -1 and not mask & 1 << j:
                    if not visit[nextNode][mask | 1 << j]:
                        dq.append((nextNode, mask | 1 << j, numJewel + 1))
                        visit[nextNode][mask] = True
    return ans


if __name__ == '__main__':
    print(solution())
