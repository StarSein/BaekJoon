from sys import stdin
from collections import deque


input = lambda: stdin.readline().rstrip()


def solution(p: str, n: int, arr: str):
    if n:
        dq = deque(arr[1:-1].split(','))
    else:
        dq = deque()
        
    flip = False
    for p_i in p:
        if p_i == 'R':
            flip = not flip
        elif dq:
            if flip:
                dq.pop()
            else:
                dq.popleft()
        else:
            print("error")
            return

    if flip:
        dq.reverse()

    print(f"[{','.join(dq)}]")


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        p_inp = input()
        n_inp = int(input())
        arr_inp = input()
        solution(p_inp, n_inp, arr_inp)
