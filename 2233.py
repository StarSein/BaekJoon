import sys


HEAD = 0


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    bin_code = input()
    rotten_list = list(map(int, input().split()))
    pos_list = [0] * len(bin_code)
    parent_list = [0] * (n + 1)
    node = HEAD
    pos = HEAD
    for idx, char in enumerate(bin_code):
        if char == '0':
            pos += 1
            parent = node
            node = pos
            parent_list[node] = parent
            pos_list[idx] = node
        else:
            pos_list[idx] = node
            node = parent_list[node]

    node_a = pos_list[rotten_list[0]-1]
    node_b = pos_list[rotten_list[1]-1]

    current_node = node_a
    ancestor_set = set()
    while current_node != HEAD:
        ancestor_set.add(current_node)
        current_node = parent_list[current_node]
    lca = node_b    # lowest common ancestor
    while lca not in ancestor_set:
        lca = parent_list[lca]

    res_list = []
    for idx, pos in enumerate(pos_list):
        if pos == lca:
            res_list.append(idx + 1)
            if len(res_list) == 2:
                break
    print(*res_list)


if __name__ == '__main__':
    main()
