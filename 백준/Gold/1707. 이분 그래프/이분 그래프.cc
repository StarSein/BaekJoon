#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;

const int MAX_V = 2e4+1;
vector<vector<int>> graph(MAX_V);
bool visit[MAX_V], check[MAX_V];
queue<int> q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int K; cin >> K;
    while (K--) {
        memset(visit, 0, sizeof(visit));
        memset(check, 0, sizeof(check));

        int V, E; cin >> V >> E;
        for (int i = 1; i <= V; i++) {
            graph[i].clear();
        }
        for (int i = 0; i < E; i++) {
            int u, v; cin >> u >> v;
            graph[u].push_back(v);
            graph[v].push_back(u);
        }
        for (int s = 1; s <= V; s++) {
            if (!visit[s]) {
                q.push(s);
                check[s] = true;
                visit[s] = true;
                while (!q.empty()) {
                    int cur = q.front();
                    q.pop();
                    for (int &adj : graph[cur]) {
                        if (!visit[adj]) {
                            q.push(adj);
                            check[adj] = !check[cur];
                            visit[adj] = true;
                        }
                    }
                }
            }
        }
        bool flag = true;
        for (int cur = 1; cur <= V; cur++) {
            for (int &adj : graph[cur]) {
                if (check[cur] == check[adj]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        cout << (flag ? "YES" : "NO") << '\n';
    }
}