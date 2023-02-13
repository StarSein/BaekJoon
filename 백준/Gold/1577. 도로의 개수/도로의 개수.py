from sys import stdin


def input():
    return stdin.readline().rstrip()


def solution() -> int:
    N, M = map(int, input().split())
    K = int(input())
    north_able = [[True for c in range(N + 1)] for r in range(M + 1)]
    west_able = [[True for c in range(N + 1)] for r in range(M + 1)]
    for _ in range(K):
        a, b, c, d = map(int, input().split())
        if (a, b) > (c, d):
            a, c = c, a
            b, d = d, b
        if a != c:
            west_able[d][c] = False
        elif b != d:
            north_able[d][c] = False

    for r in range(M + 1):
        west_able[r][0] = False
    for c in range(N + 1):
        north_able[0][c] = False
    
    dp = [[-1 for c in range(N + 1)] for r in range(M + 1)]
    dp[0][0] = 1
    
    def calc(r: int, c: int) -> int:
        if dp[r][c] != -1:
            return dp[r][c]
        if north_able[r][c] and west_able[r][c]:
            dp[r][c] = calc(r - 1, c) + calc(r, c - 1)
        elif north_able[r][c]:
            dp[r][c] = calc(r - 1, c)
        elif west_able[r][c]:
            dp[r][c] = calc(r, c - 1)
        else:
            dp[r][c] = 0
        return dp[r][c]

    return calc(M, N)


if __name__ == '__main__':
    print(solution())
