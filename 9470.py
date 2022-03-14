import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    t = int(input())
    for tc in range(t):
        k, m, p = map(int, input().split())
        followers = [[] for node in range(m + 1)]
        num_preceder = [0] * (m + 1)
        for edge in range(p):
            a, b = map(int, input().split())
            followers[a].append(b)
            num_preceder[b] += 1

        preceder_orders = [[] for node in range(m + 1)]
        ORIGIN_ORDER = 1
        curr_nodes = deque([node for node in range(1, m + 1) if num_preceder[node] == 0])
        for curr_node in curr_nodes:
            preceder_orders[curr_node].append(ORIGIN_ORDER)
        max_order = 1
        while len(curr_nodes):
            curr_node = curr_nodes.popleft()
            
            if len(preceder_orders[curr_node]) == 1:
                curr_order = preceder_orders[curr_node][0]
            else:
                preceder_orders[curr_node].sort()
                if preceder_orders[curr_node][-1] == preceder_orders[curr_node][-2]:
                    curr_order = preceder_orders[curr_node][-1] + 1
                    max_order = max(max_order, curr_order)
                else:
                    curr_order = preceder_orders[curr_node][-1]
            
            for follower in followers[curr_node]:
                preceder_orders[follower].append(curr_order)
                if len(preceder_orders[follower]) == num_preceder[follower]:
                    curr_nodes.append(follower)
        
        print(k, max_order)


if __name__ == '__main__':
    main()
