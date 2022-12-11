from sys import stdin
from itertools import combinations
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M, K = map(int, input().split())
    run_times = list(map(int, input().split()))
    edge_list = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, K, run_times, edge_list


def solution(N: int, M: int, K: int, run_times: List[int], edge_list: List[Tuple[int, int]]) -> int:
    ind_cnts = [0 for _ in range(N + 1)]
    outd_lists = [[] for _ in range(N + 1)]
    for s, e in edge_list:
        ind_cnts[e] += 1
        outd_lists[s].append(e)
    START = 1
    END = -1
    for i in range(2, N + 1):
        if not outd_lists[i]:
            END = i
            break
    zero_cdd = list(range(2, END)) + list(range(END + 1, N + 1))
    answer = int(1e9)
    for zero_costs in combinations(zero_cdd, K):
        costs = [0] + run_times
        for i in zero_costs:
            costs[i] = 0
        dp = [0 for _ in range(N + 1)]
        dp[START] = costs[START]
        q = deque([START])
        inds = ind_cnts[:]
        while q:
            node = q.popleft()
            for outd in outd_lists[node]:
                inds[outd] -= 1
                dp[outd] = max(dp[outd], dp[node] + costs[outd])
                if inds[outd] == 0:
                    q.append(outd)
        answer = min(answer, dp[END])
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
