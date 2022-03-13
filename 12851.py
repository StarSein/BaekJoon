import sys
from collections import deque
from collections import defaultdict
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def bfs(start: int, end: int) -> Tuple[int, int]:
    visit_deque = deque([(0, start)])
    min_time_dict = defaultdict(lambda: -1)
    cnt = 0
    while len(visit_deque):
        total_time, curr_node = visit_deque.popleft()

        if curr_node == end:
            if min_time_dict[end] == -1:
                min_time_dict[end] = total_time
                cnt = 1
            elif min_time_dict[end] == total_time:
                cnt += 1
            else:
                break
        else:
            if min_time_dict[curr_node] == -1:
                min_time_dict[curr_node] = total_time
            elif min_time_dict[curr_node] < total_time:
                continue

        if curr_node < end:
            visit_deque.append((total_time + 1, curr_node + 1))
            visit_deque.append((total_time + 1, curr_node * 2))
        if curr_node > 1:
            visit_deque.append((total_time + 1, curr_node - 1))

    return min_time_dict[end], cnt


def main():
    n, k = map(int, input().split())
    if n < k:
        answer = bfs(n, k)
    else:
        answer = (n - k, 1)
    print(answer[0], answer[1], sep='\n')


if __name__ == '__main__':
    main()
