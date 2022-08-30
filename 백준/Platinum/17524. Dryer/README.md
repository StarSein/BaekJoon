# [Platinum I] Dryer - 17524 

[문제 링크](https://www.acmicpc.net/problem/17524) 

### 성능 요약

메모리: 2020 KB, 시간: 120 ms

### 분류

애드 혹(ad_hoc), 브루트포스 알고리즘(bruteforcing), 정렬(sorting)

### 문제 설명

<p>You have <em>n</em> wet clothes you just pulled off the washer. You also have an electronic dryer. The dryer is large enough to dry all the clothes in one run. You can control the temperature of the drying air. However, if you dry all the clothes at a high temperature, some clothes will be damaged. Precisely, let <em>t<sub>i</sub></em> denote the highest temperature at which the <em>i</em>-th cloth can be dried without damage and let <em>w<sub>i</sub></em> denote the wetness of the <em>i</em>-th cloth. If you dry this cloth at the temperature <em>T</em> (of course, <em>T</em> ≤ <em>t<sub>i</sub></em>), it will take <em>m<sub>i</sub></em> = 30 + (<em>t<sub>i</sub></em> − <em>T</em>) <em>w<sub>i</sub></em> minutes. If you dry two or more clothes at once, the time the dryer takes is the longest <em>m<sub>i</sub></em> of these clothes. You should dry all the clothes without damage.</p>

<p>Because the dryer uses a lot of electricity, you are going to partition <em>n</em> clothes into at most <em>k</em> groups and runs the dryer for each group. Given <em>n</em> clothes with <em>t<sub>i</sub></em>’s and <em>w<sub>i</sub></em>’s, write a program to find the minimum total time to dry all the clothes without damage.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/aaca7bb4-c93b-45d9-9efd-9d458a132212/-/preview/" style="width: 405px; height: 177px;"></p>

<p>This figure illustrates an example of <em>n</em> = 4, <em>k</em> = 2. In this case, the total time is 90 + 30 = 120 minutes.</p>

### 입력 

 <p>Your program is to read from standard input. The first line contains two integers <em>n</em> (1 ≤ <em>n</em> ≤ 1,000) and <em>k</em> (1 ≤ <em>k</em> ≤ 3). In the following <em>n</em> lines, the <em>i</em>-th line has two integers <em>t<sub>i</sub></em> (40 ≤ <em>t<sub>i</sub></em> ≤ 100) and <em>w<sub>i</sub></em> (0 ≤ <em>w<sub>i</sub></em> ≤ 100).</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line containing the minimum total time as an integer.</p>

