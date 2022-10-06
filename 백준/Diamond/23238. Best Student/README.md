# [Diamond V] Best Student - 23238 

[문제 링크](https://www.acmicpc.net/problem/23238) 

### 성능 요약

메모리: 146496 KB, 시간: 576 ms

### 분류

mo's(mo), 오프라인 쿼리(offline_queries), 제곱근 분할법(sqrt_decomposition)

### 문제 설명

<p>The SY School selects a best student every day. Given a list of the best students for $n$ days, the school wants to know a student who is most often selected as the best student during a period $[S, E]$ of days from $S$ to $E$. The school plans to award for the student with a gift.</p>

<p>Given a list of best students for $n$ consecutive days and $q$ queries $\{(S_1, E_1), \dots, (S_q, E_q)\}$, write a program to find the best student to be selected most often during the period $[S_i, E_i]$ for each query $(S_i, E_i)$.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing two integers, $n$ and $q$, representing the number of days and the number of queries, respectively, where $1 \le n \le 100,000$ and $1 \le q \le 100,000$. Students have unique id numbers between $1$ and $10^9$. The next line consists of $n$ positive integers representing $n$ id numbers for best students, ordered from day $1$ to day $n$. Each of the following $q$ lines consists of two positive integers, $S_i$ and $E_i$, that represent a query $(S_i, E_i)$, where $[S_i, E_i]$ is the period of days from $S_i$ to $E_i$ . Note that $1 \le S_i \le E_i \le n$ for $i = 1, \dots, q$.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly $q$ lines. The $i$-th line should contain the id number of the student selected most often as best student during the $i$-th period $[S_i, E_i]$. When there are more than one such students, the program should print the largest one among their id numbers.</p>

