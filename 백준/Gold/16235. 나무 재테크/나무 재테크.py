from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M, K = map(int, input().split())
    A = [list(map(int, input().split())) for _ in range(N)]
    trees = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, K, A, trees


def solution(N: int, M: int, K: int, A: List[List[int]], trees: List[Tuple[int, int, int]]) -> int:
    grid = [[[] for c in range(N)] for r in range(N)]
    new_tree_cnt = [[0 for c in range(N)] for r in range(N)]
    food = [[5 for c in range(N)] for r in range(N)]
    dir_list = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]

    trees.sort(key=lambda x: x[2])
    for x, y, z in trees:
        grid[x - 1][y - 1].append(z)

    for _ in range(K):
        # 봄 & 여름
        for r in range(N):
            for c in range(N):
                if not grid[r][c]:
                    continue

                cur = grid[r][c]
                cur.sort()
                length = len(cur)
                for i in range(length):
                    if cur[i] <= food[r][c]:
                        food[r][c] -= cur[i]
                        cur[i] += 1
                    else:
                        for j in range(length - 1, i - 1, -1):
                            food[r][c] += cur[j] // 2
                            cur.pop()
                        break

        # 가을
        for r in range(N):
            for c in range(N):
                if not grid[r][c]:
                    continue

                count = 0
                for age in grid[r][c]:
                    if age % 5 == 0:
                        count += 1

                for dr, dc in dir_list:
                    nr = r + dr
                    nc = c + dc
                    if 0 <= nr < N and 0 <= nc < N:
                        new_tree_cnt[nr][nc] += count

        for r in range(N):
            for c in range(N):
                if new_tree_cnt[r][c] == 0:
                    continue
                grid[r][c].extend(1 for _ in range(new_tree_cnt[r][c]))
                new_tree_cnt[r][c] = 0

        # 겨울
        for r in range(N):
            for c in range(N):
                food[r][c] += A[r][c]

    # K년이 지난 후
    answer = 0
    for r in range(N):
        for c in range(N):
            answer += len(grid[r][c])

    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
