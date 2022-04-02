"""
탐욕법으로 풀 수 있다.
일단 무조건 단말 노드는 비우고, 단말 노드의 부모 노드에 경찰서를 설치하는 것이 최적이다.
후위 순회를 하면서
    해당 내부 노드에서, 모든 자식 노드에 대해 '경찰서가 설치된 자식 노드'가 하나라도 존재할 경우 해당 노드에 경찰서를 설치하지 않아도 된다.

[1차 채점]
WA. 경찰서를 설치하지 않아도 될 조건에는, 자식 노드에 경찰서가 설치된 경우도 포함된다.

[2차 채점]
WA. 문제를 성실히 읽지 않았다. 감시 범위에 들어가야 하는 것은 노드뿐만 아니라 모든 간선이다.
그렇게 되면 내부 노드에 대해 부여할 경찰서 설치의 조건이 다음과 같이 달라진다.
    모든 자식 노드에 경찰서가 설치된 경우에만, 해당 노드에 경찰서를 설치하지 않는다.
"""
import sys


sys.setrecursionlimit(int(1e5 + 50))


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for node in range(n + 1)]
    for edge in range(n - 1):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    is_office = [False] * (n + 1)

    def place_office(current: int, parent: int):
        num_child = 0
        num_monitor = 0
        for child in graph[current]:
            if child != parent:
                num_child += 1
                place_office(child, current)
                num_monitor += int(is_office[child])

        if num_child > num_monitor:
            is_office[current] = True

    ROOT = 1
    place_office(ROOT, 0)
    print(sum(is_office))


if __name__ == '__main__':
    main()
