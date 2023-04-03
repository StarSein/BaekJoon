from sys import stdin
from typing import List


class Node:
    def __init__(self, parent):
        self.parent = parent
        self.child = [0 for _ in range(26)]
        self.mark = False

class Trie:
    def __init__(self, R: int, C: int):
        self.R = R
        self.C = C
        self.child = [0 for _ in range(26)]
        self.leaf = [0 for _ in range(C)]

    def insert(self, col: int, column: str):
        idx = 0
        size = len(column)
        cur = self
        while idx < size:
            char = column[idx]
            pos = ord(char) - ord('a')
            if cur.child[pos] == 0:
                cur.child[pos] = Node(cur)
            cur = cur.child[pos]
            idx += 1
        self.leaf[col] = cur

    def answer(self):
        for erase_cnt in range(1, self.R):
            mark_cnt = 0
            for col in range(self.C):
                self.leaf[col] = self.leaf[col].parent
                if self.leaf[col].mark:
                    continue
                self.leaf[col].mark = True
                mark_cnt += 1
            if mark_cnt < self.C:
                return erase_cnt - 1
        return self.R - 1


def input():
    return stdin.readline().rstrip()


def read_input():
    R, C = map(int, input().split())
    table = [input() for _ in range(R)]
    return R, C, table


def solution(R: int, C: int, table: List[str]) -> int:
    root = Trie(R, C)
    column_list = [''.join(table[i][j] for i in range(R - 1, -1, -1)) for j in range(C)]
    for col, column in enumerate(column_list):
        root.insert(col, column)
    return root.answer()


if __name__ == '__main__':
    print(solution(*read_input()))
