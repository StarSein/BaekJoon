import sys


input = sys.stdin.readline


def solution() -> str:
    pad_list.sort()

    cnt_catch = 0
    for idx, target in enumerate(target_list):
        tx, ty = target
        px = binary_search(tx)
        if is_in_range(tx, ty, px):
            cnt_catch += 1

    return f'{cnt_catch}'


def binary_search(target_x: int) -> int:
    start, end = 0, m - 1
    while start <= end:
        mid = start + (end - start) // 2
        if target_x > pad_list[mid]:
            start = mid + 1
        elif target_x < pad_list[mid]:
            end = mid - 1
        else:
            return pad_list[mid]

    candidates = []
    for dx in [-1, 0, 1]:
        cdd = mid + dx
        if 0 <= cdd < m:
            candidates.append((abs(target_x - pad_list[cdd]), cdd))
    candidates.sort()
    return pad_list[candidates[0][1]]




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
