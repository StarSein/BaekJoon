"""
각각의 길에 대해서
1보다 큰 차이가 발생한 지점부터 L 이상의 동일한 값이 등장하는지 여부를 찾으면 되겠다.
X) 조건 분기를 간단하게 하려면 각 행과 열을 새로운 객체로 복사해서 가져온 다음 값을 변경하는 게 낫겠다.
O) 각 행과 열을 덱으로 변환해서 가져오는 게 훨씬 간결하겠다.
"""
from sys import stdin
from collections import deque
from typing import List, Deque

input = lambda: stdin.readline().rstrip()


def solution(n: int, l: int, grid: List[List[int]]) -> int:
    def is_passable(road: Deque[int]) -> bool:
        prev_h = road.popleft()
        prev_cnt = 1
        while road:
            cur_h = road.popleft()
            if prev_h == cur_h:
                prev_cnt += 1
                continue
            elif prev_h == cur_h - 1:
                if prev_cnt < l:
                    return False
                else:
                    prev_cnt = 1
            elif prev_h == cur_h + 1:
                cur_cnt = 1
                while cur_cnt < l and road and road.popleft() == cur_h:
                    cur_cnt += 1
                if cur_cnt != l:
                    return False
                else:
                    prev_cnt = 0
            else:
                return False
            prev_h = cur_h
        return True

    ans = 0
    for row in grid:
        if is_passable(deque(row)):
            ans += 1
    for c in range(n):
        col = (grid[r][c] for r in range(n))
        if is_passable(deque(col)):
            ans += 1
    return ans


if __name__ == '__main__':
    N, L = map(int, input().split())
    print(solution(N, L, [list(map(int, input().split())) for _ in range(N)]))

