"""
트리에서 모든 노드는 단 하나의 부모 노드를 갖는다는 점에서 탐욕법으로 풀 수 있다.
1. 루트 노드로만 구성된 트리도 고려하자.
2.  후위 순회를 하면서
    리프 노드의 부모 노드부터 전단지를 붙이면 된다.
    현재 노드의 자식 노드 중 하나라도 전단지를 볼 수 없다면
    현재 노드에 전단지를 붙여야 한다.
    만약 모든 자식 노드가 전단지를 볼 수 있다면
    현재 노드에 전단지를 붙이지 않는다.

[1차 채점] WA.
루트 노드의 경우에는 부모 노드가 없으므로,
전단지가 붙은 자식 노드가 하나도 없다면, 전단지를 붙여줘야 한다.
"""
import sys


sys.setrecursionlimit(int(3e5))


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_tree(current: int, parent: int):
        for child in graph[current]:
            if child != parent:
                childs[current].append(child)
                make_tree(child, current)

    def emplace_leaflet(current: int):
        if len(childs[current]) == 0:
            return
        for child in childs[current]:
            emplace_leaflet(child)
        if any(emplaced[child] for child in childs[current]):
            affected[current] = True
        if any(not affected[child] for child in childs[current]):
            emplaced[current] = True
            affected[current] = True
            for child in childs[current]:
                affected[child] = True

    n, m = map(int, input().split())
    graph = [[] for _ in range(n + 1)]
    for edge in range(m):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    emplaced = [False] * (n + 1)
    affected = [False] * (n + 1)
    childs = [[] for _ in range(n + 1)]
    for node in range(1, n + 1):
        if not affected[node]:
            make_tree(node, -1)
            emplace_leaflet(node)
            if not affected[node]:
                emplaced[node] = True
    print(emplaced.count(True))


if __name__ == '__main__':
    main()
