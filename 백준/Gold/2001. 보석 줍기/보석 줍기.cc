#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;
typedef tuple<int, int, int> ti;

vector<int> jewel;
vector<vector<pi>> graph;
vector<vector<bool>> visit;
queue<ti> q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int n, m, K; cin >> n >> m >> K;

    jewel.resize(n + 1, -1);

    for (int i = 0; i < K; i++) {
        int nd; cin >> nd;

        jewel[nd] = i;
    }

    graph.resize(n + 1, vector<pi>());
    visit.resize(n + 1, vector<bool>(1 << K, false));
    for (int i = 0; i < m; i++) {
        int a, b, c; cin >> a >> b >> c;

        graph[a].emplace_back(b, c);
        graph[b].emplace_back(a, c);
    }

    q.emplace(1, 0, 0);
    visit[1][0] = true;
    int ans = 0;
    int node, mask, numJewel;
    while (!q.empty()) {
        tie(node, mask, numJewel) = q.front();
        q.pop();

        if (node == 1 && numJewel > ans) {
            ans = numJewel;
        }

        for (auto &[nextNode, limit] : graph[node]) {
            if (numJewel <= limit && !visit[nextNode][mask]) {
                q.emplace(nextNode, mask, numJewel);
                visit[nextNode][mask] = true;

                int j = jewel[nextNode];
                if (j != -1 && !(mask & 1 << j) && !visit[nextNode][mask | 1 << j]) {
                    q.emplace(nextNode, mask | 1 << j, numJewel + 1);
                    visit[nextNode][mask | 1 << j] = true;
                }
            }
        }
    }
    cout << ans;
    return 0;
}