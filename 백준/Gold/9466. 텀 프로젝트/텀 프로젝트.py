from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    n = int(input())
    picks = list(map(int, input().split()))
    return n, picks


def solve(n: int, picks: List[int]) -> int:
    picks.insert(0, 0)

    belong_cnt = 0
    visit = [False for _ in range(n + 1)]
    stack = []
    for start in range(1, n + 1):
        if visit[start]:
            continue
        stack.append(start)
        visit[start] = True
        cur = picks[start]
        while not visit[cur]:
            stack.append(cur)
            visit[cur] = True
            cur = picks[cur]

        cycle = []
        while stack and stack[-1] != cur:
            cycle.append(stack.pop())

        if stack:
            belong_cnt += len(cycle) + 1
            stack.clear()

    return n - belong_cnt


def main():
    T = int(input())
    for t in range(T):
        print(solve(*read_test_case()))


if __name__ == '__main__':
    main()
