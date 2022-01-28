import sys


input = sys.stdin.readline


def union(a: int, b: int):                  # a와 b가 서로 다른 집합에 존재하면 같은 집합에 포함시키는 함수
    a_root = find_root(a)
    b_root = find_root(b)

    if a_root != b_root:                    # a의 루트노드와 b의 루트노드가 다르면,
        roots[b_root] = a_root              # b의 루트노드가 a의 루트노드를 루트노드로 가리키도록 한다.


def is_same_set(a: int, b: int) -> bool:    # a와 b의 루트노드를 비교하여 같은 집합인지 여부를 판별하는 함수
    a_root = find_root(a)
    b_root = find_root(b)

    return a_root == b_root


def find_root(x: int) -> int:               # 탐색 과정에서 루트노드를 update 하면서 찾은 루트노드를 반환하는 함수
    if roots[x] == x:                       # roots[idx] = idx (초기값) 이면 루트노드
        return x

    root = find_root(roots[x])              # 재귀적으로 root 는 재귀호출 중단 시점의 x (루트노드)
    roots[x] = root                         # 재귀 호출 과정에서 방문하는 모든 노드 idx 에 대해 roots[idx]를 root 로 update 한다
    return root


if __name__ == '__main__':
    n, m = map(int, input().split())
    roots = [i for i in range(n + 1)]
    for inp in range(m):
        opr, a, b = map(int, input().split())
        if opr == 0:                        # 입력받은 연산이 0이면 합집합 연산을 수행한다.
            union(a, b)
        else:                               # 입력받은 연산이 1이면 같은 집합 여부를 확인하고 결과를 출력한다.
            print({True: "YES",
                   False: "NO"}.get(is_same_set(a, b)))

