/* 이전 풀이 반례

2 2
1 2
-1 -2

이 경우처럼 사이클이 존재할 경우
위상 정렬이 되지 않는다.

따라서 위상 정렬 순서와 같은 scc 번호 순서를 직접 이용해야 한다.
*/

#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <iterator>
using namespace std;

vector<vector<int>> digraph, revgraph;
vector<bool> visited;
vector<int> check;
stack<int> stNode;

void dfsDIR(int node) {
    visited[node] = true;
    for (int& nextNode : digraph[node]) {
        if (!visited[nextNode])
            dfsDIR(nextNode);
    }
    stNode.push(node);
}

void dfsREV(int node, int sccNum) {
    visited[node] = true;
    check[node] = sccNum;
    for (int& nextNode : revgraph[node]) {
        if (!visited[nextNode])
            dfsREV(nextNode, sccNum);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    int ub = 2*n+1;
    digraph.resize(ub, vector<int>());
    revgraph.resize(ub, vector<int>());
    visited.resize(ub, false);
    check.resize(ub, 0);
    int a, b;
    for (int i = 0; i < m; i++) {
        cin >> a >> b;
        if (a < 0)  a = ub + a;
        if (b < 0)  b = ub + b;
        digraph[ub-a].push_back(b);
        digraph[ub-b].push_back(a);
        revgraph[b].push_back(ub-a);
        revgraph[a].push_back(ub-b);
    }

    for (int i = 1; i < ub; i++) {
        if (!visited[i])
            dfsDIR(i);
    }
    fill(visited.begin(), visited.end(), false);
    int curNode, node, cntSCC = 1;
    while (!stNode.empty()) {
        curNode = stNode.top();
        stNode.pop();
        if (!visited[curNode]) {
            dfsREV(curNode, cntSCC++);
        }
    }
    for (int i = 1; i <= n; i++) {
        if (check[i] == check[ub-i]) {
            cout << 0;
            return 0;
        }
    }

    cout << 1 << '\n';
    for (int i = 1; i <= n; i++)
        cout << (check[i] > check[ub-i]) << ' ';
    return 0;
}