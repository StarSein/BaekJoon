"""
누적합으로 각 지점의 위치를 표현할 수 있다.
두 개의 탑 사이의 최단 거리
    = min(시계 방향 거리, 반시계 방향 거리)
    = min(abs(tower1 - tower2), abs(tower1 + 둘레 - tower2))
언제 이 값이 최대가 될까?
시계 방향 거리 + 반시계 방향 거리 == 둘레 이므로
최단 거리 + (최단 거리 + 차) == 둘레
최단 거리 == (둘레 - 차) // 2
두 거리 사이의 차가 최소가 될 때 최단 거리가 최대가 된다.
그 지점은 이분 탐색으로 찾을 수 있겠다.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    MAX_DIST = int(1e9)
    n = int(input())
    dist_list = [int(input()) for _ in range(n)]
    perimeter = sum(dist_list)
    pos_list = [0]
    for i in range(n):
        pos_list.append(pos_list[-1] + dist_list[i])
    for i in range(n - 1):
        pos_list.append(pos_list[-1] + dist_list[i])

    def binary_search(std: int) -> int:
        left = std + 1
        right = std + n - 1
        min_abs_diff = MAX_DIST
        while left <= right:
            mid = left + (right - left) // 2
            diff = (pos_list[mid] - pos_list[std]) - (pos_list[std+n] - pos_list[mid])
            min_abs_diff = min(min_abs_diff, abs(diff))
            if diff < 0:
                left = mid + 1
            elif diff > 0:
                right = mid - 1
            else:
                min_abs_diff = 0
                break

        return (perimeter - min_abs_diff) // 2

    max_dist = 0
    for std_idx in range(n):
        max_dist = max(max_dist, binary_search(std_idx))

    print(max_dist)


if __name__ == '__main__':
    main()
