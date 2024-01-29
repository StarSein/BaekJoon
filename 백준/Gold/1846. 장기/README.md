# [Gold IV] 장기 - 1846 

[문제 링크](https://www.acmicpc.net/problem/1846) 

### 성능 요약

메모리: 20384 KB, 시간: 292 ms

### 분류

해 구성하기

### 제출 일자

2024년 1월 29일 14:23:03

### 문제 설명

<p>장기의 말 중 차(車)는 상하좌우 네 방향 중 한 방향으로 몇 칸이든 갈 수 있는 말이다. 예를 들어 차가 아래와 같은 위치에 있다고 할 때, 이 차가 이동할 수 있는 칸은 다음과 같다.</p>

<p style="text-align: center;"><img alt="" height="341" src="https://www.acmicpc.net/JudgeOnline/upload/201007/chess.PNG" width="359"></p>

<p>우리는 N×N 모양의 게임판에 N개의 차를 배치하려고 하는데, 다음의 두 가지 조건을 만족시키도록 배치하고 싶다.</p>

<ol>
	<li>어떤 차도 다른 차가 이동할 수 있는 칸에 위치해서는 안 된다. 즉 서로 같은 세로줄 또는 가로줄에 두 개의 차가 위치할 수는 없다.</li>
	<li>두 대각선이 지나는 칸 위에는 차가 위치할 수 없다. N=7인 경우, 대각선이 지나는 칸이란 아래와 같은 칸들을 말한다.</li>
</ol>

<p style="text-align: center;"><img alt="" height="287" src="https://www.acmicpc.net/JudgeOnline/upload/201007/chess2.PNG" width="297"></p>

<p>주어진 두 조건을 만족하면서 N×N 모양의 게임판에 N개의 차를 배치하는 프로그램을 작성하시오. 아래는 N=4인 경우 조건을 만족하는 배치가 된다. (색칠된 칸은 차를 놓을 수 없는 칸)</p>

<p style="text-align: center;"><img alt="" height="163" src="https://www.acmicpc.net/JudgeOnline/upload/201007/chess3.PNG" width="181"></p>

### 입력 

 <p>첫째 줄에 N(3 ≤ N ≤ 100,000)이 주어진다.</p>

### 출력 

 <p>N개의 줄에 걸쳐, 게임판에서 각 줄의 몇 번째 칸에 차를 배치했는지를 나타내는 칸의 번호를 순서대로 출력한다. 조건을 만족시키는 배치가 둘 이상이면 아무 것이나 출력한다. 배치가 불가능한 경우 첫째 줄에 -1만을 출력한다.</p>

