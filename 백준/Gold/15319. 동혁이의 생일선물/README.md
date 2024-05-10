# [Gold IV] 동혁이의 생일선물 - 15319 

[문제 링크](https://www.acmicpc.net/problem/15319) 

### 성능 요약

메모리: 43864 KB, 시간: 532 ms

### 분류

분할 정복을 이용한 거듭제곱, 수학

### 제출 일자

2024년 5월 10일 22:47:04

### 문제 설명

<p style="text-align:center"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15319/1.png" style="height:150px; width:225px"></p>

<p>동혁이에게는 N명의 친구가 있다. 동혁이의 생일은 4월 14일인데, 친구들은 그 날 동혁이에게 수열을 선물하려 했다. 하지만 그 수열을 구성하는 숫자가 너무 많았기 때문에 수열 전체를 선물하는 대신에 수열을 만들 수 있는 숫자 x만 선물하였고, 동혁이에게 수열을 만드는 방법을 전수해 줬다. 이제 7월 1일인 오늘, 친구들은 동혁이가 자신들의 선물을 얼마나 소중히 여겼는지 확인하기 위해 선물한 수열의 K번째 수를 물어보려 한다. 동혁이가 제대로 대답을 하지 못한다면, 친구들은 실망하고 떠나갈 것이다. 하지만 동혁이는 돈도 안되는 수열엔 관심이 없었기 때문에 하나도 외우지 못했다. 동혁이가 지금 당장 친구들의 물음에 빠르게 답할 수 있도록 도와주자.</p>

<p>i번째 친구가 선물한 숫자 x_i로 만들 수 있는 수열 M<sub>i</sub> (1 ≤ i ≤ N)는 다음과 같은 규칙을 갖는다. 숫자 x<sub>i</sub>의 거듭제곱을 원소로 하는 집합을 A라 할 때 (A = {x<sub>i</sub><sup>0</sup>, x<sub>i</sub><sup>1</sup>, x<sub>i</sub><sup>2</sup>, …}), 집합 A의 공집합을 제외한 모든 부분집합을 A<sub>f</sub> (f ≥ 0, f ∈ <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-texatom texclass="ORD"><mjx-mi class="mjx-ds mjx-b"><mjx-c class="mjx-c2124 TEX-A"></mjx-c></mjx-mi></mjx-texatom></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mrow data-mjx-texclass="ORD"><mi mathvariant="double-struck">Z</mi></mrow></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(\mathbb{Z}\)</span></mjx-container>)라 하자. 여기서 집합 A<sub>j</sub> (j ∈ f)의 모든 원소들의 합을 a<sub>j</sub>라 하면, 수열 M<sub>i</sub>는 a<sub>j</sub> (j ∈ f)로 구성된다. (단, M<sub>i</sub>는 중복되는 값을 갖지 않는 증가수열이다.)</p>

<p>수열 M<sub>i</sub>에 대해 좀 더 풀어서 설명하면 다음과 같다.</p>

<p>집합 A = {xi<sup>0</sup>, x<sub>i</sub><sup>1</sup>, x<sub>i</sub><sup>2</sup>, …} 의 공집합을 제외한 모든 부분집합을 다음과 같이 나열할 수 있다.</p>

<p style="text-align:center">A<sub>0</sub>, A<sub>1</sub>, A<sub>2</sub>, …</p>

<p>그리고 각각의 부분집합의 합은 다음과 같다.</p>

<p style="text-align:center">a<sub>0</sub>, a<sub>1</sub>, a<sub>2</sub>, …</p>

<p>이제 i번째 친구가 선물한 x<sub>i</sub>로 만들 수 있는 수열 M<sub>i</sub>는 a<sub>0</sub>, a<sub>1</sub>, a<sub>2</sub>, … 를 오름차순으로 정렬한 수열이다.</p>

### 입력 

 <p>첫째 줄에 친구의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 그 후 N줄에 걸쳐 친구들이 선물한 숫자 x(2 ≤ x ≤ 1,000) 와 친구들이 질문하고 싶어하는 K(1 ≤ K ≤ 1,000,000,000)가 주어진다.</p>

### 출력 

 <p>숫자 x<sub>i</sub> (1 ≤ i ≤ N)로 구성된 수열 M<sub>i</sub> (1 ≤ i ≤ N)의 K번째 수를 모두 구한 뒤, 전부 더한 값을 1,000,000,007로 나눈 나머지를 출력한다.</p>

