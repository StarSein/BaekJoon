"""
[Sol3]
이번엔 정말 확실하게 O(NlogN)이다.

1. 최솟값 세그먼트 트리를 만들어 놓자.
2. 모든 단말 노드[FIRST_LEAF:FIRST_LEAF+n]에 대해, 해당 최솟값을 높이로 하는 직사각형의 너비를 계산한다.
    1) 단말 노드를 기준으로 직사각형의 왼쪽 끝점과 오른쪽 끝점을 찾는다.
        (1) 왼쪽은 (idx-1), 오른쪽은 (idx+1) 부터 탐색 시작.
        (2) 양측 탐색 모두 각각 min_seg[idx] < height이 될 때까지,
            좌측 탐색은 idx -= 1, idx >>= 1
            우측 탐색은 idx += 1, idx >>= 1
        (3) 좌측 탐색에서는 오른쪽 자식부터 min_seg[idx] >= height 여부를 확인하며,
            min_seg[idx] < height인 노드에 대해 같은 작업을 재귀적으로 한다. (반복문으로 구현하자)
        (4) 우측 탐색에서는 왼쪽 자식부터 min_seg[idx] >= height 여부를 확인하며,
            min_seg[idx] < height인 노드에 대해 같은 작업을 재귀적으로 한다. (반복문으로 구현하자)
        (5) 작업 (3),(4)에서 단말 노드에 도착할 경우 그 idx값을 반환한다.
    2) 이때 양쪽 끝점을 찾을 때 각 층의 양쪽 끝 노드를 방문하는 경우를 따로 처리해야 한다.
    3) 직사각형의 너비 = right_idx - left_idx + 1
"""
import sys
from math import ceil, log2


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_min_seg_tree():
        for idx, height in enumerate(heights, start=FIRST_LEAF):
            min_seg[idx] = height

        for idx in range(FIRST_LEAF - 1, 0, -1):
            min_seg[idx] = min(min_seg[idx << 1], min_seg[(idx << 1) + 1])

    def get_leftbound(idx: int, height: int) -> int:
        if idx & (idx - 1) == 0 or min_seg[idx-1] < height:
            return idx
        last_idx = idx
        idx -= 1

        while idx >= 1:
            last_idx = idx
            if min_seg[idx] < height or idx & (idx - 1) == 0:
                break
            idx -= 1
            idx >>= 1

        idx = (last_idx << 1) + 1
        while idx < TREE_SIZE:
            if min_seg[idx] >= height:
                idx -= 1
            idx = (idx << 1) + 1
        idx >>= 1
        return idx if min_seg[idx] >= height else idx + 1

    def get_rightbound(idx: int, height: int) -> int:
        if idx & (idx + 1) == 0 or min_seg[idx+1] < height:
            return idx
        last_idx = idx
        idx += 1
        while idx >= 1:
            last_idx = idx
            if min_seg[idx] < height or idx & (idx + 1) == 0:
                break
            idx += 1
            idx >>= 1

        idx = last_idx << 1
        while idx < TREE_SIZE:
            if min_seg[idx] >= height:
                idx += 1
            idx = idx << 1
        idx >>= 1
        return idx if min_seg[idx] >= height else idx - 1

    def get_width(idx: int, height: int) -> int:
        return get_rightbound(idx, height) - get_leftbound(idx, height) + 1

    def get_large_area() -> int:
        return max([height * get_width(idx, height) for idx, height in enumerate(heights, start=FIRST_LEAF)])

    while True:
        n, *heights = map(int, input().split())
        if n == 0:
            return

        LOG = ceil(log2(n)) + 1
        TREE_SIZE = 1 << LOG
        FIRST_LEAF = TREE_SIZE >> 1
        min_seg = [0] * TREE_SIZE

        make_min_seg_tree()

        print(get_large_area())


if __name__ == '__main__':
    main()
