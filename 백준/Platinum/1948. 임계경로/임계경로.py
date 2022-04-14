"""
완전 탐색으로는 지수 시간 복잡도라서 풀 수가 없겠다.
위상 정렬을 이용해 각 노드를 마지막으로 방문한 사람의 '총 이동 거리'와 '방문 직전 출발한 노드'를 저장하자.
도착 노드의 '방문 시각'을 정답으로 출력하자.
도착 노드부터 출발 노드까지 '방문 직전 출발한 노드'를 거슬러 올라가면서 도로의 개수를 센다.
도로의 개수를 정답으로 출력하자.

위상 정렬
dq = [(depart, arrive, dist)]
cnt_precede[node] = node로 통하는 도로의 개수
dq.popleft() 한 arrive에 대해
    1) cnt_precede[arrive] -= 1
        cnt_precede[arrive] == 0인 경우 arrive의 인접 리스트를 dq에 추가
        추가하는 요소 (depart, arrive, max_dist[depart] + weight)
    2) max_dist[arrive]와 dist 비교
        if. max_dist[arrive] < dist:
            max_dist[arrive] = dist
            final_depart[arrive] = [depart]
        elif max_dist[arrive] == dist:
            final_depart[arrive].append(depart)

도착 -> 출발 역행
checked[node]: bool
dq = [도착 노드]
dq.popleft() 한 node에 대해
    cnt_run_road += len(final_depart[node])
    checked == False 인 final_depart[node] 를 dq에 추가하고 checked = True 처리
"""
import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n = int(input())
    m = int(input())
    graph = [[] for node in range(n + 1)]
    cnt_precede = [0] * (n + 1)
    for edge in range(m):
        depart, arrive, weight = map(int, input().split())
        graph[depart].append((depart, arrive, weight))
        cnt_precede[arrive] += 1
    start_node, end_node = map(int, input().split())

    max_dist = [0] * (n + 1)
    final_depart = [[] for node in range(n + 1)]
    dir_dq = deque(graph[start_node])
    while dir_dq:
        depart, arrive, dist = dir_dq.popleft()

        if max_dist[arrive] < dist:
            max_dist[arrive] = dist
            final_depart[arrive] = [depart]
        elif max_dist[arrive] == dist:
            final_depart[arrive].append(depart)

        cnt_precede[arrive] -= 1
        if cnt_precede[arrive] == 0:
            dir_dq.extend([(d, a, max_dist[d] + w) for d, a, w in graph[arrive]])

    checked = [False] * (n + 1)
    cnt_run_road = 0
    rev_dq = deque([end_node])
    while rev_dq:
        node = rev_dq.popleft()
        if checked[node]:
            continue
        checked[node] = True
        cnt_run_road += len(final_depart[node])
        rev_dq.extend(final_depart[node])

    print(max_dist[end_node])
    print(cnt_run_road)


if __name__ == '__main__':
    main()
