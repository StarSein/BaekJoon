import sys
from itertools import combinations


def allocate():
    global min_gap
    for team1 in combinations(range(N), N//2):
        for member in team1:
            l_check[member] = True
        team2 = []
        for person in range(N):
            if not l_check[person]:
                team2.append(person)
        score1, score2 = 0, 0
        for item1 in combinations(team1, 2):
            score1 += S[item1[0]][item1[1]] + S[item1[1]][item1[0]]
        for item2 in combinations(team2, 2):
            score2 += S[item2[0]][item2[1]] + S[item2[1]][item2[0]]
        gap = abs(score1 - score2)
        min_gap = min(min_gap, gap)
        for member in team1:
            l_check[member] = False


N = int(sys.stdin.readline())
S = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
l_check = [False] * N
min_gap = sys.maxsize
allocate()
print(min_gap)
