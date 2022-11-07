# [Platinum II] Trio - 23577 

[문제 링크](https://www.acmicpc.net/problem/23577) 

### 성능 요약

메모리: 2096 KB, 시간: 652 ms

### 분류

포함 배제의 원리(inclusion_and_exclusion)

### 문제 설명

<p>Let $A$ be any set of $n$ natural numbers whose decimal representations consist of exactly four digits without $0$ in any decimal place.</p>

<p>A <em>trio</em> is a set of three numbers $\{a, b, c\}$ chosen from $A$ such that the following conditions are fulfilled simultaneously:</p>

<ul>
	<li>The ones decimals of three numbers $a$, $b$, $c$ are either all equal or all distinct.</li>
	<li>The tens decimals of three numbers $a$, $b$, $c$ are either all equal or all distinct.</li>
	<li>The hundreds decimals of three numbers $a$, $b$, $c$ are either all equal or all distinct.</li>
	<li>The thousands decimals of three numbers $a$, $b$, $c$ are either all equal or all distinct.</li>
</ul>

<p>For examples, $\{1425, 1113, 1354\}$ is a trio if the three numbers are members of $A$ because the ones decimals of the three numbers are all distinct, their tens decimals are all distinct, their hundreds decimals are all distinct, and their thousands decimals are all equal. The set $\{1425, 1113, 5436\}$, however, is not a trio, even if $A$ contains those three numbers.</p>

<p>Given a set $A$ as input, write a program that computes and prints out the number of different trios.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line consisting of a single integer $n$ ($1 ≤ n ≤ 2,000$) that represents the number of members in $A$. Each of the following $n$ lines consists of a positive integer in decimal form that consists of exactly four digits without $0$ in any decimal place. These $n$ numbers are supposed to be all distinct and the members of the input set $A$.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should consists of a single integer that represents the number of different trios for the input set $A$.</p>

