import sys


input = sys.stdin.readline


def solution():
    nums.sort()

    start, end = 0, n - 1
    best_val = abs(nums[start] + nums[end])
    best_start, best_end = start, end
    while start != end - 1:
        val_1 = abs(nums[start+1] + nums[end])
        val_2 = abs(nums[start] + nums[end-1])
        if val_1 <= val_2:
            start += 1
        else:
            end -= 1
        current_val = abs(nums[start] + nums[end])
        if current_val < best_val:
            best_val = current_val
            best_start, best_end = start, end

    print(nums[best_start], nums[best_end])


if __name__ == '__main__':
    n = int(input())
    nums = list(map(int, input().split()))
    solution()
