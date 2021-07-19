import sys
import heapq

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    heap = []
    for cnt in range(1, N + 1):
        heapq.heappush(heap, int(sys.stdin.readline()))
        stack = []
        for i in range((cnt + 1) // 2):
            stack.append(heapq.heappop(heap))
        print(stack[-1])
        for i in range(len(stack)):
            heapq.heappush(heap, stack.pop())
