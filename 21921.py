import sys


input = sys.stdin.readline


def solution():
    current_sum = sum(num_list[:x])
    max_sum = current_sum
    cnt_max = 1
    i = 0
    while i + x < n:
        current_sum -= num_list[i]
        current_sum += num_list[i+x]
        if current_sum > max_sum:
            max_sum = current_sum
            cnt_max = 1
        elif current_sum == max_sum:
            cnt_max += 1
        i += 1
    res = f"{max_sum}\n{cnt_max}" if max_sum > 0 else "SAD"
    print(res)


if __name__ == '__main__':
    n, x = map(int, input().split())
    num_list = list(map(int, input().split()))
    solution()