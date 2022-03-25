import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    ROOT = 0
    tree = [["", []]]
    FOOD = 0
    CHILDS = 1

    def make_tree():
        for i in range(n):
            inp = list(input().split())
            pos = ROOT
            for food in inp[1:]:
                child_foods = [tree[child_pos][FOOD] for child_pos in tree[pos][CHILDS]]
                child_pos = -1
                for idx, child_food in enumerate(child_foods):
                    if food == child_food:
                        child_pos = tree[pos][CHILDS][idx]

                if child_pos == -1:
                    tree.append([food, []])
                    child_pos = len(tree) - 1
                    tree[pos][CHILDS].append(child_pos)
                pos = child_pos

    def sort_tree(pos: int):
        tree[pos][CHILDS].sort(key=lambda x: tree[x][FOOD])

        for child_pos in tree[pos][CHILDS]:
            sort_tree(child_pos)

    def print_tree(pos: int, depth: int):
        for child_pos in tree[pos][CHILDS]:
            print('--' * (depth - 1), tree[child_pos][FOOD], sep='')
            print_tree(child_pos, depth + 1)

    make_tree()
    sort_tree(ROOT)
    print_tree(ROOT, 1)


if __name__ == '__main__':
    main()
