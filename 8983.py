import sys


input = sys.stdin.readline


def solution() -> str:
    pad_list.sort()

    cnt_catch = 0
    for idx, target in enumerate(target_list):
        tx, ty = target
        px = binary_search(tx)
        if is_in_range(tx, ty, px):
            print(tx, ty, px)
            cnt_catch += 1

    return f'{cnt_catch}'


def binary_search(target_x: int) -> int:
    start, end = 0, m - 1
    while start < end - 1:
        mid = start + (end - start + 1) // 2
        if target_x > pad_list[mid]:
            start = mid + 1
        elif target_x < pad_list[mid]:
            end = mid - 1
        else:
            return pad_list[mid]

    if abs(target_x - pad_list[start]) < abs(target_x - pad_list[end]):
        return pad_list[start]
    else:
        return pad_list[end]


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
