import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    # 입력을 받는다
    m, n = map(int, input().split())
    graph = [[] for _ in range(m)]
    for i in range(n):
        s, e, c = map(int, input().split())
        graph[s].append((e, c))

    # 그래프를 너비 우선 탐색 하면서 0번 노드로부터 각 노드까지의 최소 비용을 갱신한다
    # 단, 너비 우선 탐색의 같은 깊이에 방문한 노드끼리만 비교한다
    INF = 1 << 30
    visit_depth = [INF] * m
    min_cost = [INF] * m

    dq = deque()
    visit_depth[0] = 0
    min_cost[0] = 0
    dq.append(0)
    depth = 1
    while dq:
        length = len(dq)

        for i in range(length):
            cur = dq.popleft()

            for nex, cost in graph[cur]:
                if visit_depth[nex] < depth:
                    continue
                if visit_depth[nex] == INF:
                    visit_depth[nex] = depth
                    dq.append(nex)
                min_cost[nex] = min(min_cost[nex], min_cost[cur] + cost)

        depth += 1

    # 0번 노드로부터 1번 노드까지의 최소 비용을 출력한다
    print(min_cost[1])

    
if __name__ == '__main__':
    main()
