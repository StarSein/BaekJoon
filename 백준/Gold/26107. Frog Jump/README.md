# [Gold IV] Frog Jump - 26107 

[문제 링크](https://www.acmicpc.net/problem/26107) 

### 성능 요약

메모리: 165428 KB, 시간: 868 ms

### 분류

누적 합(prefix_sum), 스위핑(sweeping)

### 문제 설명

<p>A frog is living in a beautiful lake. On the lake, there are a lot of lotus leaves floating in a row, which are represented by closed intervals on the line. The frog likes to be on lotus leaves and moves between them.</p>

<p>The $n$ closed intervals, representing lotus leaves, on the line, that is, on the $x$-axis are given and the frog is initially on some interval $I_0$. The frog can move from an interval $I$ to an interval $J$ if they overlap. Two intervals overlap if they share a common point. So the frog can move through overlapping intervals. When the frog is moving to the right (left) through the overlapping intervals, it may reach an interval $H$, where it can no longer move to the right (left) from the right (left) endpoint of $H$. In this case, the frog can <em>jump</em> to the interval $K$ with the smallest (largest) left (right) endpoint among intervals whose left (right) endpoint is greater (smaller) than the right (left) endpoint of $H$ if they exist. Then, the <em>jump length</em> is defined to be the length between the right (left) endpoint of $H$ and the left (right) endpoint of $K$. See Figure F.1.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 384px; height: 107px;"></p>

<p style="text-align: center;">Figure F.1 Jump length</p>

<p>A sequence of $k$ intervals $I_1, I_2, \dots , I_k$ is given and the frog should visit the intervals in order from the initial interval $I_0$. In this travel, the frog has to jump if necessary.</p>

<p>For example, in Figure F.2, eight intervals $[1, 8]$, $[2, 4]$, $[5, 11]$, $[13, 15]$, $[15, 17]$, $[16, 18]$, $[19, 22]$ and $[20, 22]$ are given and numbered from $1$ and $8$. The frog is initially on interval $1$. Intervals $3, 7, 4, 6, 3$ which the frog should visit in a sequence are given. Then the frog moves from interval $1$ to $3$ with no jump, and it moves from $3$ to $7$ with two jumps, say, $3 → 4$ and $6 → 7$ whose jump length is $3$ totally. In this movement, the frog passes through the interval $4$. Nevertheless, it should visit the interval $4$ after the interval $7$. Then, there are two jumps during the movements from $7$ to $4$ and from $6$ to $3$ whose jump length is $3$ totally. Thus after the frog visits all the given intervals, the total jump length is $6$.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 488px; height: 90px;"></p>

<p style="text-align: center;">Figure F.2 The given eight intervals</p>

<p>Given $n$ intervals on the line and a sequence of $k$ intervals, write a program to output the total jump length during the travel that the frog visits the $k$ intervals in order from its initial interval $1$.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing two integers, $n$ and $k$ ($1 ≤ n ≤ 100\,000$ and $1 ≤ k ≤ 1\,000\,000$), where $n$ is the number of intervals and $k$ is the number of intervals which the frog should visit. The intervals are numbered from $1$ to $n$ and the initial location of the frog is always $1$. In the following $n$ lines, the $i$-th line contains two integers $a$ and $b$ ($0 ≤ a < b ≤ 10^9$) that represent the left and right endpoints of interval $i$, respectively. The intervals are given in increasing order of their left endpoints – if they are same, then in increasing order of the right endpoints. Also the intervals are all distinct. The next line contains $k$ integers that represent the intervals which the frog should visit in order. These integers are between $1$ and $n$ and can be in duplicate.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should contain the total jump length of frog when it visits the given $k$ intervals in order.</p>

