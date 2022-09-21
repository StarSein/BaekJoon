import sys


def input():
    return sys.stdin.readline().rstrip()


def upper_bound(st: list, _num: int):
    left, right = 0, len(st) - 1
    pos = -1
    while left <= right:
        mid = (left + right) // 2
        if st[mid] > _num:
            pos = mid
            right = mid - 1
        else:
            left = mid + 1
    st[pos] = _num
            

def solution() -> int:
    n = int(input())
    nums = list(map(int, input().split()))

    stack = []
    for num in nums:
        if not stack or stack[-1] < num:
            stack.append(num)
        else:
            upper_bound(stack, num)
    return len(stack)


if __name__ == '__main__':
    print(solution())
