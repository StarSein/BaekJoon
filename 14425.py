import sys


input = sys.stdin.readline
NUM_CHAR = 26
ASCII_a = 97


class Trie:
    def __init__(self):
        self.is_terminal = False
        self.children = [None] * NUM_CHAR

    def insert(self, i):
        if i == len(inp_str):
            self.is_terminal = True
            return

        char = inp_str[i]
        index = char_to_idx(char)
        if self.children[index] is None:
            self.children[index] = Trie()

        self.children[index].insert(i + 1)

    def is_exist(self, i) -> bool:
        if i == len(inp_str):
            if self.is_terminal:
                return True
            else:
                return False

        char = inp_str[i]
        index = char_to_idx(char)
        if self.children[index] is None:
            return False

        return self.children[index].is_exist(i + 1)


def char_to_idx(char: chr):
    return ord(char) - ASCII_a


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
