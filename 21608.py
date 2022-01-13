import sys
from collections import deque
from typing import Tuple


input = sys.stdin.readline
# 교실을 2차원 배열로 나타낸 room에서
EMPTY = 0   # 비어있는 자리를 나타내는 원소 0
WALL = -1   # 벽(경계선)을 나타내는 원소 -1
# 좌표를 나타낸 Tuple의 인덱스에 부여한 별칭
COL = 0
ROW = 1
# i = 0 ~ 3으로 iteration 하면서 해당 좌표의 아랫쪽, 오른쪽, 위쪽, 왼쪽 인접 좌표를 확인하기 위한 dx, dy 배열
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
# 좋아하는 사람의 수를 key, 점수를 value로 하는 딕셔너리
score_table = {0: 0,
               1: 1,
               2: 10,
               3: 100,
               4: 1000}


def solution() -> int:
    # 행이나 열 인덱스가 0 또는 n+1인 좌표에는 -1을 저장해 벽(경계선)이라고 표시함
    for col in range(n+2):
        room[col][0] = WALL
        room[col][n+1] = WALL
    for row in range(n+2):
        room[0][row] = WALL
        room[n+1][row] = WALL

    while len(student_queue):   # queue 사용: 입력받은 학생 순서대로 반복함
        student = student_queue.popleft()

        best_seat = (0, 0)
        cpr_prefer, cpr_empty = -1, -1  # best_seat 에 해당되는 '선호하는 사람 수'와 '비어있는 좌석 수'
                                        # 초기값을 0보다 작은 값으로 해야 logic error 발생하지 않음
        for col in range(1, n+1):
            for row in range(1, n+1):
                if room[col][row]:      # 해당 좌표에 이미 학생이 있는 경우
                    continue            # 건너뜀

                num_prefer, num_empty = cnt_prefer_empty(col, row, student)
                if num_prefer > cpr_prefer or (num_prefer == cpr_prefer and num_empty > cpr_empty): # 조건 1, 2를 순서대로 적용
                                                                                                    # 조건 3은 line 39, 40의 iteration 순서에 반영되어 있음
                    best_seat = (col, row)
                    cpr_prefer, cpr_empty = num_prefer, num_empty
        
        room[best_seat[COL]][best_seat[ROW]] = student  # 최적의 좌석에 학생을 위치시킴

    total_score = 0
    for col in range(1, n+1):
        for row in range(1, n+1):
            total_score += eval_score(col, row)

    return total_score


def cnt_prefer_empty(col: int, row: int, student: int) -> Tuple[int, int]:  # '선호하는 사람 수'와 '비어있는 좌석 수'를 세는 함수
    cnt_prefer, cnt_empty = 0, 0
    for i in range(4):  # 해당 좌표의 아래쪽, 오른쪽, 위쪽, 왼쪽 인접 좌표 순으로 iteration 과정에서 adjacent_seat 에 할당됨
        adjacent_seat = room[col + dy[i]][row + dx[i]]
        if adjacent_seat == EMPTY:    # 인접 좌표가 비어있을 경우
            cnt_empty += 1
        elif adjacent_seat == WALL:   # 인접 좌표가 벽(경계선)일 경우
            continue
        elif adjacent_seat in pref_table[student]:  # 인접 좌표에 있는 학생이 '선호하는 사람' 일 경우
            cnt_prefer += 1
    return cnt_prefer, cnt_empty


def eval_score(col: int, row: int) -> int:  # 개별 학생의 만족도를 산정하는 함수
    student = room[col][row]
    cnt_prefer, cnt_empty = cnt_prefer_empty(col, row, student)
    score = score_table[cnt_prefer]
    return score


if __name__ == '__main__':
    n = int(input())
    pref_table = dict()     # key: 학생, value: 선호하는 학생 리스트
    student_queue = deque() # 학생이 입력되는 순서대로 담는 deque
    for i in range(n**2):
        inp = list(map(int, input().split()))
        student = inp[0]
        preferences = inp[1:]
        pref_table[student] = preferences
        student_queue.append(student)
    room = [[0 for row in range(n + 2)] for col in range(n + 2)]    # 교실: 0과 n+1 인덱스는 벽(경계선)으로 두고, 나머지 1~n 인덱스를 좌표로 표현함
    sol = solution()
    print(sol)
