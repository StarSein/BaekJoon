from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    nums = [int(input()) for _ in range(N)]
    return N, nums


def solution(N: int, nums: List[int]):
    nums.insert(0, 0)

    visited = [False for _ in range(N + 1)]
    answer = []
    for i in range(1, N + 1):
        if visited[i]:
            continue
        stack = []
        contained = [False for _ in range(N + 1)]
        cur = i
        stack.append(cur)
        contained[cur] = True
        visited[cur] = True
        flag = False
        while not contained[nums[cur]]:
            cur = nums[cur]
            if visited[cur]:
                flag = True
                break
            stack.append(cur)
            contained[cur] = True
            visited[cur] = True

        if flag:
            continue

        bottom = nums[cur]
        while stack[-1] != bottom:
            answer.append(stack.pop())
        answer.append(bottom)

    answer.sort()
    print(len(answer))
    print(*answer, sep='\n')


if __name__ == '__main__':
    solution(*read_input())
