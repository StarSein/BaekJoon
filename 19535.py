"""
트리의 노드별 정보를 바탕으로 계산할 수 있다.
depth[node], num_child[node], parent[node] 를 사용한다.
전위 순회 방식으로 트리를 만들고 depth, num_child 배열을 완성한다
ㄷ의 개수: depth >= 3인 모든 노드에 대해, 자식 노드의 개수를 합한 것
         depth와 관계 없이 모든 노드에 대해, 자식 노드의 개수 * (부모 노드의 자식 수 - 1)을 합한 것
ㅈ의 개수: depth >= 2인 모든 노드에 대해, comb(자식 노드의 개수, 2)를 합한 것
         depth와 관계 없이 모든 노드에 대해, comb(자식 노드의 개수, 3)을 합한 것
"""


import sys
from math import comb

sys.setrecursionlimit(int(3e5 + 50))


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for node in range(n + 1)]
    for edge in range(n-1):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    depth = [-1] * (n + 1)
    num_child = [-1] * (n + 1)
    parent = [-1] * (n + 1)

    def make_tree(curr_node: int):
        depth[curr_node] = depth[parent[curr_node]] + 1
        num_child[curr_node] = len(graph[curr_node])
        for next_node in graph[curr_node]:
            if next_node == parent[curr_node]:
                num_child[curr_node] -= 1
            else:
                parent[next_node] = curr_node
                make_tree(next_node)

    ROOT = 1
    depth[0] = 0
    parent[ROOT] = 0
    make_tree(ROOT)

    def count_d() -> int:
        cnt = 0
        for node in range(ROOT + 1, n + 1):
            cnt += num_child[node] * (num_child[parent[node]] - 1)
            if depth[node] >= 3:
                cnt += num_child[node]
        return cnt

    def count_g() -> int:
        cnt = 0
        for node in range(ROOT, n + 1):
            cnt += comb(num_child[node], 3)
            if depth[node] >= 2:
                cnt += comb(num_child[node], 2)
        return cnt
    num_d = count_d()
    num_g = count_g()
    if num_d > 3 * num_g:
        res = "D"
    elif num_d < 3 * num_g:
        res = "G"
    else:
        res = "DUDUDUNGA"
    print(res)


if __name__ == '__main__':
    main()
