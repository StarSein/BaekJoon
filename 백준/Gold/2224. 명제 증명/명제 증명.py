"""
N은 최대 1만이지만, 알파벳의 개수는 52개다.
따라서 가능한 명제의 개수는 perm(52, 2) = 약 1000개다.
명제의 개수를 K라고 하면 시간 복잡도는 O(K^2)이다.
"""
from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N = int(input())
    propositions = [input() for _ in range(N)]
    return N, propositions


def solution(N: int, propositions: List[str]):
    def ctoi(c: str) -> int:
        if c.isupper():
            return ord(c) - 65  # 65 = A
        else:
            return ord(c) - 71  # 71 = a - 26

    def itoc(i: int) -> str:
        if i < 26:
            return chr(i + 65)
        else:
            return chr(i + 71)

    def bfs(start: int):
        visited = [False for _ in range(NUM_ALPHA)]
        visited[start] = True
        dq = deque([start])
        while dq:
            cur = dq.popleft()
            for nex in digraph[cur]:
                if not visited[nex]:
                    answers.append((itoc(start), itoc(nex)))
                    dq.append(nex)
                    visited[nex] = True

    prop_list = [(ctoi(prop[0]), ctoi(prop[-1])) for prop in propositions]
    NUM_ALPHA = 52
    digraph = [[] for _ in range(NUM_ALPHA)]
    checked = [[False for c in range(NUM_ALPHA)] for r in range(NUM_ALPHA)]
    for p, q in prop_list:
        if p != q and not checked[p][q]:
            digraph[p].append(q)
            checked[p][q] = True

    answers = []
    for p in range(NUM_ALPHA):
        bfs(p)

    answers.sort()
    print(len(answers))
    for p, q in answers:
        print(f"{p} => {q}")


if __name__ == '__main__':
    solution(*read_test_case())
