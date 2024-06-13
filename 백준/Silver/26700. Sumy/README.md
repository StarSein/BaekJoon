# [Silver II] Sumy - 26700 

[문제 링크](https://www.acmicpc.net/problem/26700) 

### 성능 요약

메모리: 92312 KB, 시간: 1116 ms

### 분류

이분 탐색, 정렬

### 제출 일자

2024년 6월 13일 17:07:34

### 문제 설명

<p>Morze Bajtockie znane jest z wielu gatunków ryb, niespotykanych w innych akwenach wodnych świata. Najbardziej słynie z powodu zamieszkujących je bajtockich sumów, których okazy czasami ważą nawet kilka ton! Sumy bajtockie charakteryzuje również bardzo nietypowa dieta: gdy nadchodzi zima, zjadają one jedynie inne sumy żyjące w akwenie!</p>

<p>Algolina jest doktorantką Uniwersytetu Bajtockiego i jej projektem badawczym jest zbadanie tego zachowania sumów. Zdążyła już wyłapać wszystkie okazy z Morza Bajtockiego, zważyć je i wypuścić z powrotem do akwenu. Masa każdego suma, wyrażona w gramach, jest dodatnią liczbą całkowitą. Ponadto, Algolina zaobserwowała, że sum może zjeść innego suma tylko wtedy, gdy jest od niego cięższy. Innymi słowy, sum może żywić się jedynie sumami o ściśle mniejszej masie. W momencie, gdy jeden sum zje drugiego, lżejszego suma, jego masa wzrasta do sumy mas obu sumów, a zjedzony sum znika z morza.</p>

<p>Przyszedł czas na analizę wyników badań. Algolina zastanawia się, czy może się okazać, że w Morzu Bajtockim pozostanie tylko jeden sum. Dokładniej, jeśli w wyniku powyższego procesu żywienia się sumów w akwenie pozostanie dokładnie jeden sum, to ryba ta staje się królem Morza Bajtockiego. Naturalnie więc narzuca się pytanie: które ryby mogą stać się królami Morza Bajtockiego?</p>

### 입력 

 <p>Pierwszy wiersz wejścia zawiera jedną liczbę całkowitą n (2 ≤ n ≤ 500 000) oznaczającą liczbę sumów w Morzu Bajtockim.</p>

<p>Drugi wiersz składa się z n liczb całkowitych a<sub>1</sub>, a<sub>2</sub>, . . . , a<sub>n</sub> (1 ≤ a<sub>i</sub> ≤ 10<sup>9</sup>) i opisuje masy kolejnych sumów w morzu – a<sub>i</sub> oznacza masę i-tego suma wyrażoną w gramach.</p>

### 출력 

 <p>W pierwszym i jedynym wierszu wyjścia wypisz ciąg n znaków; i-ty znak opisu (1 ≤ i ≤ n) powinien być równy T, jeśli i-ty sum może stać się królem Morza Bajtockiego, zaś N w przeciwnym przypadku.</p>

