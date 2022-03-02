import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def init_dp(current_node: int, parent_node: int):
        connected[current_node].discard(parent_node)
        for child_node in connected[current_node]:
            init_dp(child_node, current_node)

        if connected[current_node]:
            dp[current_node][0] = w_list[current_node] + sum(dp[child_node][1] for child_node in connected[current_node])
            dp[current_node][1] = sum(max(dp[child_node]) for child_node in connected[current_node])
        else:
            dp[current_node][0] = w_list[current_node]
            dp[current_node][1] = 0

    def det_contain(current_node: int):
        if dp[current_node][0] >= dp[current_node][1]:
            contained_set.add(current_node)
            for child in connected[current_node]:
                for grand_child in connected[child]:
                    det_contain(grand_child)
        else:
            for child in connected[current_node]:
                det_contain(child)

    HEAD = 0
    ROOT = 1

    n = int(input())
    w_list = list(map(int, input().split()))
    w_list.insert(0, HEAD)
    connected = [set() for node in range(n + 1)]
    for edge in range(n - 1):
        a, b = map(int, input().split())
        connected[a].add(b)
        connected[b].add(a)

    dp = [[-1, -1] for node in range(n + 1)]
    init_dp(ROOT, HEAD)
    print(max(dp[ROOT]))
    contained_set = set()
    det_contain(ROOT)
    print(*sorted(list(contained_set)))


if __name__ == '__main__':
    main()
