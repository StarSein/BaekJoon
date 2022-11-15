# [Gold III] Imprecise Computer - 20173 

[문제 링크](https://www.acmicpc.net/problem/20173) 

### 성능 요약

메모리: 9832 KB, 시간: 92 ms

### 분류

구성적(constructive), 다이나믹 프로그래밍(dp)

### 문제 설명

<p>The Imprecise Computer (IC) is a computer with some structural issue that it can compare two integers correctly only when their difference is at least two. For example, IC can always correctly answer ‘4 is larger than 2’, but it can answer either ‘2 is larger than 3’ or ‘3 is larger than 2’ (in this case, IC arbitrarily chooses one of them). For two integers <em>x</em> and <em>y</em>, we say ‘<em>x</em> defeats <em>y</em>’ when IC answers ‘<em>x</em> is larger than <em>y</em>’.</p>

<p>Given a positive integer <em>n</em>, let <em>P<sub>n</sub></em> = {1, 2, … , <em>n</em>} be the set of positive integers from 1 to <em>n</em>. Then we run a double round-robin tournament on <em>P<sub>n</sub></em> using IC. The double-round-robin tournament is defined as follows:</p>

<ol>
	<li>The tournament is composed of two rounds (the 1st round and the 2nd round).</li>
	<li>For each round, each element in <em>P<sub>n</sub></em> is compared to every other element in <em>P<sub>n</sub></em>.</li>
</ol>

<p>Now for each element <em>k</em> in <em>P<sub>n</sub></em>, let <em>r<sub>i</sub></em>(<em>k</em>) be the number of wins of <em>k</em> in the <em>i</em>-th round of the tournament. We also define the ‘difference sequence’ <em>D</em> = <em>d</em><sub>1</sub><em>d</em><sub>2</sub>…<em>d<sub>n</sub></em> where for each 1 ≤ <em>k</em> ≤ <em>n</em>, <em>d<sub>k</sub></em> = |<em>r</em><sub>1</sub>(<em>k</em>) − <em>r</em><sub>2</sub>(<em>k</em>)|.</p>

<p>The following shows an example when <em>n</em> = 5.</p>

<table class="table table-bordered table-center-30 th-center td-center">
	<thead>
		<tr>
			<th>1<sup>st</sup> round</th>
			<th>2<sup>nd</sup> round</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><code>2 defeats 1</code></td>
			<td><code>3 defeats 1</code></td>
		</tr>
		<tr>
			<td><code>3 defeats 1</code></td>
			<td><code>4 defeats 1</code></td>
		</tr>
		<tr>
			<td><code>4 defeats 1</code></td>
			<td><code>5 defeats 1</code></td>
		</tr>
		<tr>
			<td><code>5 defeats 1</code></td>
			<td><code>1 defeats 2</code></td>
		</tr>
		<tr>
			<td><code>3 defeats 2</code></td>
			<td><code>4 defeats 2</code></td>
		</tr>
		<tr>
			<td><code>4 defeats 2</code></td>
			<td><code>5 defeats 2</code></td>
		</tr>
		<tr>
			<td><code>5 defeats 2</code></td>
			<td><code>2 defeats 3</code></td>
		</tr>
		<tr>
			<td><code>5 defeats 3</code></td>
			<td><code>4 defeats 3</code></td>
		</tr>
		<tr>
			<td><code>3 defeats 4</code></td>
			<td><code>5 defeats 3</code></td>
		</tr>
		<tr>
			<td><code>4 defeats 5</code></td>
			<td><code>5 defeats 4</code></td>
		</tr>
	</tbody>
</table>

<p>In the example above, <em>r</em><sub>1</sub>(1) = 0, <em>r</em><sub>1</sub>(2) = 1, <em>r</em><sub>1</sub>(3) = 3, <em>r</em><sub>1</sub>(4) = 3, <em>r</em><sub>1</sub>(5) = 3, and <em>r</em><sub>2</sub>(1) = 1, <em>r</em><sub>2</sub>(2) = 1, <em>r</em><sub>2</sub>(3) = 1, <em>r</em><sub>2</sub>(4) = 3,<em>r</em><sub>2</sub>(5) = 4. Therefore, the difference sequence is <em>D</em> = 1 0 2 0 1 in this example.</p>

<p>Given a sequence of <em>n</em> nonnegative integers, write a program to decide whether the input sequence can be a difference sequence of <em>P<sub>n</sub></em>.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing an integer <em>n</em>, (3 ≤ <em>n</em> ≤ 1,000,000), where <em>n</em> is the size of <em>P<sub>n</sub></em>. In the following line, a sequence of <em>n</em> integers between 0 and <em>n</em> is given, where each element in the sequence is separated by a single space.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. Print <code>YES</code> if the sequence can be the difference sequence of <em>P<sub>n</sub></em>, and print <code>NO</code> otherwise.</p>

