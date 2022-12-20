from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_input():
    w, h = map(int, input().split())
    x, y = map(int, input().split())
    return w, h, x, y


def solution(w: int, h: int, x: int, y: int) -> int:
    MOD = int(1e6 + 7)

    dp = [[-1 for j in range(w + h + 1)] for i in range(w + h + 1)]

    def comb(n: int, r: int) -> int:
        if r == n or r == 0:
            return 1
        if dp[n][r] != -1:
            return dp[n][r]
        dp[n][r] = (comb(n - 1, r) + comb(n - 1, r - 1)) % MOD
        return dp[n][r]
    
    num_case1 = comb(x + y - 2, x - 1)
    num_case2 = comb(w + h - x - y, w - x)
    answer = (num_case1 * num_case2) % MOD
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
