"""
재귀 호출과 DP 메모이제이션을 활용하자.
입력으로 조합법을 받은 부품은 중간 부품.
입력으로 조합법을 받지 않은 부품은 기본 부품.
"""
import sys
from collections import defaultdict


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    m = int(input())
    materials = defaultdict(list)
    for recipe in range(m):
        x, y, k = map(int, input().split())
        materials[x].append((y, k))

    dp = [dict()] * (n + 1)
    for num in range(1, n):
        if num not in materials:
            dp[num] = {num: 1}

    def get_material(num: int):
        if dp[num]:
            return dp[num]

        m_dict = defaultdict(int)
        for material, cnt in materials[num]:
            for k, v in get_material(material).items():
                m_dict[k] += v * cnt

        dp[num] = m_dict
        return dp[num]

    for material, cnt in sorted(get_material(n).items()):
        print(material, cnt)


if __name__ == '__main__':
    main()
