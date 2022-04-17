"""
투 포인터를 이용하자.
양 끝점의 최솟값이 최대가 되도록 투 포인터를 움직이자.

[1차 채점] WA.
반례) 150 50 30 50 150 1 10
투 포인터 중 값이 더 작은 것을 버리도록 이동하는게 맞겠다.
"""
import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    stats = list(map(int, input().split()))

    lp, rp = 0, n - 1
    max_res = 0
    while lp < rp:
        max_res = max(max_res, (rp - lp - 1) * min(stats[lp], stats[rp]))
        if stats[lp] > stats[rp]:
            rp -= 1
        else:
            lp += 1
    print(max_res)


if __name__ == '__main__':
    main()
