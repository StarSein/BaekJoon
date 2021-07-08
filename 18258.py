import sys
from collections import deque


class Que:
    def __init__(self):
        self.q = deque()
    def push(self, x):
        self.q.append(x)
    def pop(self):
        if self.q:
            print(self.q.popleft())
        else:
            print(-1)
    def size(self):
        print(len(self.q))
    def empty(self):
        print(int(not bool(self.q)))
    def front(self):
        if self.q:
            print(self.q[0])
        else:
            print(-1)
    def back(self):
        if self.q:
            print(self.q[-1])
        else:
            print(-1)


def operate(cmd):
    if cmd[:3] == "pus":
        opr, num = cmd.split()
        q.push(num)
    elif cmd[:3] == "pop":
        q.pop()
    elif cmd[:3] == "siz":
        q.size()
    elif cmd[:3] == "emp":
        q.empty()
    elif cmd[:3] == "fro":
        q.front()
    else:
        q.back()


N = int(sys.stdin.readline())
q = Que()
for _ in range(N):
    str_cmd = sys.stdin.readline().rstrip()
    operate(str_cmd)
