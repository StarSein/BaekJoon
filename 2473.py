import sys


input = sys.stdin.readline
MAX_VAL = 1_000_000_000 * 3


def solution():
    nums.sort()

    best_val = MAX_VAL
    is_zero = False
    for i in range(n - 2):
        std = nums[i]
        left, right = i + 1, n - 1
        current_val = abs(std + nums[left] + nums[right])
        if current_val < best_val:
            best_val = current_val
            best_std, best_l, best_r = std, left, right

        while left != right - 1:
            val_1 = abs(std + nums[left+1] + nums[right])
            val_2 = abs(std + nums[left] + nums[right-1])
            if val_1 <= val_2:
                left += 1
            else:
                right -= 1
            current_val = abs(std + nums[left] + nums[right])
            if current_val < best_val:
                best_val = current_val
                best_std, best_l, best_r = std, left, right
                if best_val == 0:
                    is_zero = True
                    break
        if is_zero:
            break

    print(best_std, nums[best_l], nums[best_r])


if __name__ == '__main__':
    n = int(input())
    nums = list(map(int, input().split()))
    solution()
