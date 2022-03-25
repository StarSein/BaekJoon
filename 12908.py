import sys
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    xs, ys = map(int, input().split())
    xe, ye = map(int, input().split())
    teleports = dict()
    NUM_TP = 3
    TP_COST = 10
    for teleport in range(NUM_TP):
        x1, y1, x2, y2 = map(int, input().split())
        teleports[(x1, y1)] = (x2, y2)
        teleports[(x2, y2)] = (x1, y1)

    res_list = []
    is_used = defaultdict(bool)

    def backtrack(x: int, y: int, time: int):
        if x == xe and y == ye:
            res_list.append(time)
            return

        next_time = time + abs(xe - x) + abs(ye - y)
        backtrack(xe, ye, next_time)

        for xt, yt in teleports.keys():
            if not is_used[(xt, yt)]:
                xnt, ynt = teleports[(xt, yt)]
                is_used[(xt, yt)] = True
                is_used[(xnt, ynt)] = True
                next_time = time + TP_COST + abs(xt - x) + abs(yt - y)
                backtrack(xnt, ynt, next_time)
                is_used[(xt, yt)] = False
                is_used[(xnt, ynt)] = False

    backtrack(xs, ys, 0)
    print(min(res_list))


if __name__ == '__main__':
    main()
