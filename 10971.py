import sys


input = sys.stdin.readline
MAX_COST = 1_000_000 * 10


def solution():
    global min_cost, hometown
    min_cost = MAX_COST
    for node in range(n):
        hometown = node
        is_visited[node] = True
        backtrack(node, 1, 0)   # 현재 위치, 방문 노드 개수, 현재까지의 비용
        is_visited[node] = False

    print(min_cost)


def backtrack(current_node: int, num_visit: int, current_cost: int):
    if num_visit == n:
        global min_cost, hometown
        if graph[current_node][hometown]:
            min_cost = min(min_cost, current_cost + graph[current_node][hometown])
        return

    for next_node in range(n):
        if (not is_visited[next_node]) and (graph[current_node][next_node]):
            is_visited[next_node] = True
            backtrack(next_node, num_visit + 1, current_cost + graph[current_node][next_node])
            is_visited[next_node] = False


if __name__ == '__main__':
    n = int(input())
    graph = [list(map(int, input().split())) for col in range(n)]
    is_visited = [False] * n
    solution()
