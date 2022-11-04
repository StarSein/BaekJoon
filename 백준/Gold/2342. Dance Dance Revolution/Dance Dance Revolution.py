"""
왼발, 오른발의 상태는 5P2 = 20 가지 경우의 수가 있다.
그런데 왼발, 오른발의 구분이 필요하지 않으므로 처리할 경우의 수는 5C2 = 10 이다.

튜플을 인덱스로 변환하고, 인덱스를 튜플로 변환하는 작업을 하는 방법도 있겠지만,
(높은 번호, 낮은 번호)를 key로 하는 딕셔너리를 이용하는 것이 좋겠다.

try 1) WA
원인: cost_move 함수에서 발을 반대편으로 움직일 때 힘의 크기로 3을 반환한다.
해결: 4로 수정
"""
from sys import stdin
from collections import defaultdict
from typing import List

input = lambda: stdin.readline().rstrip()


def cost_move(prev: int, cur: int) -> int:
    if prev == cur:
        return 1
    elif prev == 0:
        return 2
    elif (prev + cur) % 2 != 0:
        return 3
    else:
        return 4


def solution(cmds: List[int]) -> int:
    if len(cmds) == 0:
        return 0

    INF = int(1e8)

    dp = [defaultdict(lambda: INF) for _ in range(len(cmds))]
    dp[0][(cmds[0], 0)] = 2

    for i in range(1, len(cmds)):
        prev_dict = dp[i - 1]
        cmd = cmds[i]

        for key, val in prev_dict.items():
            high, low = key
            next_key = tuple(sorted((cmd, low)))
            dp[i][next_key] = min(dp[i][next_key], dp[i - 1][key] + cost_move(high, cmd))

            next_key = tuple(sorted((high, cmd)))
            dp[i][next_key] = min(dp[i][next_key], dp[i - 1][key] + cost_move(low, cmd))

    n = len(cmds)
    ans = min(dp[n - 1].values())
    return ans


if __name__ == '__main__':
    cmd_list = list(map(int, input().split()))
    cmd_list.pop()
    print(solution(cmd_list))
