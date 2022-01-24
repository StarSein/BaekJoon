import sys


input = sys.stdin.readline
NUM_TC = 4
NUM_PLAYER = 6

WIN = 0
DRAW = 1
LOSE = 2


def solution() -> str:
    sum_wins = sum([res_table[i][WIN] for i in range(NUM_PLAYER)])
    sum_loses = sum([res_table[i][LOSE] for i in range(NUM_PLAYER)])

    if sum_wins != sum_loses:
        return '0'

    for idx, res in enumerate(res_table):
        if sum(res) != NUM_PLAYER - 1:
            return '0'

    for i in range(NUM_PLAYER):
        if res_table[i][DRAW]:
            for j in range(i + 1, NUM_PLAYER):
                if res_table[i][DRAW] and res_table[j][DRAW]:
                    res_table[i][DRAW] -= 1
                    res_table[j][DRAW] -= 1
            if res_table[i][DRAW]:
                return '0'

    return '1'


if __name__ == '__main__':
    for tc in range(NUM_TC):
        inp = list(map(int, input().split()))
        res_table = []
        for player in range(NUM_PLAYER):
            res_table.append(inp[3*player:3*player+3])
        sol = solution()
        print(sol, end=' ')
