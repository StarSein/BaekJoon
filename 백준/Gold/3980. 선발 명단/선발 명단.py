from sys import stdin
from typing import Tuple


input = lambda: stdin.readline().rstrip()


def solution(stats: Tuple[Tuple[int, ...]]) -> int:
    occupied = [False] * NUM_POS

    def dfs(player: int, cur_sum: int) -> int:
        if player == NUM_POS:
            return cur_sum
        
        ret = 0
        for pos in range(NUM_POS):
            if stats[player][pos] and not occupied[pos]:
                occupied[pos] = True
                res = dfs(player + 1, cur_sum + stats[player][pos])
                ret = max(ret, res)
                occupied[pos] = False
        return ret

    return dfs(0, 0)


if __name__ == '__main__':
    NUM_POS = 11

    C = int(input())
    for _ in range(C):
        S = tuple(tuple(map(int, input().split())) for _ in range(NUM_POS))
        print(solution(S))
        