# [Gold IV] A DP Problem - 7308 

[문제 링크](https://www.acmicpc.net/problem/7308) 

### 성능 요약

메모리: 12480 KB, 시간: 72 ms

### 분류

수학, 파싱, 문자열

### 제출 일자

2024년 9월 23일 00:21:03

### 문제 설명

<p>In this problem, you are to solve a very easy linear equation with only one variable x with no parentheses! An example of such equations is like the following:</p>

<p>2x - 4 + 5x + 300 = 98x</p>

<p>An expression in its general form, will contain a `=' character with two expressions on its sides. Each expression is made up of one or more terms combined by `+' or `-' operators. No unary plus or minus operators are allowed in the expressions. Each term is either a single integer, or an integer followed by the lower-case character x or the single character x which is equivalent to 1x.</p>

<p><br>
You are to write a program to find the value of x that satisfies the equation. Note that it is possible for the equation to have no solution or have infinitely many. Your program must detect these cases too.</p>

### 입력 

 <p>The first number in the input line, t ( 1 ≤ t ≤ 10) is the number of test cases, followed by t lines of length at most 255 each containing an equation. There is no blank character in the equations and the variable is always represented by the lower-case character `x'. The coefficients are integers in the range (0..1000) inclusive.</p>

### 출력 

 <p>The output contains one line per test case containing the solution of the equation. If s is the solution to the equation, the output line should contain [s](the ``floor" of s, i.e., the largest integer number less than or equal to s). The output should be `IMPOSSIBLE' or `IDENTITY' if the equation has no solution or has infinite solutions, respectively. Note that the output is case-sensitive.</p>

<p> </p>

