import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def make_dp(curr_node: int):
        if len(child_list[curr_node]) == 0:
            dp[curr_node] = 0
            return

        child_dp_list = []
        for child_node in child_list[curr_node]:
            make_dp(child_node)
            child_dp_list.append(dp[child_node])

        child_dp_list.sort(reverse=True)
        INF = 0
        min_time = INF
        for dir_time, sub_time in enumerate(child_dp_list, start=1):
            min_time = max(min_time, dir_time + sub_time)
        dp[curr_node] = min_time

    n = int(input())
    parent_list = list(map(int, input().split()))
    child_list = [[] for node in range(n)]
    for i in range(1, n):
        child_list[parent_list[i]].append(i)
    dp = [-1] * n
    ROOT = 0
    make_dp(ROOT)
    print(dp[ROOT])


if __name__ == '__main__':
    main()
