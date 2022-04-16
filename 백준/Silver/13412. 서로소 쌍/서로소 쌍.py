import sys
from typing import List


def input():
    return sys.stdin.readline().rstrip()


def main():
    def divisors(_n: int) -> List[int]:
        return [num for num in range(1, int(_n ** (1/2)) + 1) if _n % num == 0]

    def gcd(a: int, b: int) -> int:
        while b > 0:
            a, b = b, a % b
        return a

    t = int(input())
    for tc in range(t):
        n = int(input())
        print(len([num for num in divisors(n) if gcd(num, n // num) == 1]))


if __name__ == '__main__':
    main()
