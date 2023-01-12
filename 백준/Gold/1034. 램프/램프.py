"""
K는 50으로도 충분하다.
중간에서 만나기를 통해 완전 탐색 경우의 수를 2^25 ** 2 = 약 6천만으로 줄이자.
아, 최악의 경우의 수는 중복조합(50, 50) 라서 안 되겠구나.
근데 이거 말고는 방법이 없다.
아, K가 홀수/짝수이면 스위치를 누른 횟수가 홀수/짝수인 모든 경우를 체크해놓으면 되구나.
그러면 중간에서 만나기를 이용해 경우의 수를 줄이고
비트마스킹을 이용해 최적화를 하자.
아, 결국에는 횟수 제한을 고려해야 한다.

예제 5번을 보니
각 패턴을 가진 행의 개수를 세면 간단히 구할 수 있겠구나.

try 1) WA
원인: 스위치 누르는 횟수가 K 이하여야 한다는 조건을 빠트렸다.
"""
from sys import stdin
from collections import Counter
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, M = map(int, input().split())
    lamp_list = [input() for _ in range(N)]
    K = int(input())
    return N, M, lamp_list, K


def solution(N: int, M: int, lamp_list: List[str], K: int) -> int:
    counter = Counter(lamp_list)
    answer = max((value for key, value in counter.items()
                 if ((zero_cnt := key.count('0')) - K) % 2 == 0 and zero_cnt <= K), default=0)
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
