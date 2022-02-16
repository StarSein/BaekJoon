import sys


input = sys.stdin.readline
MAX_COST = 10_000_000
HOMETOWN = 0


def solution():
    global min_cost
    min_cost = MAX_COST
    is_visited[HOMETOWN] = True
    backtrack(HOMETOWN, 1, 0)
    return min_cost


def backtrack(current_node: int, num_visit: int, current_cost: int):
    global min_cost
    if current_cost > min_cost:
        return

    if num_visit == n and dist[current_node][HOMETOWN]:
        min_cost = min(min_cost, current_cost + dist[current_node][HOMETOWN])
        return

    for next_node in range(n):
        if (not is_visited[next_node]) and dist[current_node][next_node]:
            is_visited[next_node] = True
            backtrack(next_node, num_visit + 1, current_cost + dist[current_node][next_node])
            is_visited[next_node] = False


if __name__ == '__main__':
    n = int(input())
    dist = [list(map(int, input().split())) for col in range(n)]
    is_visited = [False] * n
    sol = solution()
    print(sol)
