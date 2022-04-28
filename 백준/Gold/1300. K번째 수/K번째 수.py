import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def count_below(num: int) -> int:
        return sum(min(n, (num-1)//i) for i in range(1, n + 1))

    n = int(input())
    k = int(input())

    left, right = 0, n ** 2
    while left <= right:
        mid = left + (right - left) // 2
        if count_below(mid) < k:
            left = mid + 1
        else:
            right = mid - 1
    print(right)


if __name__ == '__main__':
    main()
