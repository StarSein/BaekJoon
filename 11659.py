import sys
from collections import deque
from typing import List

input = sys.stdin.readline


def solution() -> List[str]:
    res_list = []
    prefix_sums = [0]
    for idx, val in enumerate(num_list):
        prefix_sums.append(prefix_sums[-1] + val)
    while len(requests):
        l, r = requests.popleft()
        interval_sum = prefix_sums[r] - prefix_sums[l-1]
        res_list.append(str(interval_sum))
    return res_list


if __name__ == '__main__':
    n, m = map(int, input().split())
    num_list = list(map(int, input().split()))
    requests = deque([tuple(map(int, input().split())) for i in range(m)])
    sol = solution()
    print('\n'.join(sol))
