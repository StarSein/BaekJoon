import sys

sys.setrecursionlimit(5000)


def input():
    return sys.stdin.readline().rstrip()


def main():
    def calc_min_cost(col: int, row: int) -> int:
        if dp[col][row] != -1:
            return dp[col][row]
        cost_leftside, cost_upside = 0, 0
        if col > 0:
            gap_upside = matrix[col][row] - matrix[col-1][row]
            if gap_upside >= 0:
                cost_upside = gap_upside + 1
        if row > 0:
            gap_leftside = matrix[col][row] - matrix[col][row-1]
            if gap_leftside >= 0:
                cost_leftside = gap_leftside + 1
        if col == 0:
            dp[col][row] = calc_min_cost(col, row-1) + cost_leftside
        elif row == 0:
            dp[col][row] = calc_min_cost(col-1, row) + cost_upside
        else:
            dp[col][row] = min(calc_min_cost(col-1, row) + cost_upside, calc_min_cost(col, row-1) + cost_leftside)
        return dp[col][row]

    n = int(input())
    matrix = [list(map(int, input().split())) for col in range(n)]
    dp = [[-1 for row in range(n)] for col in range(n)]
    dp[0][0] = 0
    print(calc_min_cost(n-1, n-1))


if __name__ == '__main__':
    main()
