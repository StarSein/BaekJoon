#include <iostream>
#include <vector>
using namespace std;
typedef struct Edge {
    int s, e, t;
} Edge;


const int DUMMY = 0;
const int MAX_N = 500, INF = static_cast<int>(1e9);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int t; cin >> t;
    while (t--) {
        vector<int> dist(MAX_N + 1, INF);
        vector<Edge> edges;
        int n, m, w; cin >> n >> m >> w;
        int s, e, t;
        for (int i = 0; i < m; i++) {
            cin >> s >> e >> t;
            edges.push_back({s, e, t});
            edges.push_back({e, s, t});
        }
        for (int i = 0; i < w; i++) {
            cin >> s >> e >> t;
            edges.push_back({s, e, -t});
        }
        for (int i = 1; i <= n; i++) {
            edges.push_back({DUMMY, i, 0});
        }
        dist[DUMMY] = 0;
        for (int i = 0; i < n; i++) {
            for (Edge& edge : edges) {
                if (dist[edge.s] != INF) {
                    dist[edge.e] = min(dist[edge.e], dist[edge.s] + edge.t);
                }
            }
        }
        bool flag = false;
        for (Edge& edge : edges) {
                if (dist[edge.s] != INF) {
                    if (dist[edge.e] > dist[edge.s] + edge.t) {
                        flag = true;
                        break;
                    }
                }
            }
        cout << (flag ? "YES" : "NO") << '\n';
    }
}