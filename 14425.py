import sys
from collections import defaultdict


input = sys.stdin.readline
NUM_CHAR = 26
ASCII_a = 97


class Trie:
    def __init__(self):
        self.is_terminal = False
        self.children = defaultdict(int)

    def insert(self, i):
        if i == len(inp_str):
            self.is_terminal = True
            return

        index = ord(inp_str[i]) - ASCII_a
        if self.children[index] == 0:
            self.children[index] = Trie()

        self.children[index].insert(i + 1)

    def is_exist(self, i) -> bool:
        if i == len(inp_str):
            if self.is_terminal:
                return True
            else:
                return False

        index = ord(inp_str[i]) - ASCII_a
        if self.children[index] == 0:
            return False

        return self.children[index].is_exist(i + 1)


if __name__ == '__main__':
    n, m = map(int, input().split())

    root = Trie()
    for insert_str in range(n):
        inp_str = input().rstrip()
        root.insert(0)

    cnt = 0
    for check_str in range(m):
        inp_str = input().rstrip()
        if root.is_exist(0):
            cnt += 1
    print(cnt)
