"""
나무의 리스트 - 정렬된 상태 유지
각 칸의 양분 2차원 배열: 원소 int
spring, summer, autumn, winter

[1차 채점] TLE
모든 나무를 정렬하는 작업에서 시간 소모가 크다.
나무를 2차원 배열 내부의 리스트에 저장해야겠다.
"""
import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m, k = map(int, input().split())
    food_add = [list(map(int, input().split())) for _ in range(n)]
    trees = [[[] for row in range(n)] for col in range(n)]
    for tree in range(m):
        col, row, age = map(int, input().split())
        trees[col-1][row-1].append(age)

    INIT_FOOD = 5
    BABY_AGE = 1
    food = [[INIT_FOOD for row in range(n)] for col in range(n)]
    baby_trees = deque()
    dead_trees = deque()

    d = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]

    def spring():
        for col in range(n):
            for row in range(n):
                grown_trees = []
                for age in sorted(trees[col][row]):
                    if food[col][row] >= age:
                        grown_trees.append(age + 1)
                        food[col][row] -= age
                    else:
                        dead_trees.append((col, row, age))
                trees[col][row] = grown_trees

    def summer():
        while dead_trees:
            col, row, age = dead_trees.popleft()
            food[col][row] += age // 2

    def autumn():
        for col in range(n):
            for row in range(n):
                for age in trees[col][row]:
                    if age % 5 == 0:
                        for dy, dx in d:
                            nc, nr = col + dy, row + dx
                            if 0 <= nc < n and 0 <= nr < n:
                                baby_trees.append((nc, nr))
        while baby_trees:
            col, row = baby_trees.popleft()
            trees[col][row].append(BABY_AGE)

    def winter():
        for col in range(n):
            for row in range(n):
                food[col][row] += food_add[col][row]

    for i in range(k):
        spring()
        summer()
        autumn()
        winter()

    num_tree = 0
    for col in range(n):
        for row in range(n):
            num_tree += len(trees[col][row])
    print(num_tree)


if __name__ == '__main__':
    main()
