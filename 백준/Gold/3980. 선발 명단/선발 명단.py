from sys import stdin
from typing import Tuple


input = lambda: stdin.readline().rstrip()


def solution(stats: Tuple[Tuple[int, ...]]) -> int:
    proper_stats = tuple(tuple((pos, stat) for pos, stat in enumerate(stats[player])
                               if stats[player][pos])
                         for player in range(NUM_POS))

    occupied = [False] * NUM_POS

    def dfs(player: int, cur_sum: int) -> int:
        if player == NUM_POS:
            return cur_sum

        ret = 0
        for pos, stat in proper_stats[player]:
            if not occupied[pos]:
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
        