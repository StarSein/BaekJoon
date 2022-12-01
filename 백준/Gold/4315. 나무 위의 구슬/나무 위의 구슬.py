from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, List[List[int]]]:
    n = int(input())
    inp_list = [list(map(int, input().split())) for _ in range(n)]
    return n, inp_list


def solution(n: int, inputs: List[List[int]]) -> int:
    nums = [0 for _ in range(n + 1)]
    tree = [[] for _ in range(n + 1)]
    is_root = [True for _ in range(n + 1)]
    for inp in inputs:
        node = inp[0]
        nums[node] = inp[1]
        tree[node] = inp[3:]
        for child in tree[node]:
            is_root[child] = False
    
    root = 0
    for node in range(1, n + 1):
        if is_root[node]:
            root = node
            break

    def dfs(node: int) -> Tuple[int, int]:
        total_dist = 0
        total_num = nums[node] - 1
        for child in tree[node]:
            res = dfs(child)
            total_dist += res[0] + abs(res[1])
            total_num += res[1]
        return total_dist, total_num

    return dfs(root)[0]


def main():
    while True:
        n, inputs = read_input()
        if n == 0:
            return
        print(solution(n, inputs))


if __name__ == '__main__':
    main()
    