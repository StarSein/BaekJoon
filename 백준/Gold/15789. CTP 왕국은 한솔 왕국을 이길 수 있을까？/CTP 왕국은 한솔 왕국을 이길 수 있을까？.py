from sys import stdin
from collections import Counter


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, M = map(int, input().split())
    edge_list = [tuple(map(int, input().split())) for _ in range(M)]
    C, H, K = map(int, input().split())
    return N, M, edge_list, C, H, K


def solution(N, M, edge_list, C, H, K) -> int:
    def find_root(x: int) -> int:
        if roots[x] != x:
            roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int) -> int:
        if a > b:
            a, b = b, a
        roots[b] = a
    roots = [i for i in range(N + 1)]
    for x, y in edge_list:
        rx = find_root(x)
        ry = find_root(y)
        if rx != ry:
            union(rx, ry)
    counter = Counter((find_root(i) for i in range(1, N + 1)))
    answer = counter[roots[C]]
    counter.pop(roots[C])
    counter.pop(roots[H])
    sorted_values = sorted(counter.values(), reverse=True)
    answer += sum(sorted_values[:K])
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
