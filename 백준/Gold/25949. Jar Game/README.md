# [Gold II] Jar Game - 25949 

[문제 링크](https://www.acmicpc.net/problem/25949) 

### 성능 요약

메모리: 10068 KB, 시간: 8 ms

### 분류

다이나믹 프로그래밍(dp), 게임 이론(game_theory)

### 문제 설명

<p>Two players <strong><code>F</code></strong>(irst) and <strong><code>S</code></strong>(econd) play a game with three jars each containing $a$, $b$ and $c$ pebbles. The game is played according to the following rules:</p>

<ul>
	<li>Two players take turns one at a time. For each turn, the player chooses a jar and takes some pebbles from the jar.</li>
	<li><strong><code>F</code></strong> starts first, then <strong><code>S</code></strong> next. These turns alternate till the game ends.</li>
	<li>The number of pebbles that can be drawn at the $k$-th turn is $k$; the number of pebbles taken by <strong><code>F</code></strong> at the first turn is $1$. So in the next turn, <strong><code>S</code></strong> takes $2$ pebbles, then at the third turn, <strong><code>F</code></strong> takes $3$ pebbles, and so on.</li>
	<li>For each turn, the pebbles must be taken out of only one jar.</li>
	<li>At the $k$-th turn, if the number of pebbles remaining in the chosen jar is less than $k$, the player should take all the remained pebbles in that jar. If the remained pebbles is greater than $k$ in the chosen jar, then the player is not allowed to take less than $k$ pebbles from the jar.</li>
	<li>If there are no pebbles left in the three jars, then the game is over. The player with more pebbles wins the game when the game is over. So in some cases, a draw may be possible if the number of pebbles two players took is the same.</li>
	<li>We assume that two players <strong><code>F</code></strong> and <strong><code>S</code></strong> do their best to win.</li>
	<li>Two players always know the exact number of the pebbles remained in three jars. There is no hidden information in this jar game.</li>
</ul>

<p>Given the number of pebbles in three jars, write a program to find who is the winner or if the draw is possible.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing three integers, $a$, $b$ and $c$ ($1 ≤ a, b, c ≤ 100$) denoting the number of pebbles in three jars at the beginning.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should contain a capital letter among {<strong><code>F</code></strong>, <strong><code>S</code></strong>, <strong><code>D</code></strong>}. {<strong><code>F</code></strong>, <strong><code>S</code></strong>} means the winner among two players and <strong><code>D</code></strong> denotes a draw when the game ends.</p>

