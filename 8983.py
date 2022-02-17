import sys


input = sys.stdin.readline


def solution() -> str:
    pad_list.sort()
    target_list.sort()

    cnt_prey = 0
    i = 0
    for idx, target in enumerate(target_list):
        tx, ty = target
        px = pad_list[i]
        if is_in_range(tx, ty, px):
            cnt_prey += 1
            continue

        if i < m - 1:
            while tx > px:
                i += 1

                px = pad_list[i]
                if is_in_range(tx, ty, px):
                    cnt_prey += 1
                    break

    return f'{cnt_prey}'


def is_in_range(tx: int, ty: int, px: int) -> bool:
    dist = abs(tx - px) + ty
    return dist <= l


if __name__ == '__main__':
    m, n, l = map(int, input().split())
    pad_list = list(map(int, input().split()))
    target_list = []
    for animal in range(n):
        target_list.append(tuple(map(int, input().split())))

    print(solution())
