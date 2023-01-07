from sys import stdin
from collections import deque


def input():
    return stdin.readline().rstrip()


def read_test_case():
    return int(input())


def solution(S: int) -> int:
    visited = [[False for c in range(501)] for r in range(1500)]
    dq = deque()
    dq.append((1, 0))
    visited[1][0] = True
    time = 0
    while dq:
        for _ in range(len(dq)):
            cur_num, cpy_num = dq.popleft()
            if cur_num == S:
                return time
            if cur_num < 501 and not visited[cur_num][cur_num]:
                dq.append((cur_num, cur_num))
                visited[cur_num][cur_num] = True
            nex_num = cur_num + cpy_num
            if cpy_num and nex_num < 1500 and not visited[nex_num][cpy_num]:
                dq.append((nex_num, cpy_num))
                visited[nex_num][cpy_num] = True
            nex_num = cur_num - 1
            if cur_num and not visited[nex_num][cpy_num]:
                dq.append((nex_num, cpy_num))
                visited[nex_num][cpy_num] = True
        time += 1
    return time


if __name__ == '__main__':
    print(solution(read_test_case()))
