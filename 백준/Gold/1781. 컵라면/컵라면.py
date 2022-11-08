"""
단위 시간 1의 시점에는 데드라인이 1인 문제뿐만 아니라 6인 문제도 선택될 수 있다.
반면 단위 시간 6의 시점에는 데드라인이 1인 문제가 고려되지 않는다.
따라서 데드라인이 긴 것부터 '컵라면 수의 내림차순으로 정렬되는 힙'에 넣어서 처리하면 된다
"""
from sys import stdin
from typing import Tuple, List
import heapq

input = lambda: stdin.readline().rstrip()


def solution(n: int, probs: List[Tuple[int, int]]) -> int:
    probs.sort(key=lambda x: -x[0])
    max_time = probs[0][0]
    heap = []

    ans = 0

    idx = 0
    for time in range(max_time, 0, -1):
        while idx < n and probs[idx][0] == time:
            heapq.heappush(heap, (-probs[idx][1], probs[idx][1]))
            idx += 1
        if heap:
            ans += heapq.heappop(heap)[1]

    return ans


if __name__ == '__main__':
    N = int(input())
    print(solution(N, [tuple(map(int, input().split())) for _ in range(N)]))
