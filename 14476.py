import sys


def input():
    return sys.stdin.readline().rstrip()


def gcd(a: int, b: int) -> int:
    while b != 0:
        tmp = a % b
        a, b = b, tmp
    return a


def main():
    n = int(input())
    nums = list(map(int, input().split()))
    prefix_gcd = [0]
    suffix_gcd = [0] * (n + 1)
    for i in range(n):
        prefix_gcd.append(gcd(prefix_gcd[-1], nums[i]))
    for i in range(n-1, -1, -1):
        suffix_gcd[i] = gcd(suffix_gcd[i+1], nums[i])
    best_gcd = 0
    best_num = 0
    for idx, num in enumerate(nums):
        current_gcd = gcd(prefix_gcd[idx], suffix_gcd[idx+1])
        if num % current_gcd == 0:
            continue

        if current_gcd > best_gcd:
            best_gcd = current_gcd
            best_num = num
    if best_num:
        print(best_gcd, best_num)
    else:
        print(-1)


main()
