import sys


input = sys.stdin.readline


def solution():
    num_list.sort()
    start = 0
    end = len(num_list) - 1
    best_sum = abs(num_list[start] + num_list[end])
    best_start, best_end = start, end
    while start != end - 1:
        if abs(num_list[start + 1] + num_list[end]) <= abs(num_list[start] + num_list[end - 1]):
            start += 1
        else:
            end -= 1
        current_sum = abs(num_list[start] + num_list[end])
        if current_sum < best_sum:
            best_sum = current_sum
            best_start, best_end = start, end
            if best_sum == 0:
                break

    print(num_list[best_start], num_list[best_end])


if __name__ == '__main__':
    n = int(input())
    num_list = list(map(int, input().split()))
    solution()
