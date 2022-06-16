#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;

const int INF = 100'000;
vector<vector<int>> graph;
vector<int> vOrd;
vector<bool> visited;
vector<pi> vCE;

template<typename a, typename b>
ostream& operator<<(ostream& os, pair<a, b>& e) {
    os << e.first << ' ' << e.second;
    return os;
}

int dfs(int curNode, int prevNode) {
    vOrd[curNode] = vOrd[prevNode] + 1;
    visited[curNode] = true;
    int curOrd, minOrd = INF;
    for (int& nextNode : graph[curNode]) {
        if (!visited[nextNode]) {
            curOrd = dfs(nextNode, curNode);
            if (vOrd[curNode] < curOrd) {
                if (curNode < nextNode)
                    vCE.emplace_back(curNode, nextNode);
                else
                    vCE.emplace_back(nextNode, curNode);
            }
            if (curOrd < minOrd)
                minOrd = curOrd;
        }
    }
    for (int& nextNode : graph[curNode]) {
        if (nextNode != prevNode) {
            if (vOrd[nextNode] < minOrd)
                minOrd = vOrd[nextNode];
        }
    }
    return minOrd;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int v, e; cin >> v >> e;
    graph.resize(v + 1, vector<int>());
    vOrd.resize(v + 1, 0);
    visited.resize(v + 1, false);
    int a, b;
    for (int i = 0; i < e; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int i = 1; i <= v; i++) {
        if (!visited[i]) {
            dfs(i, 0);
        }
    }
    sort(vCE.begin(), vCE.end(), less<pi>());
    cout << vCE.size() << '\n';
    for (pi& ce : vCE)
        cout << ce << '\n';
}