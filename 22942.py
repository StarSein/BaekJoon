import sys
from collections import deque


input = sys.stdin.readline
MIN_X = -1_000_000


def solution() -> str:
    spot_deque = deque(sorted(spot_list))
    latest_x = MIN_X - 1
    while len(spot_deque):
        current_spot = spot_deque.popleft()
        current_x = current_spot[0]
        if latest_x == current_x:
            return "NO"

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

        latest_x = current_x

    return "YES"


if __name__ == '__main__':
    n = int(input())
    spot_list = []
    circle_stack = []
    for circle in range(n):
        x, r = map(int, input().split())
        spot_list.append((x - r, False, x + r))
        spot_list.append((x + r, True))
    sol = solution()
    print(sol)
