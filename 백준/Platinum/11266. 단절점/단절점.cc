#include <iostream>
#include <vector>
#include <numeric>
using namespace std;

const int INF = 10'000;
vector<vector<int>> graph;
vector<int> vOrder;
vector<bool> visited, isCV;

int dfs(int node) {
    visited[node] = true;
    int curOrder, minOrder = INF;
    for (int& next : graph[node]) {
        if (!visited[next]) {
            vOrder[next] = vOrder[node] + 1;
            curOrder = dfs(next);
            if (vOrder[node] == curOrder) {
                isCV[node] = true;
            }
            if (curOrder < minOrder)
                minOrder = curOrder;
        }
    }
    for (int& next : graph[node])
        if (vOrder[next] < minOrder)
            minOrder = vOrder[next];
    return minOrder;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int v, e; cin >> v >> e;
    graph.resize(v + 1, vector<int>());
    vOrder.resize(v + 1);
    visited.resize(v + 1, false);
    isCV.resize(v + 1, false);

    int a, b;
    for (int i = 0; i < e; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int root = 1; root <= v; root++) {
        if (visited[root])
            continue;

        int cnt = 0;
        visited[root] = true;
        for (int& next : graph[root]) {
            if (!visited[next]) {
                vOrder[next] = 2;
                dfs(next);
                cnt++;
            }
        }
        if (cnt > 1)
            isCV[root] = true;
    }

    cout << accumulate(isCV.begin(), isCV.end(), 0) << '\n';
    for (int i = 1; i <= v; i++)
        if (isCV[i])
            cout << i << ' ';
}