#include <iostream>
#include <vector>
#include <cmath>
#include <queue>
#include <iomanip>
using namespace std;
#define X first
#define Y second
typedef long long ll;
typedef pair<ll, ll> pll;
typedef pair<double, int> pdi;

int n, m;
vector<pll> pos;
vector<vector<double>> graph;
vector<bool> visited;
priority_queue<pdi, vector<pdi>, greater<pdi>> pq;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    pos.resize(n + 1, pll());
    graph.resize(n + 1, vector<double>(n + 1, 0));
    visited.resize(n + 1, false);
    ll x, y;
    for (int i = 1; i <= n; i++) {
        cin >> x >> y;
        pos[i] = {x, y};
    }

    double dist = 0;
    ll dx = 0, dy = 0;
    for (int i = 2; i <= n; i++) {
        for (int j = 1; j < i; j++) {
            dx = pos[i].X - pos[j].X;
            dy = pos[i].Y - pos[j].Y;
            dist = sqrt(dx * dx + dy * dy);
            graph[i][j] = dist;
            graph[j][i] = dist;
        }
    }

    int nodeA, nodeB;
    for (int i = 0; i < m; i++) {
        cin >> nodeA >> nodeB;
        graph[nodeA][nodeB] = 0;
        graph[nodeB][nodeA] = 0;
    }

    visited[1] = true;
    for (int i = 2; i <= n; i++)
        pq.push({graph[i][1], i});
    
    double lenMST = 0;
    pdi cur;
    while (!pq.empty()) {
        cur = pq.top();
        pq.pop();
        if (!visited[cur.Y]) {
            visited[cur.Y] = true;
            lenMST += cur.X;
            for (int i = 1; i <= n; i++) {
                pq.push({graph[cur.Y][i], i});
            }
        }
    }
    cout << fixed;
    cout.precision(2);
    cout << round(lenMST * 100) / 100;
}