# [Gold II] 문제 추천 시스템 Version 2 - 21944 

[문제 링크](https://www.acmicpc.net/problem/21944) 

### 성능 요약

메모리: 17376 KB, 시간: 56 ms

### 분류

자료 구조(data_structures), 트리를 사용한 집합과 맵(tree_set)

### 문제 설명

<p>tony9402는 최근 깃헙에 코딩테스트 대비 문제를 직접 뽑아서 "<strong>문제 번호</strong>,<strong> 난이도</strong>,<strong> 알고리즘 분류</strong>"로 정리해놨다.</p>

<p>깃헙을 이용하여 공부하시는 분들을 위해 새로운 기능을 추가해보려고 한다.</p>

<p>만들려고 하는 명령어는 총 3가지가 있다. 아래 표는 각 명령어에 대한 설명이다.</p>

<table border="1" cellpadding="1" cellspacing="1" class="table table-bordered">
	<tbody>
		<tr>
			<td><strong>recommend $G$ $x$</strong></td>
			<td>
			<p>$x$가 1인 경우 추천 문제 리스트에서 알고리즘 분류가 $G$인 문제 중 가장 어려운 문제 번호를 출력한다.</p>

			<p>조건을 만족하는 문제가 여러 개라면 그 중 <strong>문제 번호가 큰 것으로 출력</strong>한다.</p>

			<p>$x$가 -1인 경우 추천 문제 리스트에서 알고리즘 분류가 $G$인 문제 중 가장 쉬운 문제 번호를 출력한다.</p>

			<p>조건을 만족하는 문제가 여러 개라면 그 중 <strong>문제 번호가 작은 것으로 출력</strong>한다.</p>

			<p>해당 명령어는 해당 그룹 $G$에 문제 번호가 한 개 이상이 있을 경우에만 주어진다.</p>
			</td>
		</tr>
		<tr>
			<td><strong>recommend2 $x$ </strong></td>
			<td>
			<p>$x$가 1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 가장 어려운 문제 번호를 출력한다.</p>

			<p>조건을 만족하는 문제가 여러 개라면 그 중 <strong>문제 번호가 큰 것으로 출력</strong>한다.</p>

			<p>$x$가 -1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 가장 쉬운 문제 번호를 출력한다.</p>

			<p>조건을 만족하는 문제가 여러 개라면 그 중 <strong>문제 번호가 작은 것으로 출력</strong>한다.</p>
			</td>
		</tr>
		<tr>
			<td><strong>recommend3 $x$ $L$</strong></td>
			<td>
			<p>$x$가 1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 난이도 $L$보다 크거나 같은 문제 중 가장 쉬운 문제 번호를 출력한다.</p>

			<p>조건을 만족하는 문제가 여러 개라면 그 중 <strong>문제 번호가 작은 것으로 출력</strong>한다. 만약 조건을 만족하는 문제 번호가 없다면 <strong>-1을 출력</strong>한다.</p>

			<p>$x$가 -1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 난이도 $L$보다 작은 문제 중 가장 어려운 문제 번호를 출력한다.</p>

			<p>조건을 만족하는 문제가 여러 개라면 그 중 <strong>문제 번호가 큰 것으로 출력</strong>한다. 만약 조건을 만족하는 문제 번호가 없다면 <strong>-1을 출력</strong>한다.</p>
			</td>
		</tr>
		<tr>
			<td><strong>add $P$ $L$ $G$</strong></td>
			<td>추천 문제 리스트에 난이도가 $L$이고 알고리즘 분류가 $G$인 문제 번호 $P$를 추가한다. (추천 문제 리스트에 없는 문제 번호 $P$만 입력으로 주어진다. 이전에 추천 문제 리스트에 있던 문제 번호가 다른 난이도와 다른 알고리즘 분류로 다시 들어 올 수 있다.)</td>
		</tr>
		<tr>
			<td><strong>solved $P$</strong></td>
			<td>추천 문제 리스트에서 문제 번호 $P$를 제거한다. (추천 문제 리스트에 있는 문제 번호 $P$만 입력으로 주어진다.)</td>
		</tr>
	</tbody>
</table>

<p>명령어 <strong>recommend</strong>, <strong>recommend2</strong>,<strong> recommend3</strong>는 추천 문제 리스트에 문제가 하나 이상 있을 때만 주어진다.</p>

<p>명령어 <strong>solved</strong>는 추천 문제 리스트에 문제 번호가 하나 이상 있을 때만 주어진다.</p>

<p>위 명령어들을 수행하는 추천 시스템을 만들어보자.</p>

### 입력 

 <p>첫 번째 줄에 추천 문제 리스트에 있는 문제의 개수 $N$가 주어진다.</p>

<p>두 번째 줄부터 $N + 1$ 줄까지 문제 번호 $P$와 난이도 $L$, 알고리즘 분류 $G$가 공백으로 구분되어 주어진다.</p>

<p>$N + 2$줄은 입력될 명령문의 개수 $M$이 주어진다.</p>

<p>그 다음줄부터 $M$개의 위에서 설명한 명령문이 입력된다.</p>

### 출력 

 <p><strong>recommend</strong>, <strong>recommend2</strong>,<strong> recommend3 명령이 주어질</strong> 때마다 문제 번호를 한 줄씩 출력한다. 주어지는 recommend, recommend2, recommend3 명령어의 총 개수는 최소 1개 이상이다.</p>

