import sys
from collections import deque


input = sys.stdin.readline


def solution() -> str:
    preceders = [set() for _ in range(n + 1)]
    followers = [set() for _ in range(n + 1)]
    for i in range(n):
        followers[board[i]] = set([board[j] for j in range(i + 1, n)])
    for i in range(n):
        preceders[board[i]] = set([board[j] for j in range(i)])

    is_changing = [False] * (n + 1)
    is_changed = [False] * (n + 1)
    ranks = [0] * (n + 1)
    for rank, team in enumerate(board, start=1):
        ranks[team] = rank
    while len(changes):
        teamA, teamB = changes.popleft()
        for i in range(teamA, teamB + 1):
            is_changing[i] = True
        is_changed[teamA] = True
        is_changed[teamB] = True
        if ranks[teamA] < ranks[teamB]:
            followers[teamA].discard(teamB)
            preceders[teamA].add(teamB)
            followers[teamB].add(teamA)
            preceders[teamB].discard(teamA)
        else:
            followers[teamB].discard(teamA)
            preceders[teamB].add(teamA)
            followers[teamA].add(teamB)
            preceders[teamA].discard(teamB)
    for i in range(n + 1):
        if is_changing[i] != is_changed[i]:
            return "?"

    res = []
    queue = deque([team for team in range(1, n + 1) if len(preceders[team]) == 0])
    while len(queue):
        curr_team = queue.popleft()
        res.append(str(curr_team))
        for follower in followers[curr_team]:
            preceders[follower].discard(curr_team)
            if len(preceders[follower]) == 0:
                queue.append(follower)

    if len(res) == n:
        return ' '.join(res)
    else:
        return "IMPOSSIBLE"


if __name__ == '__main__':
    t = int(input())
    for tc in range(t):
        n = int(input())
        board = list(map(int, input().split()))
        m = int(input())
        changes = deque()
        for change in range(m):
            changes.append(tuple(map(int, input().split())))
        sol = solution()
        print(sol)