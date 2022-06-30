#include <iostream>
#include <vector>
using namespace std;
#define ROOT 1
typedef long long ll;
ll cntMove = 0;
vector<vector<int>> graph;

void dfs(int cur, int par, int depth) {
    if (graph[cur].size() == 1 && cur != ROOT) {
        cntMove += depth;
        return;
    }
    for (int &adj : graph[cur]) {
        if (adj != par) {
            dfs(adj, cur, depth + 1);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    graph.resize(n+1, vector<int>());
    int a, b;
    for (int i = 1; i < n; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    dfs(ROOT, 0, 0);
    cout << (cntMove & 1 ? "Yes" : "No");
}