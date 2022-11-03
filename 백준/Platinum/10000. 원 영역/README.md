# [Platinum IV] 원 영역 - 10000 

[문제 링크](https://www.acmicpc.net/problem/10000) 

### 성능 요약

메모리: 5696 KB, 시간: 84 ms

### 분류

자료 구조(data_structures), 분리 집합(disjoint_set), 기하학(geometry), 정렬(sorting), 스택(stack), 스위핑(sweeping)

### 문제 설명

<p>x축 위에 원이 N개 있다. 원은 서로 교차하지 않는다. 하지만, 접할 수는 있다.</p>

<p>원으로 만들어지는 영역이 몇 개인지 구하는 프로그램을 작성하시오.</p>

<p>영역은 점의 집합으로 모든 두 점은 원을 교차하지 않는 연속되는 곡선으로 연결될 수 있어야 한다.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 201px; height: 201px;"></p>

### 입력 

 <p>첫째 줄에 원의 개수 N(1 ≤ N ≤ 300,000)이 주어진다.</p>

<p>다음 N개 줄에는 각 원의 정보 x<sub>i</sub>와 r<sub>i</sub>가 정수로 주어진다. x<sub>i</sub>는 원의 중심 좌표이며, r<sub>i</sub>는 반지름이다. (-10<sup>9</sup> ≤ x<sub>i</sub> ≤ 10<sup>9</sup>, 1 ≤ r<sub>i</sub> ≤ 10<sup>9</sup>)</p>

<p>입력으로 주어지는 원은 항상 유일하다.</p>

### 출력 

 <p>첫째 줄에 원으로 인해서 만들어지는 영역의 개수를 출력한다.</p>

