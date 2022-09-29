from sys import stdin
from typing import List


input = lambda: stdin.readline().rstrip()


def gcd(a: int, b: int) -> int:
    while b:
        tmp = a
        a = b
        b = tmp % b
    return a


def solution() -> List[int]:
    N = int(input())
    nums = list(map(int, input().split()))

    prf_gcd = [0] * N
    prf_gcd[0] = nums[0]
    for i in range(1, N):
        prf_gcd[i] = gcd(prf_gcd[i-1], nums[i])

    suf_gcd = [0] * N
    suf_gcd[-1] = nums[-1]
    for i in range(N - 2, -1, -1):
        suf_gcd[i] = gcd(suf_gcd[i+1], nums[i])

    max_gcd, k = -1, -1
    if nums[0] % suf_gcd[1] and suf_gcd[1] > max_gcd:
        max_gcd = suf_gcd[1]
        k = nums[0]
    if nums[-1] % prf_gcd[-2] and prf_gcd[-2] > max_gcd:
        max_gcd = prf_gcd[-2]
        k = nums[-1]

    for i in range(1, N - 1):
        cur_gcd = gcd(prf_gcd[i-1], suf_gcd[i+1])
        if nums[i] % cur_gcd and cur_gcd > max_gcd:
            max_gcd = cur_gcd
            k = nums[i]

    if max_gcd == -1:
        return [max_gcd]
    else:
        return [max_gcd, k]


if __name__ == '__main__':
    print(*solution())
