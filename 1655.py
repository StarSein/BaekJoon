import sys
import heapq

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    mid = int(sys.stdin.readline())
    print(mid)
    left, right = [], []
    for i in range(1, N):
        num = int(sys.stdin.readline())
        if num >= mid:
            heapq.heappush(left, num)
        else:
            heapq.heappush(right, (-num, num))
        if len(left) == len(right) + 2:
            heapq.heappush(right, (-mid, mid))
            mid = heapq.heappop(left)
        elif len(left) + 2 == len(right):
            heapq.heappush(left, mid)
            mid = heapq.heappop(right)[1]
        else:
            pass

        if len(left) == len(right):
            print(mid)
        elif len(left) + 1 == len(right):
            print(min(mid, right[0][1]))
        else:
            print(min(mid, left[0]))
