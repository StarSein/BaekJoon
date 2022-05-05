"""
Bottom-up 방식으로 2차원 dp에 해당 좌표로 이동 가능한 내리막길 경로의 수를 추가하자.
Bottom-up 전개를 위해 bfs를 사용하자.
그런데 내리막길로만 이동이 가능하게끔 한다면, 특정 지역을 뺑글뺑글 도는 경우가 없다.
그렇다면 간단하게 Top-down 방식의 구현이 가능하며, deque를 사용을 위해 라이브러리를 호출할 필요도 없게 된다.
"""
import sys

sys.setrecursionlimit(int(1e3 + 10))


def input():
    return sys.stdin.readline().rstrip()


def main():
    m, n = map(int, input().split())
    earth = [list(map(int, input().split())) for _ in range(m)]
    dp = [[-1 for row in range(n)] for col in range(m)]
    dp[0][0] = 1

    y_steps = [0, 1, 0, -1]
    x_steps = [1, 0, -1, 0]

    def get_num_route(col: int, row: int) -> int:
        if dp[col][row] != -1:
            return dp[col][row]
        cur_height = earth[col][row]
        num_route = 0
        for dy, dx in zip(y_steps, x_steps):
            nc, nr = col + dy, row + dx
            if 0 <= nc < m and 0 <= nr < n and earth[nc][nr] > cur_height:
                num_route += get_num_route(nc, nr)
        dp[col][row] = num_route
        return dp[col][row]

    print(get_num_route(m-1, n-1))


if __name__ == '__main__':
    main()
