import sys


l_inp = list(sys.stdin.readline().rstrip())
l_bomb = list(sys.stdin.readline().rstrip())
stack = []
for char in l_inp:
    stack.append(char)
    if stack[-len(l_bomb):] == l_bomb:
        for i in range(len(l_bomb)):
            stack.pop()
if stack:
    print(''.join(stack))
else:
    print("FRULA")