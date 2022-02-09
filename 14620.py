import sys


input = sys.stdin.readline
NUM_SEED = 3
MAX_PRICE = 200 * 5 * 3

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def solution():
    global best_cost
    make_spot_list()            # spot_list에 원소를 채움 (line 66 참조)
    best_cost = MAX_PRICE
    backtrack(0, 0)             # 씨앗을 심을 좌표를 넣었다 뺐다 하면서 최소 비용을 갱신함 (line 34 참조)
    print(best_cost)


def make_spot_list():                           # spot_list에 원소를 채우는 함수 (line 66 참조)
    for col in range(1, n - 1):                 # 씨앗이 화단의 외곽에 있어서는 안 되므로 좌표의 범위를 [1, n-1)로 설정함
        for row in range(1, n - 1):
            cost = calc_cost(col, row)          # (col, row)와 상하좌우 좌표의 가격을 다 더한 값을 구함
            spot_list.append((cost, col, row))


def calc_cost(col: int, row: int):              # (col, row)와 상하좌우 좌표의 가격을 다 더한 값을 구하는 함수
    cost = matrix[col][row]
    for i in range(len(dx)):
        cost += matrix[col + dy[i]][row + dx[i]]
    return cost


def backtrack(pos: int, total_cost: int):           # 매개변수 pos: spot_list 내에서 현재 포함 여부를 결정하는 인덱스
    global best_cost                                # 매개변수 total_cost: 현재까지의 총 가격
    if len(spots_chosen) == NUM_SEED:               # 씨앗을 심을 좌표 3개를 정하고 나면 best_cost와 비교해서 최솟값을 갱신함
        best_cost = min(best_cost, total_cost)
        return

    if pos == len(spot_list):                       # pos가 상한값을 초과하면 재귀 호출을 종료함
        return

    cost, nc, nr = spot_list[pos]
    is_touched = False                              # 새로 추가할 좌표가 기존의 좌표들과 충돌하는지 여부를 나타내는 변수
    for c, r in spots_chosen:
        is_touched = check_touched(c, r, nc, nr)    # 기존의 좌표들에 대해 충돌 여부를 확인한다
        if is_touched:
            break
    if not is_touched:                              # 새로운 좌표가 충돌을 일으키지 않을 경우
        spots_chosen.append((nc, nr))               # 해당 좌표를 선택한 좌표들의 목록에 추가하고
        backtrack(pos + 1, total_cost + cost)       # 해당 좌표의 비용을 더한 spot_list의 다음 인덱스를 탐색한다
        spots_chosen.pop()                          # 탐색한 다음 해당 좌표를 목록에서 제거한다
    backtrack(pos + 1, total_cost)                  # 해당 좌표를 추가하지 않고 spot_list의 다음 인덱스를 탐색한다


def check_touched(c1: int, r1: int, c2: int, r2: int) -> bool:  # 두 좌표의 꽃이 서로 겹치는지 여부를 확인하는 함수
    if abs(c1 - c2) + abs(r1 - r2) < 3:                         # 두 좌표의 맨해튼 거리가 3 미만이면 서로 겹치고
        return True
    else:                                                       # 두 좌표의 맨해튼 거리가 3 이상이면 서로 겹치지 않는다
        return False


if __name__ == '__main__':
    n = int(input())
    matrix = [list(map(int, input().split())) for col in range(n)]  # 화단의 지점당 가격을 담은 2차원 배열
    spot_list = []              # 원소: Tuple(해당 좌표에 씸는 씨앗의 비용, 행 인덱스, 열 인덱스)
    spots_chosen = []           # 백트래킹 과정에서 씨앗을 심을 좌표의 삽입과 삭제가 이뤄지는 배열
    solution()
