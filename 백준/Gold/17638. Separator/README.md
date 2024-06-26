# [Gold IV] Separator - 17638 

[문제 링크](https://www.acmicpc.net/problem/17638) 

### 성능 요약

메모리: 130624 KB, 시간: 912 ms

### 분류

자료 구조, 스택

### 제출 일자

2024년 6월 27일 02:35:04

### 문제 설명

<p>Let A = (a<sub>1</sub>, a<sub>2</sub>, . . .) be a sequence of distinct integers. An index j is called a separator if the following two conditions hold:</p>

<ul>
	<li>for all k < j: a<sub>k</sub> < a<sub>j</sub>,</li>
	<li>for all k > j: a<sub>k</sub> > a<sub>j</sub>.</li>
</ul>

<p>In other words, the array A consists of three parts: all elements smaller then a<sub>j</sub>, then a<sub>j</sub> itself, and finally all elements greater than a<sub>j</sub>.</p>

<p>For instance, let A = (30, 10, 20, 50, 80, 60, 90). The separators are the indices 4 and 7, corresponding to the values 50 and 90.</p>

<p>The sequence A is initially empty. You are given a sequence a<sub>1</sub>, . . . , a<sub>n</sub> of elements to append to A, one after another. After appending each a<sub>i</sub>, output the current number s<sub>i</sub> of separators in the sequence you have.</p>

<p>The input format is selected so that you have to compute the answers online. Instead of the elements a<sub>i</sub> you should append to A, you are given a sequence b<sub>i</sub>.</p>

<p>Process the input as follows:</p>

<p>The empty sequence A contains s0 = 0 separators.</p>

<p>For each i from 1 to n, inclusive:</p>

<ol>
	<li>Calculate the value a<sub>i</sub> = (b<sub>i</sub> + s<sub>i−1</sub>) mod 10<sup>9</sup>.</li>
	<li>Append a<sub>i</sub> to the sequence A.</li>
	<li>Calculate s<sub>i</sub> : the number of separators in the current sequence A.</li>
	<li>. Output a line containing the value s<sub>i</sub>.</li>
</ol>

### 입력 

 <p>The first line contains a single integer n (1 ≤ n ≤ 10<sup>6</sup>): the number of queries to process.</p>

<p>Then, n lines follow. The i-th of these lines contains the integer b<sub>i</sub> (0 ≤ b<sub>i</sub> ≤ 10<sup>9</sup> − 1). The values b<sub>i</sub> are chosen in such a way that the values a<sub>i</sub> you’ll compute will all be distinct.</p>

### 출력 

 <p>As described above, output n lines with the values s<sub>1</sub> through s<sub>n</sub>.</p>

