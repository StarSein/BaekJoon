# [Platinum V] Forbidden Turns - 26106 

[문제 링크](https://www.acmicpc.net/problem/26106) 

### 성능 요약

메모리: 157800 KB, 시간: 1408 ms

### 분류

자료 구조(data_structures), 다익스트라(dijkstra), 그래프 이론(graphs), 해시를 사용한 집합과 맵(hash_set), 트리를 사용한 집합과 맵(tree_set)

### 문제 설명

<p>A GPS navigation company ICPC(International Control Perfection Company) designs a car navigation system for a transportation network. The system abstracts the transportation network as a directed graph $G(V, E)$ with edge cost $c$. For a directed edge $(v, w) ∈ E$, $c(v, w)$ denotes the distance from a place $v ∈ V$ to another place $w ∈ V$. The company wants to implement the shortest path module in the system. To reflect the normal situation that we cannot turn to some directions in a junction of transportation network, we want to find the shortest path that does not contain forbidden turns as a subpath.</p>

<p>A path from $v$ to $w$ is a sequence of vertices $(v_1, v_2, \cdots , v_k)$ where $v_1 = v$, $v_k = w$, $(v_i, v_{i+1}) ∈ E$ for $1 ≤ i ≤ k - 1$. Unlike the common definition of the path, you are here allowed to repeat the same vertices in a path one or more. A subpath of a path is a contiguous subsequence of the sequence that corresponds to the path. A forbidden turn is a path (i.e., triplet) $(x, y, z)$ such that $x, y, z ∈ V$ and $(x, y) ∈ E$ and $(y, z) ∈ E$. The distance of a path $(v_1, v_2, \cdots , v_k)$ is defined as $\sum_{i=1}^{k-1}{c(v_i, v_{i+1}}$. The shortest path from $v ∈ V$ to $w ∈ V$ is a path from $v$ to $w$ with the minimum distance. The company wants to find the distance of the shortest path that avoids the forbidden turns between two designated vertices. Note that the shortest path from $v ∈ V$ to $v ∈ V$ has distance $0$ and it avoids all the forbidden turns.</p>

<p>Let's see the following example in the figure below. Each edge cost lies beside each edge and the list of three forbidden turns are in the right box. The shortest path without forbidden turns from the vertex $3$ to the vertex $2$ is $(3, 0, 1, 5, 4, 1, 2)$ which is denoted as blue arrows in the following figure. The distance of the shortest path is $3 + 12 + 4 + 7 + 8 + 2 = 36$. Note that we cannot take the shorter paths $(3, 0, 1, 2)$ and $(3, 0, 1, 5, 2)$ since they contain forbidden turns $(0, 1, 2)$ and $(1, 5, 2)$, respectively.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 497px; height: 202px;"></p>

<p>Given a directed graph $G(V, E)$ with the edge cost $c$, a set of forbidden turns $F$, and two vertices $v$ and $w$, write a program to output the distance of the shortest path from $v$ to $w$ that avoids all the forbidden turns. We assume that out-degree of each vertex $v$, i.e., the number of edges that starts from $v$ is at most $10$.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing three integers, $m$, $n$, and $k$. ($0 ≤ m ≤ 10n$, $1 ≤ n ≤ 30\,000$, $0 ≤ k ≤ 500\,000$), where $m$ is the number of directed edges, $n$ is the number of vertices, and $k$ is the number of forbidden turns of the given directed graph $G(V, E)$. Here, $k$ is less than or equal to the number of all the possible forbidden turns in the given directed graph $G(V, E)$. The vertices are numbered from $0$ to $n - 1$. The second line contains two integers $v$ and $w$ which denote the source and destination vertices, respectively. In the following mm lines, the $i$-th line contains three integers $x_i$, $y_i$, and $c_i$ ($0 ≤ x_i ≠ y_i ≤ n - 1$ and $0 ≤ c_i ≤ 10^3$) which denotes an edge $(x_i, y_i) ∈ E$ and its cost, respectively. In the following $k$ lines, the $i$-th line contains three integers $x_i$, $y_i$, and $z_i$ which denote a forbidden turn $(x_i, y_i, z_i)$.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should contain an integer that represents the distance of the shortest path from $v$ to $w$ which avoids all the forbidden turns. If such a path does not exist, the line should contain $-1$.</p>

