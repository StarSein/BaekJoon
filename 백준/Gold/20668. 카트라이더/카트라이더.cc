#include <iostream>
#include <vector>
#include <tuple>
#include <queue>
using namespace std;
typedef long long ll;
typedef tuple<ll, ll, ll> tll;

const int DIV = 1*2*3*4*5*6*7*8*9*10;
const int MAX_N = 1e4+1, MAX_K = 11;
int n, m;
vector<vector<tll>> graph;
priority_queue<tll, vector<tll>, greater<tll>> pq;
bool visit[MAX_N][MAX_K];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    graph.resize(n+1, vector<tll>());
    ll a, b, l, k;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> l >> k;
        graph[a].emplace_back(b, l * DIV, k);
        graph[b].emplace_back(a, l * DIV, k);
    }

    pq.emplace(0, 1, 1);
    ll cost, node, velocity;
    while (!pq.empty()) {
        tie(cost, node, velocity) = pq.top();
        if (node == n) {
            cout << fixed;
            cout.precision(9);
            cout << (long double)cost / DIV;
            return 0;
        }
        pq.pop();
        if (visit[node][velocity]) {
            continue;
        }
        visit[node][velocity] = true;
        for (auto& [next, length, limit] : graph[node]) {
            for (int nv = velocity - 1; nv <= velocity + 1; nv++) {
                if (1 <= nv && nv <= limit) {
                    pq.emplace(cost + length / nv, next, nv);
                }
            }
        }
    }
}