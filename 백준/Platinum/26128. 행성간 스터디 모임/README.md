# [Platinum III] 행성간 스터디 모임 - 26128 

[문제 링크](https://www.acmicpc.net/problem/26128) 

### 성능 요약

메모리: 183164 KB, 시간: 1084 ms

### 분류

그래프 이론(graphs), 그래프 탐색(graph_traversal), 위상 정렬(topological_sorting)

### 문제 설명

<p>우주에는 $N$개의 행성 문명이 있고, 각 행성 문명은 하나의 행성을 지배하고 있다. 행성에는 $1$번부터 $N$번까지의 번호가 붙어 있다.</p>

<p>각 행성에는 웜홀이 하나씩 설치되어 있다. 각 웜홀의 목적지는 행성 하나로 고정되어 있으며, 사람 한 명이 $1$의 비용을 내고 자신이 위치한 행성에 있는 웜홀을 이용하면 목적지 행성으로 곧장 이동할 수 있다. 웜홀은 <strong>단방향으로만</strong> 작동하며, 웜홀의 목적지는 출발 행성과 동일할 수도 있다.</p>

<p>알고리즘 동아리 Almight의 회원들은 다양한 행성에 거주하고 있다. 회원들은 다음 모임을 진행하기 위한 장소로 행성 하나를 정하려고 한다. 행성간 이동 수단으로는 웜홀이 가장 효율적이기 때문에, 모임 장소는 회원들이 웜홀만을 이용하여 도달 가능한 행성이어야 한다. 또한, 회원들이 모임 장소에 도달하기 위해 이용해야 하는 웜홀 비용의 총합이 최소여야 한다. 단, 모임이 끝난 뒤 각자 거주 행성으로 돌아가는 방법과 비용은 고려하지 않는다.</p>

<p>조건을 만족하는 모임 장소를 정하는 것이 가능한지 판별하고, 만약 가능하다면 모든 회원이 모임 장소에 도달하기 위해 이용해야 하는 총 웜홀 비용의 최솟값을 구하시오.</p>

### 입력 

 <p>첫 번째 줄에 행성 문명의 수 $N$과 알고리즘 동아리 Almight의 회원 수 $M$이 주어진다. ($1\le N,M\le 300\ 000$)</p>

<p>두 번째 줄에 $N$개의 정수 $p_1,\dots ,p_N$이 주어진다. $p_i$는 $i$번 행성에 있는 웜홀을 이용하여 이동할 수 있는 행성의 번호를 나타낸다. ($1\le p_i\le N$)</p>

<p>세 번째 줄에 $M$개의 정수 $q_1,\dots ,q_M$이 주어진다. $q_i$는 $i$번째 동아리 회원이 거주하는 행성 번호를 나타낸다. ($1\le q_i\le N$)</p>

### 출력 

 <p>만약 조건을 만족하는 모임 장소가 존재하지 않으면 $-1$을 출력한다.</p>

<p>만약 조건을 만족하는 모임 장소가 존재하면, 모든 회원이 모임 장소에 도달하기 위해 이용해야 하는 총 웜홀 비용의 최솟값을 출력한다.</p>

