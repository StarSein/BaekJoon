from sys import stdin
from typing import List


class Trie:
    def __init__(self):
        self.is_terminal = False
        self.childs = [None for _ in range(10)]

    def check_exist(self, s: str, pos: int) -> bool:
        """
        :param s: 트라이 내부에서 존재 여부를 확인할 문자열
        :param pos: 문자열 내에서 현재 인덱스
        :return: 트라이 내부에서 문자열 s의 존재 여부
        """
        if pos == len(s):       # s가 어떤 전화번호의 접두사인 경우
            return True
        if self.is_terminal:    # 어떤 전화번호가 s의 접두사인 경우
            return True
        idx = int(s[pos])
        if self.childs[idx] is None:    # 다음 노드가 만들어져 있지 않은 경우
            return False
        else:                           # 다음 노드가 만들어져 있는 경우
            return (self.childs[idx]).check_exist(s, pos + 1)

    def insert(self, s: str, pos: int) -> None:
        """
        트라이에 문자열을 추가하는 함수
        :param s: 트라이에 추가할 문자열
        :param pos: 문자열 내에서 현재 인덱스
        """
        if pos == len(s):               # 문자열의 끝에 해당하는 노드를 만들었다면
            self.is_terminal = True     # 해당 노드가 어떤 문자열의 끝임을 표시하고 리턴
            return
        idx = int(s[pos])
        if self.childs[idx] is None:    # 다음 노드가 만들어져 있지 않은 경우
            self.childs[idx] = Trie()   # 새로운 노드를 만든다
        (self.childs[idx]).insert(s, pos + 1)


def solution(n: int, phones: List[str]) -> str:
    root = Trie()
    for phone in phones:
        if root.check_exist(phone, 0):
            return "NO"
        root.insert(phone, 0)
    return "YES"


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    phones = [input() for _ in range(n)]
    return n, phones


def main():
    t = int(input())
    for _ in range(t):
        print(solution(*read_input()))


if __name__ == '__main__':
    main()
