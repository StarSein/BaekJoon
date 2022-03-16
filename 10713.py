import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    visit_list = list(map(int, input().split()))
    price_list = [list(map(int, input().split())) for trail in range(n - 1)]
    price_list.append([0, 0, 0])

    cnt_delta_list = [0] * n
    for idx in range(1, m):
        a, b = sorted(visit_list[idx-1:idx+1])
        cnt_delta_list[a-1] += 1
        cnt_delta_list[b-1] -= 1

    cnt_list = []
    curr_cnt = 0
    for cnt_delta in cnt_delta_list:
        curr_cnt += cnt_delta
        cnt_list.append(curr_cnt)

    total_cost = 0
    for i, cnt in enumerate(cnt_list):
        total_cost += min(price_list[i][0] * cnt, price_list[i][1] * cnt + price_list[i][2])
    print(total_cost)


if __name__ == '__main__':
    main()
