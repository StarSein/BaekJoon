import sys
from itertools import permutations


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    x_list = list(map(int, input().split()))
    p, q = map(int, input().split())

    max_res = 0
    for seq in permutations(x_list, n):
        sum_list = [0] * (q + 1)

        for num in seq:
            pos = sum_list.index(min(sum_list))
            sum_list[pos] += num

        curr_res = sum_list[0]
        for i in range(1, q + 1):
            curr_res *= sum_list[i]

        max_res = max(max_res, curr_res)

    print(max_res)


if __name__ == '__main__':
    main()
