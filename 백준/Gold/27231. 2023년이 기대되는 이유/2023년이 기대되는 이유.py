from sys import stdin
from itertools import pairwise


def input():
    return stdin.readline().rstrip()


def read_test_case():
    return input()


def solution(n: str) -> str:
    if all(int(x) < 2 for x in n):
        return "Hello, BOJ 2023!"

    sz = len(n)
    rhs_set = set()

    def get_rhs(bitmask: int) -> int:
        oprs = [i for i in range(sz + 1) if bitmask & (1 << i)]
        ret = 0
        for l, r in pairwise(oprs):
            ret += int(n[l:r])
        return ret

    def dfs(pos: int, bitmask: int):
        if pos == sz:
            rhs_set.add(get_rhs(bitmask))
            return
        dfs(pos + 1, bitmask | (1 << pos))
        dfs(pos + 1, bitmask)

    def get_lhs(m: int) -> int:
        return sum(int(x) ** m for x in n)

    dfs(1, 1 | (1 << sz))
    max_rhs = max(rhs_set)
    m = 1
    answer = 0
    while True:
        lhs = get_lhs(m)
        if lhs in rhs_set:
            answer += 1
        if lhs >= max_rhs:
            break
        m += 1
    return str(answer)


def main():
    T = int(input())
    for _ in range(T):
        print(solution(read_test_case()))


if __name__ == '__main__':
    main()
