from sys import stdin, setrecursionlimit


setrecursionlimit(int(1e6))


def input():
    return stdin.readline().rstrip()


def read_input() -> str:
    return input()


def solution(s: str) -> int:
    sz = len(s)
    n = sz // 2
    INIT = -1
    INF = 4
    dp = [[[INIT for k in range(4)] for j in range(4)] for i in range(n + 1)]

    def get_dp(i: int, j: int, k: int) -> int:
        if dp[i][j][k] != INIT:
            return dp[i][j][k]
        if i == n:
            dp[i][j][k] = j + k
            return j + k
        ret = INF
        for l in range(4):
            for r in range(4):
                if j + l + k + r <= 3 and s[i + j + l] == s[sz - 1 - i - k - r]:
                    ret = min(ret, get_dp(i + 1, j + l, k + r))
        dp[i][j][k] = ret
        return ret

    ans = get_dp(0, 0, 0)
    return ans if ans != INF else -1


if __name__ == '__main__':
    print(solution(read_input()))
