import sys


sys.setrecursionlimit(110_000)
input = sys.stdin.readline
ROOT = 1
HEAD = 0


def solution():
    make_tree(ROOT, HEAD)
    calc_dists(ROOT)
    print(max(diameter))


def make_tree(current: int, parent: int):
    for idx, adjacent in enumerate(connected[current]):
        if adjacent[0] != parent:
            childs[current].append(adjacent)
            make_tree(adjacent[0], current)


def calc_dists(current: int):
    child_dists = []
    for idx, child in enumerate(childs[current]):
        calc_dists(child[0])
        child_dists.append(dist[child[0]] + child[1])

    child_dists.sort(reverse=True)
    if len(child_dists) >= 2:
        dist[current] = child_dists[0]
        diameter[current] = child_dists[0] + child_dists[1]
    elif len(child_dists) == 1:
        dist[current] = child_dists[0]
        diameter[current] = child_dists[0]
    else:
        pass


if __name__ == '__main__':
    v = int(input())
    connected = [[] for node in range(v + 1)]
    for i in range(v):
        inp = list(map(int, input().split()))
        node = inp[0]
        for j in range(1, len(inp) - 1, 2):
            connected[node].append((inp[j], inp[j+1]))
    childs = [[] for node in range(v + 1)]
    dist = [0] * (v + 1)
    diameter = [0] * (v + 1)
    solution()
