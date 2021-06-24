import sys
from itertools import combinations


INF = sys.maxsize
N, M = map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
l_house = []
l_chicken = []
for c in range(N):
    for r in range(N):
        if matrix[c][r] == 1:
            l_house.append((c, r))
        elif matrix[c][r] == 2:
            l_chicken.append((c, r))
        else:
            pass
l_NotClosed = combinations(l_chicken, M)
result = INF
for case in l_NotClosed:
    chi_dist = 0
    for house in l_house:
        dist = min(abs(house[0] - x[0]) + abs(house[1] - x[1]) for x in case)
        chi_dist += dist
    result = min(result, chi_dist)
print(result)