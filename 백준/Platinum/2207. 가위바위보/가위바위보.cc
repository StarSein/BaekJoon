#include <iostream>
#include <vector>
#include <stack>
using namespace std;

vector<vector<int>> digraph, revgraph;
vector<bool> visit;
vector<int> order;
stack<int> stNode;

int sccNum = 1;

void dfsDIR(int node) {
    visit[node] = true;
    for (int& next : digraph[node]) {
        if (!visit[next]) {
            dfsDIR(next);
        }
    }
    stNode.push(node);
}

void dfsREV(int node) {
    visit[node] = true;
    order[node] = sccNum;
    for (int& next : revgraph[node]) {
        if (!visit[next]) {
            dfsREV(next);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    int ub = 2*m+1;
    digraph.resize(ub, vector<int>());
    revgraph.resize(ub, vector<int>());
    int x, y;
    for (int i = 0; i < n; i++) {
        cin >> x >> y;
        if (x < 0)  x = ub + x;
        if (y < 0)  y = ub + y;
        digraph[ub-x].push_back(y);
        digraph[ub-y].push_back(x);
        revgraph[y].push_back(ub-x);
        revgraph[x].push_back(ub-y);
    }
    visit.resize(ub, false);
    for (int i = 1; i < ub; i++) {
        if (!visit[i]) {
            dfsDIR(i);
        }
    }
    fill(visit.begin(), visit.end(), false);
    order.resize(ub, 0);
    int node;
    while (!stNode.empty()) {
        node = stNode.top();
        stNode.pop();
        if (!visit[node]) {
            dfsREV(node);
            sccNum++;
        }
    }
    for (int i = 1; i <= m; i++) {
        if (order[i] == order[ub-i]) {
            cout << "OTL";
            return 0;
        }
    }
    cout << "^_^";
}