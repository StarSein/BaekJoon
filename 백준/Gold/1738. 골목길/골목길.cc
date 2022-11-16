#include <iostream>
#include <algorithm>
#include <vector>
#include <iterator>
#include <cstring>
#include <queue>
using namespace std;

struct Edge {
    int u, v, w;
};

const int MAX_N = 101;
const int MAX_M = 2e4;
const int INF = 1e9;
int n, m;
int d[MAX_N];
int p[MAX_N];
Edge edges[MAX_M];
vector<vector<int>> digraph;
vector<int> ans;

bool bfs(int s, int e) {
    queue<int> q;
    bool visited[MAX_N];
    memset(visited, 0, sizeof(visited));

    q.push(s);
    visited[s] = true;
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        for (int nex : digraph[cur]) {
            if (visited[nex]) {
                continue;
            } 
            if (nex == e) {
                return true;
            }
            q.push(nex);
            visited[nex] = true;
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    digraph.resize(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v, w; cin >> u >> v >> w;
        edges[i] = {u, v, -w};
        digraph[u].push_back(v);
    }
    fill(d + 2, d + 1 + n, INF);
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < m; j++) {
            const Edge &e = edges[j];
            if (d[e.u] != INF && d[e.v] > d[e.u] + e.w) {
                d[e.v] = d[e.u] + e.w;
                p[e.v] = e.u;
            }
        }
    }
    bool loop = false;
    for (int j = 0; j < m; j++) {
        const Edge &e = edges[j];
        if (d[e.u] != INF && d[e.v] > d[e.u] + e.w) {
            if (bfs(e.v, n)) {
                loop = true;
                break;
            }
        }
    }

    if (loop || d[n] == INF) {
        cout << -1;
    } else {
        for (int node = n; node; node = p[node]) {
            ans.push_back(node);
        }
        copy(ans.rbegin(), ans.rend(), ostream_iterator<int>(cout, " "));
    }
}