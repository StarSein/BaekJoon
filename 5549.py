"""
naive하게 구현할 경우 시간 복잡도는 O(MMK)
imos 알고리즘으로 구현할 경우 시간 복잡도는 O(NM + K)
"""


import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    m, n = map(int, input().split())
    k = int(input())
    grid = [list(input()) for col in range(m)]

    NUM_TERRAIN = 3
    terrain_idx = {'J': 0, 'O': 1, 'I': 2}
    pref_cnt = [[[0, 0, 0] for row in range(n+1)] for col in range(m+1)]
    for col in range(1, m+1):
        for row in range(1, n+1):
            pref_cnt[col][row] = pref_cnt[col][row-1][:]
            pref_cnt[col][row][terrain_idx.get(grid[col-1][row-1])] += 1
    for row in range(1, n+1):
        for col in range(1, m+1):
            for idx in range(NUM_TERRAIN):
                pref_cnt[col][row][idx] += pref_cnt[col-1][row][idx]

    for query in range(k):
        a, b, c, d = map(int, input().split())
        info = []
        for idx in range(NUM_TERRAIN):
            info.append(pref_cnt[c][d][idx] - pref_cnt[a-1][d][idx] - pref_cnt[c][b-1][idx] + pref_cnt[a-1][b-1][idx])
        print(*info)


if __name__ == '__main__':
    main()
