from sys import stdin
import heapq


def input():
    return stdin.readline().rstrip()


def main():
    results = init()
    T = int(input())
    for _ in range(T):
        N = int(input())
        div, mod = divmod(N, 60)
        result = results[mod][:]
        result[0] += div

        print(*result)


def init() -> dict:
    results = dict()
    heap = [[0, 0, 0, 0, 0, 0, 0]]

    variances = [60, 10, -10, 1, -1]

    while heap:
        cur = heapq.heappop(heap)
        target = cur[6]
        if target in results:
            continue

        results[target] = cur[1:6]

        for btn_idx, variance in enumerate(variances, start=1):
            nex_target = max(target + variance, 0)
            if 0 <= nex_target <= 60 and nex_target not in results:
                nex = cur[:]
                nex[0] += 1
                nex[btn_idx] += 1
                nex[6] += variance

                heapq.heappush(heap, nex)

    return results


if __name__ == '__main__':
    main()
