# [Gold II] Triangulation - 17977 

[문제 링크](https://www.acmicpc.net/problem/17977) 

### 성능 요약

메모리: 5928 KB, 시간: 4 ms

### 분류

애드 혹(ad_hoc), 다이나믹 프로그래밍(dp), 수학(math)

### 문제 설명

<p>A regular <em>n</em>-sided polygon <em>P</em> can be partitioned into <em>n</em> − 2 triangles by adding non-crossing line segments connecting two vertices of <em>P</em>. For example, a square can be partitioned into two triangles, a regular pentagon can be partitioned into three triangles, and a regular hexagon can be partitioned into four triangles. The resulting set of triangles is called a <em>triangulation</em> of <em>P</em>. There exist two or more triangulations of P if <em>n</em> is greater than three.</p>

<p>Once a triangulation <em>T</em> of <em>P</em> is chosen, the distance between two triangles <em>a</em> and <em>b</em> in <em>T</em> is defined to be the number of hops crossing the borders of two adjacent triangles when you travel from <em>a</em> to <em>b</em>. Note that you must stay inside the polygon <em>P</em>, at any time during this travel, that is, it is not allowed to hop crossing the border of <em>P</em>.</p>

<p>For example, the distance of <em>a</em> and <em>d</em> in the triangulation shown in Figure J.1 is three since the triangles, <em>a</em>, <em>b</em>, <em>c</em>, and <em>d</em>, should be visited to travel from <em>a</em> to <em>d</em>, and you have to hop three times crossing borders between triangles.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 175px; height: 199px;"></p>

<p style="text-align: center;">Figure J.1 A triangulation of a regular hexagon</p>

<p>The diameter of a triangulation <em>T</em> is the maximum of the distances between all pairs of triangles in <em>T</em>. Write a program to find a triangulation of a regular <em>n</em>-sided polygon <em>P</em> whose diameter is the minimum over all possible triangulations and print its diameter.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing <em>n</em> (3 ≤ <em>n</em> ≤ 1,000,000), where <em>n</em> is the number of sides of the regular <em>n</em>-sided polygon.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should contain the minimum diameter of the triangulations of a regular <em>n</em>-sided polygon.</p>

