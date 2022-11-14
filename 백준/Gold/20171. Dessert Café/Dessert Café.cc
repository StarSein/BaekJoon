#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, k;
vector<vector<int>> graph;
vector<bool> complex;
vector<bool> good;
vector<bool> visited;

bool dfs(int cur, int par) {
    visited[cur] = true;
    bool ret = false;
    for (int &nex : graph[cur]) {
        if (nex != par && !visited[nex]) {
            if (complex[nex]) {
                ret = true;
                continue;
            }
            if (dfs(nex, cur)) {
                ret = true;
            }
        }
    }
    if (ret) {
        good[cur] = true;
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> k;
    graph.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v, w; cin >> u >> v >> w;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    complex.resize(n + 1);
    for (int i = 0; i < k; i++) {
        int node; cin >> node;
        complex[node] = true;
    }

    good.resize(n + 1, false);
    visited.resize(n + 1, false);
    for (int i = 1; i <= n; i++) {
        if (complex[i]) {
            good[i] = true;
            dfs(i, 0);
        }
    }
    cout << count(good.begin(), good.end(), true);
    return 0;
}