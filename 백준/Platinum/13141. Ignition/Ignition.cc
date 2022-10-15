#include <iostream>
#include <vector>
#include <algorithm>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;
typedef tuple<int, int, int> ti;

const int MAX_N = 201, INF = 20'000;
int N, M; 
int dist[MAX_N][MAX_N];
vector<vector<pi>> graph;
vector<ti> edges;


void init() {
    for (int i = 1; i <= N; i++) {
        dist[i][i] = 0;
    }
    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}

float getBurnTime(int s) {
    int doubleTime = 0;
    for (const auto &[a, b, l] : edges) {
        if (a == b) {
            doubleTime = max(doubleTime, 2 * dist[s][a] + l);
            continue;
        }
        int minD = min(dist[s][a], dist[s][b]);
        int maxD = max(dist[s][a], dist[s][b]);
        if (maxD - minD >= l) {
            doubleTime = max(doubleTime, 2 * maxD);
        } else {
            doubleTime = max(doubleTime, 2 * maxD + (l - (maxD - minD)));
        }
    }
    return static_cast<float>(doubleTime) / 2;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fill(&dist[0][0], &dist[0][0] + MAX_N * MAX_N, INF);
    cin >> N >> M;
    graph.resize(N + 1);
    edges.resize(M);
    for (int i = 0; i < M; i++) {
        int s, e, l; cin >> s >> e >> l;
        graph[s].emplace_back(e, l);
        graph[e].emplace_back(s, l);
        dist[s][e] = min(dist[s][e], l);
        dist[e][s] = min(dist[e][s], l);
        edges[i] = {s, e, l};
    }
    init();

    float ans = INF;
    for (int i = 1; i <= N; i++) {
        ans = min(ans, getBurnTime(i));
    }

    cout.precision(1);
    cout << fixed;
    cout << ans;
    return 0;
}