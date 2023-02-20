from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    cur = input()
    tgt = input()
    return N, cur, tgt


def solution(N: int, cur: str, tgt: str) -> int:
    INF = int(1e9)
    dp = [[[INF for k in range(2)] for j in range(2)] for i in range(N)]
    dp[0][0][0] = 0
    dp[0][0][1] = 1
    
    for i in range(N - 1):
        switch = int(cur[i] != tgt[i])
        for j in range(2):
            for k in range(2):
                if dp[i][j][k] == INF:
                    continue
                if (j + k) % 2 == switch: 
                    dp[i + 1][k][0] = min(dp[i + 1][k][0], dp[i][j][k])
                else:                     
                    dp[i + 1][k][1] = min(dp[i + 1][k][1], dp[i][j][k] + 1)
                    
    ans = INF
    switch = int(cur[N - 1] != tgt[N - 1])
    for j in range(2):
        for k in range(2):
            if dp[N - 1][j][k] == INF:
                continue
            if (j + k) % 2 == switch:
                ans = min(ans, dp[N - 1][j][k])

    return -1 if ans == INF else ans


if __name__ == '__main__':
    print(solution(*read_input()))
