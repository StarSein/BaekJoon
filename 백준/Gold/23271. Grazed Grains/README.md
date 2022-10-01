# [Gold IV] Grazed Grains - 23271 

[문제 링크](https://www.acmicpc.net/problem/23271) 

### 성능 요약

메모리: 17652 KB, 시간: 124 ms

### 분류

기하학(geometry), 수학(math), 확률론(probability), 무작위화(randomization)

### 문제 설명

<p>This year, there have been unusually many UFO sightings reported. Nobody knows if they are caused by optical illusions, weather phenomena, or secret technology being tested by foreign powers (terrestrial or not). UFO enthusiasts across the world rejoice, and speculations run wild. But someone who is not impressed is the farmer Celeste. Her large grain field has repeatedly been used as a landing site by the UFOs, destroying her crops. Celeste would like to bring whoever is responsible to justice, but before she can do so she must assess the damage caused by the unidentified flying offenders.</p>

<p>In total $n$ circular UFOs have landed in Celeste's field. The $i$th one left a crop circle which destroyed all crops within radius $r_i$ of the point $x_i, y_i$. The field is very large, so you can assume that it extends infinitely in all directions. Your task is to estimate the total area of crops destroyed by the UFOs. Celeste only needs a rough estimate of the true answer, and your answer will be counted as correct if its relative error is less than $10\%$.</p>

### 입력 

 <p>The first line of input contains one integer $n$ ($1 \leq n \leq 10$), the number of UFOs. Then follow $n$ lines, the $i$th of which contains the three integers $x_i$, $y_i$, and $r_i$ ($0 \leq x_i, y_i \leq 10$, $1 \leq r_i \leq 10$), describing the crop circle left by the $i$th UFO.</p>

### 출력 

 <p>Output the total area covered by the crop circles in the input, with a relative error of at most $1/10$.</p>

