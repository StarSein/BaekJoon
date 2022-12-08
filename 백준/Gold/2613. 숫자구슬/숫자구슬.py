from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    nums = list(map(int, input().split()))
    return N, M, nums


def solution(N: int, M: int, nums: List[int]):
    def case_exist(limit: int) -> bool:
        group_cnt = 1
        cur_sum = 0
        for num in nums:
            if num > limit:
                return False
            elif cur_sum + num > limit:
                group_cnt += 1
                cur_sum = num
            else:
                cur_sum += num
        return group_cnt <= M

    def find_case(limit: int) -> List[int]:
        case = []
        cur_cnt = 0
        cur_sum = 0
        for num in nums:
            if cur_sum + num > limit:
                case.append(cur_cnt)
                cur_cnt = 1
                cur_sum = num
            else:
                cur_cnt += 1
                cur_sum += num
        case.append(cur_cnt)
        if len(case) == M:
            return case
        deficit = M - len(case)
        idx = len(case) - 1
        while deficit:
            if case[idx] > 1:
                case[idx] -= 1
                case.insert(idx, 1)
                deficit -= 1
            else:
                idx -= 1
        return case

    MIN_SUM, MAX_SUM = 1, 30_000
    left, right = MIN_SUM, MAX_SUM
    answer = -1
    while left <= right:
        mid = (left + right) >> 1
        if case_exist(mid):
            answer = mid
            right = mid - 1
        else:
            left = mid + 1
    print(answer)
    print(*find_case(answer))


if __name__ == '__main__':
    solution(*read_input())
