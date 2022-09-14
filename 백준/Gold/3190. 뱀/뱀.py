import sys


class Queue:
    def __init__(self, arr):
        self.arr = arr
    def push(self, v):
        self.arr.append(v)
    def pop(self):
        return self.arr.pop(0)
    def __getitem__(self, idx):
        if len(self.arr) != 0:
            return self.arr[idx]
        else:
            return []


class Deque:
    def __init__(self, arr):
        self.arr = arr
    def append(self, v):
        self.arr.append(v)
    def appendleft(self, v):
        self.arr.insert(0, v)
    def pop(self):
        return self.arr.pop(-1)
    def popleft(self):
        return self.arr.pop(0)
    def left(self):
        return self.arr[0]
    def __getitem__(self, idx):
        return self.arr[idx]


N = int(sys.stdin.readline())
board = [[0 for i in range(N)] for j in range(N)]

K = int(sys.stdin.readline())
for m in range(K):
    c, r = map(int, sys.stdin.readline().split())
    board[c-1][r-1] = -1

L = int(sys.stdin.readline())
dir_adj = []
for n in range(L):
    dir_adj.append(tuple(sys.stdin.readline().split()))

deq_dir = Deque(['Right', 'Down', 'Left', 'Up'])
dir_change_time = int(dir_adj[0][0])
dir_change_rot = dir_adj.pop(0)[1]

site_body = Queue([0, 0])
site_head = [0, 0]
time = 0
while True:
    time += 1

    if deq_dir.left() == 'Right':
        site_head[1] += 1
    elif deq_dir.left() == 'Down':
        site_head[0] += 1
    elif deq_dir.left() == 'Left':
        site_head[1] -= 1
    else:
        site_head[0] -= 1

    if site_head[0] < 0 or site_head[0] == N or site_head[1] < 0 or site_head[1] == N:
        break

    if board[site_head[0]][site_head[1]] == -1:
        site_body.push(site_head[:])
        board[site_head[0]][site_head[1]] = 0
    else:
        site_body.push(site_head[:])
        site_body.pop()

    if site_head in site_body[:-1]:
        break

    if time == dir_change_time:
        if dir_change_rot == 'D':
            deq_dir.append(deq_dir.popleft())
        else:
            deq_dir.appendleft(deq_dir.pop())
        if len(dir_adj) != 0:
            dir_change_time = int(dir_adj[0][0])
            dir_change_rot = dir_adj.pop(0)[1]

print(time)