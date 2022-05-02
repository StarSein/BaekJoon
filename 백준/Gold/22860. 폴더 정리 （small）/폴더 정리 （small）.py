"""
딕셔너리로 계층 구조를 표현하면 간단하겠다.
key: 디렉토리 이름, value: [List[하위 디렉토리], List[파일]]
"""
import sys
from collections import defaultdict, deque, Counter


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    finder = defaultdict(lambda: [[], []])
    DIR, FILE = 0, 1
    for i in range(n + m):
        name, sub_name, is_sub_dir = input().split()
        is_sub_dir = bool(int(is_sub_dir))
        if is_sub_dir:
            finder[name][DIR].append(sub_name)
        else:
            finder[name][FILE].append(sub_name)
    q = int(input())
    for query in range(q):
        pwd = list(input().split('/'))[-1]
        counter = Counter()
        dq = deque([pwd])
        while dq:
            pwd = dq.popleft()
            dq.extend(finder[pwd][DIR])
            counter.update(finder[pwd][FILE])
        print(len(counter.keys()), sum(counter.values()))


if __name__ == '__main__':
    main()
