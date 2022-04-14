"""
증가하는 도로의 비용 t는 '남아있는 모든 도로'에 적용되므로, 최적 선택지를 바꾸지는 않는다.
어떤 방식으로 최소 신장 트리를 만들던, 누적 추가 비용은 t(n-2)(n-1)/2 이다.
크루스칼 알고리즘으로 풀자.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m, t = map(int, input().split())
    edges = [tuple(map(int, input().split())) for edge in range(m)]

    def find_root(x: int) -> int:
        if roots[x] == x:
            return x
        
        roots[x] = find_root(roots[x])
        return roots[x]

    def union(ra: int, rb: int):
        if ra > rb:
            ra, rb = rb, ra

        roots[rb] = ra

    edges.sort(key=lambda x: x[2])
    roots = [node for node in range(n + 1)]
    cost_add = 0
    total_cost = 0
    for a, b, c in edges:
        root_a = find_root(a)
        root_b = find_root(b)

        if root_a != root_b:
            union(root_a, root_b)
            total_cost += c + cost_add
            cost_add += t
    print(total_cost)


if __name__ == '__main__':
    main()
