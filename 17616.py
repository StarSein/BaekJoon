import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m, x = map(int, input().split())
    precede_nums = [0] * (n + 1)
    followers = [[] for node in range(n + 1)]
    rev_precede_nums = [0] * (n + 1)
    rev_followers = [[] for node in range(n + 1)]
    for info in range(m):
        a, b = map(int, input().split())
        precede_nums[b] += 1
        followers[a].append(b)
        rev_precede_nums[a] += 1
        rev_followers[b].append(a)
    u = topological_sort(x, precede_nums, followers)
    v = n + 1 - topological_sort(x, rev_precede_nums, rev_followers)
    print(u, v)


def topological_sort(x, precede_nums, followers) -> int:
    precede_nums[0] = -1
    queue = deque([node for node, num in enumerate(precede_nums) if num == 0])
    rank = 1
    if x in queue:
        return rank
    while len(queue):
        current_node = queue.pop()
        num_superior = len(queue)
        for inferior_node in followers[current_node]:
            precede_nums[inferior_node] -= 1
            if precede_nums[inferior_node] == 0:
                if inferior_node == x:
                    return rank + num_superior + 1
                queue.append(inferior_node)
        rank += 1


if __name__ == '__main__':
    main()
