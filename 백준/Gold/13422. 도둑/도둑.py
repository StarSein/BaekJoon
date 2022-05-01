"""
[1차 채점] WA.
M개의 연속된 집을 고르는 방법은 '조합'이지 '순열'이 아니다.
"""

import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def get_num_steal() -> int:
        if n > m:
            moneys.extend(moneys[:m-1])
        cnt = 0
        curr_sum = sum(moneys[0:m])
        if curr_sum < k:
            cnt += 1
        for lp, rp in zip(range(len(moneys)), range(m, len(moneys))):
            curr_sum += (moneys[rp] - moneys[lp])
            if curr_sum < k:
                cnt += 1
        return cnt

    t = int(input())
    for tc in range(t):
        n, m, k = map(int, input().split())
        moneys = list(map(int, input().split()))
        print(get_num_steal())


if __name__ == '__main__':
    main()
