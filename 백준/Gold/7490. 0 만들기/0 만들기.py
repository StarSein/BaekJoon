from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def main():
    T = int(input())
    for _ in range(T):
        N = int(input())
        print(*solution(N), sep='\n')
        print()


def solution(N: int) -> List[str]:
    def dfs(depth: int, cur_sum: int, cur_val: int):
        if depth == N + 1:
            if cur_sum + cur_val == 0:
                answer.append(''.join(expr))
            return

        expr.append(f" {depth}")
        dfs(depth + 1, cur_sum, 10 * cur_val + depth if cur_val > 0 else 10 * cur_val - depth)
        expr.pop()

        expr.append(f"+{depth}")
        dfs(depth + 1, cur_sum + cur_val, depth)
        expr.pop()

        expr.append(f"-{depth}")
        dfs(depth + 1, cur_sum + cur_val, -depth)
        expr.pop()

    expr = ['1']
    answer = []
    dfs(2, 0, 1)
    return answer


if __name__ == '__main__':
    main()
