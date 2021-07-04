import sys
from itertools import combinations


def allocate(k):
    global min_gap
    if k == N//2:
        score1, score2 = 0, 0
        for item1 in combinations(team1, 2):
            score1 += S[item1[0]][item1[1]] + S[item1[1]][item1[0]]
        for item2 in combinations(team2, 2):
            score2 += S[item2[0]][item2[1]] + S[item2[1]][item2[0]]
        gap = abs(score1 - score2)
        min_gap = min(min_gap, gap)
    for i in range(N):
        if l_check[i]:
            continue
        l_check[i] = True
        team1.append(i)
        for j in range(N):
            if l_check[j]:
                continue
            l_check[j] = True
            team2.append(j)
            allocate(k + 1)
            l_check[j] = False
            team2.pop()
        l_check[i] = False
        team1.pop()


N = int(sys.stdin.readline())
S = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
l_check = [False] * N
team1, team2 = [], []
min_gap = sys.maxsize
allocate(0)
print(min_gap)