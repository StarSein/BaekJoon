import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    def bellman_ford() -> bool:
        dist[start] = -profit_list[start]
        for i in range(1, n):
            for edge in edge_list:
                curr_node, next_node, net_cost = edge
                if dist[curr_node] != INF and dist[curr_node] + net_cost < dist[next_node]:
                    dist[next_node] = dist[curr_node] + net_cost
        if dist[end] == INF:
            return False
        for edge in edge_list:
            curr_node, next_node, net_cost = edge
            if dist[curr_node] != INF and dist[curr_node] + net_cost < dist[next_node]:
                infinite_node = next_node
                if dfs(infinite_node, end):
                    return True
        return False

    def dfs(start: int, end: int) -> bool:
        stack = [start]
        is_visited = [False] * n
        while len(stack):
            curr_node = stack.pop()
            if is_visited[curr_node]:
                continue
            
            is_visited[curr_node] = True
            
            if curr_node == end:
                return True
            
            for next_node in connected[curr_node]:
                stack.append(next_node)

        return False

    n, start, end, m = map(int, input().split())
    edge_list = [list(map(int, input().split())) for edge in range(m)]
    profit_list = list(map(int, input().split()))

    connected = [[] for node in range(n)]
    for edge in edge_list:
        curr_node, next_node, cost = edge
        connected[curr_node].append(next_node)

    for i in range(m):
        edge_list[i][2] -= profit_list[edge_list[i][1]]
    INF = int(1e8)
    dist = [INF] * n
    is_infinite = bellman_ford()
    if is_infinite:
        print("Gee")
    elif dist[end] == INF:
        print("gg")
    else:
        print(-dist[end])


if __name__ == '__main__':
    main()
