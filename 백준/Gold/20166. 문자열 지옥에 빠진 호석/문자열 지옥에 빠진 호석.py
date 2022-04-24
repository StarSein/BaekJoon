"""
1차 채점. TLE
메모이제이션으로 최적화해야겠다.
재귀 호출로 dfs를 구현하자.
"""


import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    y_steps = [0, 1, 0, -1, 1, 1, -1, -1]
    x_steps = [1, 0, -1, 0, 1, -1, 1, -1]

    def dfs(c: int, r: int, pos: int) -> int:
        target_sub = target_str[pos:]
        if target_sub in table[c][r]:
            return table[c][r][target_sub]
        if pos == len(target_str):
            return 1
        num_case = 0
        for dy, dx in zip(y_steps, x_steps):
            nc = (c + dy) % n
            nr = (r + dx) % m
            if grid[nc][nr] == target_str[pos]:
                num_case += dfs(nc, nr, pos + 1)
        table[c][r][target_sub] = num_case
        return num_case

    def get_num_case() -> int:
        num_case = 0
        for col in range(n):
            for row in range(m):
                if grid[col][row] == target_str[0]:
                    num_case += dfs(col, row, 1)
        return num_case

    n, m, k = map(int, input().split())
    grid = [input() for _ in range(n)]
    table = [[dict() for row in range(m)] for col in range(n)]
    for i in range(k):
        target_str = input()
        print(get_num_case())


if __name__ == '__main__':
    main()
