import sys
from collections import defaultdict


input = sys.stdin.readline


def find_root(x: int) -> int:
    if roots[x] == x:
        return x

    roots[x] = find_root(roots[x])
    return roots[x]


def union(root1: int, root2: int):
    roots[root2] = root1
    nums[root1] += nums[root2]


if __name__ == '__main__':
    t = int(input())
    for tc in range(t):
        f = int(input())
        id = defaultdict(int)
        nums = defaultdict(lambda: 1)

        roots = [0]
        cnt = 1
        for friend in range(f):
            user1, user2 = input().split()
            if id[user1] == 0:
                id[user1] = cnt
                roots.append(cnt)
                cnt += 1
            if id[user2] == 0:
                id[user2] = cnt
                roots.append(cnt)
                cnt += 1

            a_root = find_root(id[user1])
            b_root = find_root(id[user2])

            if a_root < b_root:
                union(a_root, b_root)
            elif a_root > b_root:
                union(b_root, a_root)
            else:
                pass
            print(nums[min(a_root, b_root)])
