import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, w, l = map(int, input().split())
    weights = deque(map(int, input().split()))

    pass_weight_dq = deque()
    exit_time_dq = deque()
    sum_weight = 0
    time = 0
    while weights or pass_weight_dq:
        if exit_time_dq and time == exit_time_dq[0]:
            sum_weight -= pass_weight_dq.popleft()
            exit_time_dq.popleft()
        if weights and sum_weight + weights[0] <= l:
            curr_weight = weights.popleft()
            sum_weight += curr_weight
            pass_weight_dq.append(curr_weight)
            exit_time_dq.append(time + w)
        time += 1
    print(time)


if __name__ == '__main__':
    main()
