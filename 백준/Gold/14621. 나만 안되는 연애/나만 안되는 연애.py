"""
남초와 여초를 연결하는 엣지로만 구성된 최소 스패닝 트리를 찾는 문제.
스패닝 트리가 없는 경우 == 분리집합의 개수가 2 이상인 경우
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    genders = list(input().split())
    edges = [tuple(map(int, input().split())) for _ in range(m)]
    # edge = (u, v, d)
    edges.sort(key=lambda x: x[2])
    genders.insert(0, 'NULL')
    roots = [node for node in range(n + 1)]

    def find_root(x: int) -> int:
        if roots[x] == x:
            return x

        roots[x] = find_root(roots[x])
        return roots[x]

    def union(ra: int, rb: int):
        if ra > rb:
            ra, rb = rb, ra

        roots[rb] = ra

    mst_len = 0
    for u, v, d in edges:
        if genders[u] != genders[v]:
            root_u = find_root(u)
            root_v = find_root(v)
            if root_u != root_v:
                union(root_u, root_v)
                mst_len += d
    if len(set([find_root(i) for i in range(1, n + 1)])) == 1:
        print(mst_len)
    else:
        print(-1)


if __name__ == '__main__':
    main()
