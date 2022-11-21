from sys import stdin


input = lambda: stdin.readline().rstrip()


def solution(n: int) -> str:
    mod = n % 5
    if mod == 0 or mod == 2:
        return "CY"
    else:
        return "SK"


if __name__ == '__main__':
    N = int(input())
    print(solution(N))

