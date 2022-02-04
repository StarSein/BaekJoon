import sys
from collections import defaultdict


input = sys.stdin.readline
ASCII_a = 97

class Trie:
    # instance 멤버 변수 선언부
    def __init__(self):
        self.is_terminal = False            # is_terminal: 해당 instance가 문자열의 끝인지 여부를 나타내는 불리언 변수
        self.children = defaultdict(int)    # children: 인접 instance들을 담은 딕셔너리 (key: 문자 인덱스, value: instance의 주소)

    # instance 멤버 함수 선언부
    def insert(self, i: int):               # insert 함수: 트라이 구조에 특정 문자열을 삽입하는 함수
        if i == len(inp_str):               # 만약 입력하고자 하는 문자열의 끝(EOL)에 도착하면
            self.is_terminal = True         # 해당 instance가 문자열의 끝임을 표시하고
            return                          # 함수 호출을 종료함

        index = self.char_to_idx(i)         # 문자열의 i번째 문자(a~z)를 정수(0~25)로 변환함  <= 문자를 그대로 children의 key로 사용할 경우 TLE
        if self.children[index] == 0:       # i+1번째 문자에 해당하는 주소가 정의되어 있지 않다면
            self.children[index] = Trie()   # 새로운 instance(주소)를 선언하여 할당함

        self.children[index].insert(i + 1)  # i+1번째 문자에 해당하는 instance에서 insert 함수를 재귀 호출 함

    def is_exist(self, i: int) -> bool:     # is_exist 함수: 트라이 구조에 특정 문자열이 존재하는지 확인하는 함수
        if i == len(inp_str):               # 만약 입력하고자 하는 문자열의 끝(EOL)에 도착했는데
            if self.is_terminal:            # 해당 instance가 문자열의 끝이면
                return True                 # 문자열이 존재하는 것임
            else:                           # 해당 instance가 문자열의 끝이 아니면
                return False                # 문자열이 존재하지 않는 것임 (ex. 트라이 구조에 저장된 문자열은 abc인데, 찾는 문자열은 ab인 경우)

        index = self.char_to_idx(i)
        if self.children[index] == 0:       # i+1번째 문자에 해당하는 주소가 정의되어 있지 않다면
            return False                    # 문자열이 존재하지 않는 것임 (ex. 트라이 구조에 저장된 문자열은 xy인데, 찾는 문자열은 xyz인 경우)

        return self.children[index].is_exist(i + 1)     # i+1번째 문자에 해당하는 instance에서 is_exist 함수를 재귀 호출 함

    def char_to_idx(self, i: int) -> int:   # char_to_idx 함수: 문자열의 i번째 문자(a~z)를 정수(0~25)로 변환함
        return ord(inp_str[i]) - ASCII_a


if __name__ == '__main__':
    n, m = map(int, input().split())

    root = Trie()                           # root: 트라이 구조의 루트 노드
    for insert_str in range(n):
        inp_str = input().rstrip()
        root.insert(0)                      # 입력받은 문자열의 첫 번째 문자부터 마지막 문자까지 트라이 구조에 저장함

    cnt = 0
    for check_str in range(m):
        inp_str = input().rstrip()
        if root.is_exist(0):                # 입력받은 문자열의 첫 번째 문자부터 마지막 문자까지 모두 트라이 구조에 존재하며, 그곳이 문자열의 마지막일 경우
            cnt += 1
    print(cnt)
