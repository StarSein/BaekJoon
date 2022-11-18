# [Gold II] Working Plan - 16368 

[문제 링크](https://www.acmicpc.net/problem/16368) 

### 성능 요약

메모리: 10932 KB, 시간: 272 ms

### 분류

자료 구조(data_structures), 그리디 알고리즘(greedy), 우선순위 큐(priority_queue)

### 문제 설명

<p>ICPC manager plans a new project which is to be carried out for <em>n</em> days. In this project, <em>m</em> persons numbered from 1 to <em>m</em> are supposed to work. Each day <em>j</em> (1 ≤ <em>j</em> ≤ <em>n</em>) requires <em>d<sub>j</sub></em> persons, and each person <em>i</em> (1 ≤ <em>i</em> ≤ <em>m</em>) wants to work <em>w<sub>i</sub></em><sub> </sub>days.</p>

<p>To increase the efficiency in performing the project, the following two conditions should be satisfied:</p>

<ol>
	<li>each person works for only consecutive <em>w</em> days when he/she works, and</li>
	<li>each person can work again after he/she has a rest for at least <em>h</em> days.</li>
</ol>

<p>ICPC manager wants to find a working plan to assign the working days for all persons such that the number of working days of each person <em>i</em> (1 ≤ <em>i</em> ≤ <em>m</em>) is equal to <em>w<sub>i</sub></em> and the number of persons who work for each day <em>j</em> (1 ≤ <em>j</em> ≤ <em>n</em>) is equal to <em>d<sub>j</sub></em>, and above two conditions are also satisfied.</p>

<p>For example, assume the project is carried out for <em>n</em> = 9 days, and <em>m</em> = 4 persons participate in the project. Let <em>w</em> = 2 and <em>h</em> = 1. Also, assume (<em>w</em><sub>1</sub>, <em>w</em><sub>2</sub>, <em>w</em><sub>3</sub>, <em>w</em><sub>4</sub>) = (4, 4, 6, 2) and (<em>d</em><sub>1</sub>, <em>d</em><sub>2</sub>, <em>d</em><sub>3</sub>, <em>d</em><sub>4</sub>, <em>d</em><sub>5</sub>, <em>d</em><sub>6</sub>, <em>d</em><sub>7</sub>, <em>d</em><sub>8</sub>, <em>d</em><sub>9</sub>) = (1, 3, 2, 1, 2, 1, 1, 3, 2). The table below shows a feasible solution where the <em>i</em>-th row corresponds to person <em>i</em>, and the <em>j</em>-th column corresponds to day <em>j</em>. If person <em>i</em> works or has a rest in day <em>j</em>, the value of the table element with row <em>i</em> and column <em>j</em> is 1 or 0, respectively.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 366px; height: 117px;"></p>

<p>Given <em>m</em>, <em>n</em>, <em>w</em>, <em>h</em>, <em>w<sub>i</sub></em> (1 ≤ <em>i</em> ≤ <em>m</em>) which is a multiple of <em>w</em>, and <em>d<sub>j</sub></em> (1 ≤ <em>j</em> ≤ <em>n</em>), write a program to find a feasible solution as a working plan.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing four integers, <em>m</em>, <em>n</em>, <em>w</em>, <em>h</em> (1 ≤ <em>m</em> ≤ 2,000, 1 ≤ <em>n</em> ≤ 2,000, 1 ≤ <em>w</em>, <em>h</em> ≤ <em>n</em>). The following line contains <em>m</em> integers where the <em>i</em>-th (1 ≤ <em>i</em> ≤ <em>m</em>) integer represents <em>w<sub>i</sub></em> (1 ≤ <em>w<sub>i</sub></em> ≤ <em>n</em>) which is a multiple of <em>w</em>. The next line contains <em>n</em> integers where the <em>j</em>-th (1 ≤ <em>j</em> ≤ <em>n</em>) integer represents <em>d<sub>j</sub></em> (0 ≤ <em>d<sub>j</sub></em> ≤ <em>m</em>).</p>

### 출력 

 <p>Your program is to write to standard output. If there is a feasible working plan, print 1 in the first line followed by <em>m</em> lines, each <em>i</em>-th (1 ≤ <em>i</em> ≤ <em>m</em>) line should contain <em>w<sub>i</sub></em>/<em>w</em> integers. These integers form an increasing sequence of first days that person <em>i</em> works in the feasible plan. If there is no feasible working plan, print only -1 in the first line. The first sample below corresponds to the example given in the table above.</p>

