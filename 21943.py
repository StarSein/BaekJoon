import sys
from itertools import permutations, combinations


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    x_list = list(map(int, input().split()))
    p, q = map(int, input().split())

    mult_pos_list = list(combinations(range(1, n), q))
    max_res = 0
    for seq in permutations(x_list, n):
        for mult_pos in mult_pos_list:
            pos_set = set(mult_pos)
            curr_sum = seq[0]
            sum_list = []
            for pos in range(1, n):
                if pos in pos_set:
                    sum_list.append(curr_sum)
                    curr_sum = 0
                curr_sum += seq[pos]
            sum_list.append(curr_sum)

            curr_res = 1
            for s in sum_list:
                curr_res *= s

            max_res = max(max_res, curr_res)

    print(max_res)


if __name__ == '__main__':
    main()
