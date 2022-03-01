import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    x_list = list(map(int, input().split()))

    prefix_even_sum = [0]
    for even_idx in range(0, n, 2):
        prefix_even_sum.append(prefix_even_sum[-1] + x_list[even_idx])
    suffix_odd_sum = [0] * (n // 2 + 1)
    idx = -2
    for odd_idx in range(n - 1, 0, -2):
        suffix_odd_sum[idx] = suffix_odd_sum[idx+1] + x_list[odd_idx]
        idx -= 1

    max_sum = 0
    for i in range(n // 2 + 1):
        if x_list[2*i-1] <= x_list[-1]:
            max_sum = max(max_sum, prefix_even_sum[i] + suffix_odd_sum[i])
        else:
            max_sum = max(max_sum, prefix_even_sum[i] + suffix_odd_sum[i] + x_list[2*i-1] - x_list[-1])

    print(max_sum)


main()
