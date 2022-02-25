import sys
from typing import List


sys.setrecursionlimit(10_000)


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    nums = list(map(int, input().split()))
    m = int(input())
    dp = [[-1 for row in range(n)] for col in range(n)]
    for idx in range(n):
        dp[idx][idx] = 1
    for query in range(m):
        s, e = map(int, input().split())
        print(is_palindrome(s - 1, e - 1, nums, dp))


def is_palindrome(s: int, e: int, nums: List[int], dp: List[List[int]]) -> int:
    if dp[s][e] != -1:
        return dp[s][e]
    
    if nums[s] == nums[e]:
        if s + 1 == e:
            dp[s][e] = 1
        else:
            dp[s][e] = is_palindrome(s + 1, e - 1, nums, dp)
    else:
        dp[s][e] = 0
    return dp[s][e]


if __name__ == '__main__':
    main()