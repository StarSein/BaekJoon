'''
구현 내용
1. 블록 그룹 구분하기 - [블록의 크기, 무지개 블록의 수, 기준 블록의 (col, row), set(좌표)]
    * 이때 블록의 크기는 2 이상이어야 한다.
2. 제거할 블록 그룹 찾기 - 블록 그룹의 리스트를 탐색하면서 더 높은 값을 선택
3. 블록 그룹 제거하기 - 해당 요소를 EMPTY (-7)로 바꾸기
3. 중력 작용 후 변화 나타내기
    - 2차원 배열의 밑에서부터 iteration 하면서, 검정이 아닌 모든 블록(num > -1)에 대해
    아랫쪽으로 dfs를 하며 EMPTY가 아닌 요소 혹은 테두리를 만나면 그 위로 이동시킴.
4. 90도 반시계방향으로 회전하기
    for col in range(n):
        for row in range(n):
            new[col][row] = cur[row][n-1-col]
5. 중력 작용 후 변화 나타내기 - 3과 동일
6. 블록 그룹의 개수가 0이 될 때까지 1~5를 반복한다.

WA 이유: "블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서"
'''

import sys
from copy import deepcopy
from collections import deque
from typing import Set, Tuple, List


def input():
    return sys.stdin.readline().rstrip()


def main():
    EMPTY = -7
    BLACK = -1
    RAINBOW = 0

    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]

    n, m = map(int, input().split())
    board = [list(map(int, input().split())) for col in range(n)]

    def make_group(col: int, row: int) -> Set[Tuple[int, int]]:
        std_num = board[col][row]
        visit_set = set()
        visit_set.add((col, row))
        dq = deque([(col, row)])
        while dq:
            curr_block = dq.popleft()

            c, r = curr_block
            for i in range(4):
                nc, nr = c + dy[i], r + dx[i]
                if (nc, nr) not in visit_set and 0 <= nc < n and 0 <= nr < n and board[nc][nr] in [std_num, RAINBOW]:
                    visit_set.add((nc, nr))
                    dq.append((nc, nr))
        return visit_set

    def divide_group():
        for col in range(n):
            for row in range(n):
                if 1 <= board[col][row] <= m and (col, row) not in grouped_set:
                    group = make_group(col, row)
                    if len(group) < 2:
                        continue
                    num_rainbow = 0
                    std_block = (n, n)
                    for c, r in group:
                        if board[c][r] == RAINBOW:
                            num_rainbow += 1
                        else:
                            grouped_set.add((c, r))
                            std_block = min(std_block, (c, r))
                    group_list.append([len(group), num_rainbow, std_block, group])

    def erase_one() -> int:
        priority = max(group_list)
        score, target_group = priority[0] ** 2, priority[3]
        for c, r in target_group:
            board[c][r] = EMPTY
        return score

    def drop_block(col: int, row: int):
        c = col + 1
        while 0 <= c < n and board[c][row] == EMPTY:
            c += 1
        board[c-1][row], board[col][row] = board[col][row], board[c-1][row]

    def apply_gravity():
        for col in range(n-1, -1, -1):
            for row in range(n):
                if board[col][row] not in (BLACK, EMPTY):
                    drop_block(col, row)

    def rotate() -> List[List[int]]:
        new_board = deepcopy(board)
        for col in range(n):
            for row in range(n):
                new_board[col][row] = board[row][n-1-col]
        return new_board

    total_score = 0
    while True:
        grouped_set = set()
        group_list = []
        divide_group()
        if len(group_list) == 0:
            break

        total_score += erase_one()
        apply_gravity()
        board = rotate()
        apply_gravity()
    print(total_score)


if __name__ == '__main__':
    main()

