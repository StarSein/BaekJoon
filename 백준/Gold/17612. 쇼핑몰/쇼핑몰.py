from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, k = map(int, input().split())
    users = [tuple(map(int, input().split())) for _ in range(N)]
    return N, k, users


def solution(N: int, K: int, users: List[Tuple[int, int]]) -> int:
    heap = [(0, i) for i in range(1, K + 1)]
    deploy_list = []
    for user_id, pay_num in users:
        end_time, cashier_id = heapq.heappop(heap)

        end_time += pay_num
        deploy_list.append((end_time, cashier_id, user_id))

        heapq.heappush(heap, (end_time, cashier_id))

    deploy_list.sort(key= lambda x: (x[0], -x[1]))

    return sum(i * ui for i, (et, ci, ui) in enumerate(deploy_list, start=1))


if __name__ == '__main__':
    print(solution(*read_input()))
