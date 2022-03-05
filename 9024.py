import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def solution() -> int:
        num_list.sort()
        min_abs_dist = int(3e8)
        cnt_best = 0
        lp, rp = 0, n - 1
        while lp < rp:
            two_sum = num_list[lp] + num_list[rp]
            abs_dist = abs(two_sum - k)
            if abs_dist == min_abs_dist:
                cnt_best += 1
            elif abs_dist < min_abs_dist:
                min_abs_dist = abs_dist
                cnt_best = 1

            if two_sum >= k:
                rp -= 1
            else:
                lp += 1
        return cnt_best

    t = int(input())
    for tc in range(t):
        n, k = map(int, input().split())
        num_list = list(map(int, input().split()))
        print(solution())

    
if __name__ == '__main__':
    main()
