"""
절단 작업의 횟수 = 그래프에서 사이클의 개수
연결 작업의 횟수 = 그래프에서 연결 요소의 개수 - 1
[1차 채점] WA
사이클을 구성하는 간선을 제거하는 작업을 하지 않았다.
그런데 간선의 개수를 볼 때 시간 초과의 조짐이 보인다.
[2차 채점] WA
후위 순회에서 중복으로 방문하는 노드를 사이클로 규정하는 건 오류가 있는 것 같다.
트리가 되려면 간선의 개수가 N-1이어야 한다는 점을 이용해 보자.
[3차 채점] AC
분리 집합을 이용해서 풀어보자.
"""
import sys


sys.setrecursionlimit(int(1e5+500))


def input():
    return sys.stdin.readline().rstrip()


def main():
    def find_root(x: int) -> int:
        if roots[x] == x:
            return x
        roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int):
        if a > b:
            a, b = b, a
        roots[b] = a

    n, m = map(int, input().split())
    roots = [node for node in range(n + 1)]
    cnt_cut = 0
    for edge in range(m):
        u, v = map(int, input().split())
        root_u = find_root(u)
        root_v = find_root(v)
        if root_u != root_v:
            union(root_u, root_v)
        else:
            cnt_cut += 1
    cnt_join = len(set([find_root(node) for node in range(1, n + 1)])) - 1
    print(cnt_cut + cnt_join)


if __name__ == '__main__':
    main()
