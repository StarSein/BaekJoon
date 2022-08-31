#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

vector<vector<int>> graph;
vector<int> order;
int cnt = 0;

void dfs(int node) {
    order[node] = ++cnt;
    for (int adj : graph[node]) {
        if (!order[adj]) {
            dfs(adj);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, r; cin >> n >> m >> r;
    graph.resize(n + 1, vector<int>());
    for (int i = 0; i < m; i++) {
        int u, v; cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    for (int i = 1; i <= n; i++) {
        sort(graph[i].begin(), graph[i].end(), less<int>());
    }

    order.resize(n + 1, 0);
    dfs(r);
    copy(next(order.begin(), 1), order.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}