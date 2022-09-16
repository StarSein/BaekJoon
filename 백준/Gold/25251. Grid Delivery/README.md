# [Gold I] Grid Delivery - 25251 

[문제 링크](https://www.acmicpc.net/problem/25251) 

### 성능 요약

메모리: 19604 KB, 시간: 152 ms

### 분류

자료 구조(data_structures), 그리디 알고리즘(greedy), 트리를 사용한 집합과 맵(tree_set), 두 포인터(two_pointer)

### 문제 설명

<p>Your friend Ellie owns a local parcel delivery business called Grid City Parcel Courier (GCPC) which operates in Grid City, a town where all houses are aligned on a rectangular grid of streets. Each house is placed at the intersection of two streets, one running in north-south direction (vertically) and one running in east-west direction (horizontally). There are $w$ vertical streets and $h$ horizontal streets, resulting in a $h\times w$ grid of houses.</p>

<p>To grow her business, Ellie wants to start offering parcel pickup too. However, the mayor of Grid City recently decided that all streets will be one-way streets during the day to combat traffic jams. During this time, the streets of Grid City can only be passed from north to south or west to east, respectively.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 290px; height: 137px;"></p>

<p style="text-align: center;">Figure G.1: Visualization of the grid of one-way streets given in the first sample input.</p>

<p>Ellie already rented a large garage located at the city's northwesternmost intersection, from which her drivers will start their journeys to collect parcels. She asked you to help her figure out how many drivers she needs to hire to collect all parcels during the day and bring them to her logistics center located at the city's southeasternmost intersection.</p>

### 입력 

 <p>The input consists of:</p>

<ul>
	<li>One line with two integers $h$ and $w$ ($1 \le h,w \le 2\,000$), the height and width of the grid.</li>
	<li>$h$ lines, each with $w$ characters which are either <code>C</code>, indicating the house of a customer where a parcel has to be collected, or <code>\_</code>, indicating a house where nothing has to be collected.</li>
</ul>

### 출력 

 <p>Output the minimal number of drivers required to collect all parcels while all streets are one-way streets.</p>

