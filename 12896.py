'''
항상 leaf가 아닌 internal node에 소방서를 배치해야 최적이다.
아 굳이 소방서를 임의의 위치에 배치하지 않아도 되겠다.
트리의 반지름이 곧 정답이 된다.

트리 DP로 접근하자.
DP의 요소로 [해당 노드를 루트로 하는 서브트리의 지름의 길이, 서브트리의 높이]
    노드의 자식 노드가 하나뿐인 경우: dp[cur][0] = max(dp[child][0], dp[child][1] + 1)
                              dp[cur][1] = dp[child][1] + 1
    노드의 자식 노드가 둘 이상인 경우: dp[cur][0] = max(max(dp[child][0] for child in childs), dp[c1][1] + dp[c2][1] + 2)
                               dp[cur][1] = dp[c1][1] + 1
후위 순회 방식으로 dp를 갱신해가자.
'''

import sys
from math import ceil

sys.setrecursionlimit(int(2e6))


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for node in range(n+1)]
    for edge in range(n-1):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    ROOT = 1
    dp = [[-1, -1] for node in range(n+1)]

    def make_dp(current_node: int, parent_node: int):
        child_list = []
        for next_node in graph[current_node]:
            if next_node != parent_node:
                child_list.append(next_node)
                make_dp(next_node, current_node)

        if len(child_list) == 0:
            dp[current_node] = [0, 0]
        elif len(child_list) == 1:
            child = child_list[0]
            dp[current_node][0] = max(dp[child][0], dp[child][1] + 1)
            dp[current_node][1] = dp[child][1] + 1
        else:
            child_list.sort(key=lambda x: -dp[x][1])
            first, second = child_list[:2]
            dp[current_node][0] = max(max(dp[child][0] for child in child_list), dp[first][1] + dp[second][1] + 2)
            dp[current_node][1] = dp[first][1] + 1

    make_dp(ROOT, 0)
    print(ceil(dp[ROOT][0] / 2))


if __name__ == '__main__':
    main()
