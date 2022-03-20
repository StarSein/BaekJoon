import sys
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, k = map(int, input().split())
    x_list = list(map(int, input().split()))
    cnt_call = defaultdict(int)
    for x in x_list:
        cnt_call[x] += 1

    a_list = [0] * n
    for x, cnt in cnt_call.items():
        for i in range(0, n, x):
            a_list[i] += cnt

    prefix_sum = [0]
    for a in a_list:
        prefix_sum.append(prefix_sum[-1] + a)

    q = int(input())
    for query in range(q):
        l, r = map(int, input().split())
        print(prefix_sum[r+1] - prefix_sum[l])


if __name__ == '__main__':
    main()
