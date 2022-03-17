import sys
from math import comb
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    a_list = list(map(int, input().split()))
    prefix_sums = [0]
    for a in a_list:
        prefix_sums.append(prefix_sums[-1] + a)
    cnt_modulo = defaultdict(int)
    for pref_sum in prefix_sums:
        cnt_modulo[pref_sum % m] += 1
    answer = sum([comb(cnt_modulo[mod], 2) for mod in cnt_modulo.keys()])
    print(answer)


if __name__ == '__main__':
    main()
