"""
정점의 개수 == 간선의 개수 + 1 이면 트리 형태이다.
트리 형태에서 간선이 한 개 추가되면 사이클은 단 한 개 존재한다.

사이클 찾기: 임의의 한 정점에서 dfs를 한다.
방문한 정점들을 스택에 저장하고, 방문한 적이 있는 정점 X를 방문하게 될 경우
X가 pop될 때까지 현재 스택에 있는 요소들을 pop한 것이 사이클이다.
"""
import sys

sys.setrecursionlimit(int(5e3))


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    graph = [[] for _ in range(n + 1)]
    for edge in range(n):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    visited = [False] * (n + 1)
    visit_stack = []

    def find_cycle(curr_node: int, prev_node: int) -> int:
        if visited[curr_node]:
            return curr_node

        visited[curr_node] = True
        visit_stack.append(curr_node)
        for next_node in graph[curr_node]:
            if next_node != prev_node:
                cycle_end = find_cycle(next_node, curr_node)
                if cycle_end:
                    return cycle_end
        visit_stack.pop()
        return 0

    end_point = find_cycle(1, 0)
    cycle_lp = visit_stack.index(end_point)
    cycle_set = set(visit_stack[cycle_lp:])

    dists = [-1] * (n + 1)

    def calc_dist(curr_node: int, prev_node: int, dist: int):
        dists[curr_node] = dist
        for next_node in graph[curr_node]:
            if next_node not in cycle_set and next_node != prev_node:
                calc_dist(next_node, curr_node, dist + 1)

    for node in cycle_set:
        calc_dist(node, 0, 0)

    print(*dists[1:])


if __name__ == '__main__':
    main()
