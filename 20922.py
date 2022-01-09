import sys
from collections import deque

input = sys.stdin.readline
MAX_A = 100000


def solution(n, k, a_list):
    elements_cnt = dict()
    for i in range(1, MAX_A + 1):
        elements_cnt[i] = 0
    lcs = deque()  # longest continuous subsequence
    max_length = len(lcs)
    for i in range(n):
        new_num = a_list[i]
        lcs.append(new_num)
        elements_cnt[new_num] += 1
        if elements_cnt[new_num] > k:
            while True:
                removing_num = lcs.popleft()
                elements_cnt[removing_num] -= 1
                if removing_num == new_num:
                    break
        max_length = max(max_length, len(lcs))
    return max_length


if __name__ == '__main__':
    n, k = map(int, input().split())
    a_list = list(map(int, input().split()))
    sol = solution(n, k, a_list)
    print(sol)