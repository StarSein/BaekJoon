"""
최소 거리 = (단말 노드로부터 D 이상 떨어진 노드의 개수 - 1) * 2
케니소프트는 항상 경로에 포함되어야 하므로, 케니소프트를 루트 노드로 삼자.
후위 순회를 하면서, '서브트리의 단말 노드와의 거리'의 최댓값 >= D 인 노드들을 경로에 포함시키면 된다.
"""
import sys

sys.setrecursionlimit(int(3e5))


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, s, d = map(int, input().split())
    graph = [[] for _ in range(n + 1)]
    for edge in range(n-1):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)

    def find_visit_node(current_node: int, parent_node: int) -> int:
        if graph[current_node] == [parent_node]:
            max_dist_to_leaf = 0
        else:
            max_dist_to_leaf = max(find_visit_node(child_node, current_node) + 1\
                                   for child_node in graph[current_node] if child_node != parent_node)
        if max_dist_to_leaf >= d:
            visit_set.add(current_node)
        return max_dist_to_leaf

    visit_set = set()
    visit_set.add(s)
    find_visit_node(s, 0)
    answer = (len(visit_set) - 1) * 2
    print(answer)


if __name__ == '__main__':
    main()
