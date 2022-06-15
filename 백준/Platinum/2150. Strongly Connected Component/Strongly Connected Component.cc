#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <iterator>
using namespace std;

vector<vector<int>> digraph, revgraph, vSCC;
vector<bool> visited;
stack<int> stNode;

void dfsDir(int node) {
    visited[node] = true;
    for (int& nextNode : digraph[node]) {
        if (!visited[nextNode]) 
            dfsDir(nextNode);
    }
    stNode.push(node);
}

vector<int> dfsRev(int node) {
    vector<int> scc;
    stack<int> st;
    st.push(node);
    visited[node] = true;
    int curNode;
    while (!st.empty()) {
        curNode = st.top();
        st.pop();
        for (int& nextNode : revgraph[curNode]) {
            if (!visited[nextNode]) {
                st.push(nextNode);
                visited[nextNode] = true;
            }
        }
        scc.push_back(curNode);
    }
    sort(scc.begin(), scc.end(), less<int>());
    return scc;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int v, e; cin >> v >> e;
    digraph.resize(v + 1, vector<int>());
    revgraph.resize(v + 1, vector<int>());
    int a, b;
    for (int i = 0; i < e; i++) {
        cin >> a >> b;
        digraph[a].push_back(b);
        revgraph[b].push_back(a);
    }
    visited.resize(v + 1, false);
    for (int node = 1; node <= v; node++) {
        if (!visited[node]) {
            dfsDir(node);
        }
    }
    fill(visited.begin(), visited.end(), false);
    int curNode;
    while (!stNode.empty()) {
        curNode = stNode.top();
        stNode.pop();
        if (!visited[curNode]) {
            vSCC.push_back(dfsRev(curNode));
        }
    }
    sort(vSCC.begin(), vSCC.end(), less<vector<int>>());
    cout << vSCC.size() << '\n';
    for (vector<int>& scc : vSCC) {
        copy(scc.begin(), scc.end(), ostream_iterator<int>(cout, " "));
        cout << -1 << '\n';
    }
}