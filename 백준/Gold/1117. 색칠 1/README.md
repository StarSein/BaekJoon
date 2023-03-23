# [Gold V] 색칠 1 - 1117 

[문제 링크](https://www.acmicpc.net/problem/1117) 

### 성능 요약

메모리: 31256 KB, 시간: 40 ms

### 분류

수학, 구현

### 문제 설명

<p>지민이는 종이에 색칠하기를 좋아한다. 지민이는 W×H 크기의 직사각형 종이를 가지고 있다. 지민이는 종이에 다음과 같이 색칠 하려고 한다.</p>

<ol>
	<li>종이를 x = f에 맞춰서 접는다. 이때, 왼쪽 종이가 오른쪽 종이 위에 올라오게 접는다.</li>
	<li>종이를 가로로 c+1개의 크기가 동일 한 구간으로 나눈다. 그 다음에 c번 가장 위의 구간부터 차례대로 접는다.</li>
	<li>왼쪽 아래가 (x<sub>1</sub>, y<sub>1</sub>) 이고, 오른쪽 위가 (x<sub>2</sub>, y<sub>2</sub>)인 직사각형을 찾는다. 이때, (0, 0)은 현재 접힌 상태에서 가장 왼쪽 아래 점이다. 그 직사각형을 칠한다. 이때, 페인트는 겹쳐있는 모든 곳에 스며든다.</li>
	<li>종이를 편다.</li>
</ol>

<p>다음 예는 5×6 종이에, x=2이고, c=2이고, (x<sub>1</sub>, y<sub>1</sub>) = (1, 1), (x<sub>2</sub>, y<sub>2</sub>) = (3, 2)인 경우이다.</p>

<p style="text-align: center;"><img alt="" src=""><img alt="" src=""><img alt="" src=""></p>

<p style="text-align: center;"><img alt="" src=""><img alt="" src=""><img alt="" src=""></p>

<p>W, H, f, c, x<sub>1</sub>, y<sub>1</sub>, x<sub>2</sub>, y<sub>2</sub>가 주어질 때, 색칠되어 있지 않은 면적을 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 8개의 정수 W, H, f, c, x<sub>1</sub>, y<sub>1</sub>, x<sub>2</sub>, y<sub>2</sub>가 주어진다.</p>

### 출력 

 <p>첫째 줄에 색칠되지 않은 영역의 넓이를 출력한다.</p>

