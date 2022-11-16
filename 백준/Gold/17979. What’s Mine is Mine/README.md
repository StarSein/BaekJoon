# [Gold IV] What’s Mine is Mine - 17979 

[문제 링크](https://www.acmicpc.net/problem/17979) 

### 성능 요약

메모리: 2256 KB, 시간: 4 ms

### 분류

다이나믹 프로그래밍(dp)

### 문제 설명

<p>The hot new video game “Mining Simulator” has just been released. In the game, rare earth mineral ores appear at certain times and you can mine them when they appear. After mining, you can later turn in the minerals for money. The quantity of mineral available during an appearance is proportional to the duration of the appearance and the price per unit of each mineral is decided beforehand.</p>

<p>The game contains a geological sensor that determines times when mineral ores will appear during a given day and at the beginning of each day you have a price list for each mineral. Assuming you mine out the mineral in exactly the amount of time that it is available, you cannot partially mine minerals (you must either not mine any of a given occurrence or mine all of it) and you can only mine one ore occurrence at a time.</p>

<p>Given a list of the prices of <em>m</em> minerals and <em>n</em> ore occurrences during a day, write a program to output the maximum amount of money you can earn from mining on that day.The mineral amount is the appearance time (end time – start time). You can mine an ore right after finishing the previous mining. In other words, one mined-ore’s end time can be same as another mined-ore’s start time. In the case depicted in Figure L.1, if you choose the mineral of type 1 that appears at time 2 and disappears at time 5, then the mineral amount is 5 − 2 = 3 and you earn 3 × 2 = 6. Next, if you choose the mineral of type 2 that appears at time 7 and disappears at time 11, then the mineral amount is 11 − 7 = 4 and you earn 4 × 3 = 12. Therefore, you earn 18 in total.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 451px; height: 296px;"></p>

<p style="text-align: center;">Figure L.1: A mining example. For each mineral (<em>s</em>, <em>e</em>, <em>t</em>), <em>s</em> is the start time, <em>e</em> is the end time and <em>t</em> is the mineral type. Therefore, the mineral amount is <em>e</em> - <em>s</em> and the possible earning is (<em>e</em> - <em>s</em>) × <em>t</em>'s price.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing two integers, <em>m</em> and <em>n</em> (1 ≤ <em>m</em> ≤ 100, 1 ≤ <em>n</em> ≤ 10,000), where <em>m</em> is the number of types of minerals and <em>n</em> is the number of ore occurrences during the day. The mineral types are labeled from 1 to <em>m</em>. The following <em>m</em> lines contain a single integer corresponding to the price of one unit of the <em>i</em>-th mineral type on that day (the price is between 1 and 10,000). The following <em>n</em> lines represent ore occurrences. Each line contains three integers, <em>s</em>, <em>e</em>, <em>t</em> where <em>s</em> is the start time, <em>e</em> is the end time and <em>t</em> is the mineral type in each ore occurrence with 0 < s < e < 15,000 and 1 ≤ t ≤ m. The amount of mineral at each occurrence is e - s.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should contain the maximum amount of money that can be earned on that day</p>

