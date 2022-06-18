#include <iostream>
#include <vector>
using namespace std;

const int ROOT = 1;
vector<vector<int>> graph;
vector<bool> isCV;

void dfs(int current, int parent) {
    if (graph[current].size() == 1) {
        isCV[current] = false;
    }
    for (int& next : graph[current]) {
        if (next != parent) {
            dfs(next, current);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    graph.resize(n + 1, vector<int>());
    isCV.resize(n + 1, true);
    int a, b;
    for (int i = 1; i < n; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    dfs(ROOT, 0);
    int q; cin >> q;
    int t, k;
    for (int i = 0; i < q; i++) {
        cin >> t >> k;
        if (t == 1) {
            cout << (isCV[k] ? "yes" : "no") << '\n';
        } else {
            cout << "yes" << '\n';
        }
    }
}