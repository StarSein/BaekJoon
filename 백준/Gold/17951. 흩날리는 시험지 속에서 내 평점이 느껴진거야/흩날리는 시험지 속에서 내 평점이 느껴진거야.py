import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def is_obtainable(score: int) -> bool:
        seg_score = 0
        cnt_seg = 0
        for num_solve in num_solves:
            seg_score += num_solve
            if seg_score >= score:
                cnt_seg += 1
                seg_score = 0
        return cnt_seg >= k

    n, k = map(int, input().split())
    num_solves = list(map(int, input().split()))
    left, right = 0, 20 * n
    while left <= right:
        mid = left + (right - left) // 2
        if is_obtainable(mid):
            left = mid + 1
        else:
            right = mid - 1
    print(right)


if __name__ == '__main__':
    main()
