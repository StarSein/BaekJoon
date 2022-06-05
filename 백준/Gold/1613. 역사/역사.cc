#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n;
vector<vector<int>> graph;

void floydWarshall() {
    for (int m = 1; m <= n; m++) {
        for (int l = 1; l <= n; l++) {
            for (int r = 1; r <= n; r++) {
                if (graph[l][m] && graph[l][m] == graph[m][r])
                    graph[l][r] = graph[l][m];
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int k; cin >> n >> k;
    graph.resize(n + 1, vector<int>(n + 1, 0));
    int u, v;
    for (int i = 0; i < k; i++) {
        cin >> u >> v;
        graph[u][v] = -1;
        graph[v][u] = 1;
    }
    floydWarshall();
    int s; cin >> s;
    for (int i = 0; i < s; i++) {
        cin >> u >> v;
        cout << graph[u][v] << '\n';
    }
}