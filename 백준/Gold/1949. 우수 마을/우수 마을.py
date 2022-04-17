"""
트리디피를 이용하자.
dp[node][0] = node가 루트인 서브트리의 총 우수마을 주민 수 (node가 우수 마을)
dp[node][1] = node가 루트인 서브트리의 총 우수마을 주민 수 (node는 우수 마을이 아님)

dp[node][0] = popul[node] + sum(dp[child][1] for child in childs[node])
dp[node][1] = sum(max(dp[child]) for childs[node])

세 번째 조건은 '우수 마을'이 아닌 마을이 3번 연속으로 있는 경우가 없어야 한다는 조건인데,
이 로직에서는, 항상 우수 마을이 1칸 혹은 2칸 간격으로 있으므로 만족하게 된다.

1번 노드를 루트라고 두고, 후위 순회 방식으로 tree dp를 만들자.
"""
import sys

sys.setrecursionlimit(int(2e4))


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_tree_dp(curr_node: int, parent_node: int):
        for next_node in graph[curr_node]:
            if next_node != parent_node:
                make_tree_dp(next_node, curr_node)
        dp[curr_node][0] = num_ppl[curr_node] \
                           + sum(dp[child_node][1] for child_node in graph[curr_node] if child_node != parent_node)
        dp[curr_node][1] = sum(max(dp[child_node]) for child_node in graph[curr_node] if child_node != parent_node)

    n = int(input())
    num_ppl = [0] + list(map(int, input().split()))
    graph = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    dp = [[-1, -1] for node in range(n + 1)]
    ROOT = 1
    make_tree_dp(ROOT, 0)
    print(max(dp[ROOT]))


if __name__ == '__main__':
    main()
