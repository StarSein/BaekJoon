import sys
import heapq


input = sys.stdin.readline


def solution() -> str:
    heapq.heapify(min_heap)
    while len(min_heap):
        current_spot = heapq.heappop(min_heap)

        is_right = current_spot[1]
        if not is_right:
            right_pos = current_spot[2]
            circle_stack.append(right_pos)
        else:
            right_pos = current_spot[0]
            if right_pos != circle_stack[-1]:
                return "NO"
            else:
                circle_stack.pop()

    return "YES"


if __name__ == '__main__':
    n = int(input())
    min_heap = []
    circle_stack = []
    for circle in range(n):
        x, r = map(int, input().split())
        min_heap.append((x - r, False, x + r))
        min_heap.append((x + r, True))
    sol = solution()
    print(sol)
