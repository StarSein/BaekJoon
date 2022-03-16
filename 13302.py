import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def calc_min_cost(day: int, num_coupon: int) -> int:
        if day > n:
            return 0
        if dp[day][num_coupon] != MAX_COST:
            return dp[day][num_coupon]

        if not is_able[day]:
            dp[day][num_coupon] = calc_min_cost(day + 1, num_coupon)
            return dp[day][num_coupon]

        dp[day][num_coupon] = min(dp[day][num_coupon], calc_min_cost(day + 1, num_coupon) + ONE_DAY)
        dp[day][num_coupon] = min(dp[day][num_coupon], calc_min_cost(day + 3, num_coupon + 1) + THREE_DAYS)
        dp[day][num_coupon] = min(dp[day][num_coupon], calc_min_cost(day + 5, num_coupon + 2) + FIVE_DAYS)
        if num_coupon >= 3:
            dp[day][num_coupon] = min(dp[day][num_coupon], calc_min_cost(day + 1, num_coupon - 3))
        return dp[day][num_coupon]

    ONE_DAY = 10000
    THREE_DAYS = 25000
    FIVE_DAYS = 37000

    n, m = map(int, input(). split())
    is_able = [True] * (n + 1)
    if m:
        for day in list(map(int, input().split())):
            is_able[day] = False

    MAX_COST = int(1e6 + 1)
    dp = [[MAX_COST for num_coupon in range(100)] for day in range(n + 1)]
    print(calc_min_cost(1, 0))


if __name__ == '__main__':
    main()
