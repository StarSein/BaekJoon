import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    interval_list = []
    for inp in range(n):
        h, o = map(int, input().split())
        if h > o:
            h, o = o, h
        interval_list.append((o, h))
    d = int(input())

    interval_list.sort()
    start_heap = []
    max_num = 0
    for end, start in interval_list:
        heapq.heappush(start_heap, start)
        while len(start_heap) and start_heap[0] < end - d:
            heapq.heappop(start_heap)
        max_num = max(max_num, len(start_heap))

    print(max_num)


main()
