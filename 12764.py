'''
두 개의 힙, 그리고 결과를 담을 한 개의 리스트를 사용하자
N개의 (p, is_start=True, q)를 힙1에 넣는다
힙1을 iteration하면서 좌석을 배정하는데, is_start == True일 경우
(q, False, 배정된 좌석)을 힙에 넣는다
현재 힙2에 요소가 없을 경우 num_pc + 1을 좌석으로 배정하며 결과 리스트에 1을 추가한다
현재 힙2에 요소가 있을 경우 최솟값을 반환받아 좌석으로 배정하며 결과 리스트의 해당 요소에 1을 더한다
is_start == False일 경우 힙2에 해당 좌석을 추가한다
'''

import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    entry_heap = []
    for person in range(n):
        p, q = map(int, input().split())
        entry_heap.append((p, True, q))
    heapq.heapify(entry_heap)

    cnt_usage = []
    seat_heap = []
    while entry_heap:
        is_start = entry_heap[0][1]
        if is_start:
            start_time, is_start, end_time = heapq.heappop(entry_heap)
            if seat_heap:
                proper_seat = heapq.heappop(seat_heap)
                cnt_usage[proper_seat] += 1
            else:
                proper_seat = len(cnt_usage)
                cnt_usage.append(1)
            heapq.heappush(entry_heap, (end_time, False, proper_seat))
        else:
            end_time, is_start, used_seat = heapq.heappop(entry_heap)
            heapq.heappush(seat_heap, used_seat)
    print(len(cnt_usage))
    print(*cnt_usage)


if __name__ == '__main__':
    main()
