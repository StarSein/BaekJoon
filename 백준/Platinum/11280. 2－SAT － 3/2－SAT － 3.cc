#include <iostream>
#include <vector>
#include <stack>
#include <queue>
using namespace std;

vector<vector<int>> digraph, revgraph;
stack<int> stNode;
vector<bool> visited;
vector<int> check;
queue<int> scc;

void dfsDIR(int node) {
    visited[node] = true;
    for (int& nextNode : digraph[node]) {
        if (!visited[nextNode])
            dfsDIR(nextNode);
    }
    stNode.push(node);
}

queue<int> dfsREV(int node) {
    queue<int> scc;
    stack<int> st;
    st.push(node);
    visited[node] = true;
    int curNode;
    while (!st.empty()) {
        curNode = st.top();
        st.pop();
        scc.push(curNode);
        for (int& nextNode : revgraph[curNode]) {
            if (!visited[nextNode]) {
                st.push(nextNode);
                visited[nextNode] = true;
            }
        }
    }
    return scc;
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
            scc = dfsREV(curNode);
            while (!scc.empty()) {
                node = scc.front();
                scc.pop();
                check[node] = cntSCC;
                if (check[ub-node] == cntSCC) {
                    cout << 0;
                    return 0;
                }
            }
            cntSCC++;
        }
    }
    cout << 1;
    return 0;
}