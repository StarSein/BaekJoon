#include <iostream>
#include <vector>
#include <tuple>
#include <cstring>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;
typedef tuple<ll, ll, ll> tll;

vector<tll> vEdge;
vector<vector<pll>> graph;

const int MAX_N = 2e3;
bool visited[MAX_N];

pll getMaxLength(int curNode, int prevNode) {
    visited[curNode] = true;
    pll ret = {curNode, 0};
    for (auto &[nextNode, cost] : graph[curNode]) {
        if (nextNode != prevNode) {
            auto [leaf, length] = getMaxLength(nextNode, curNode);
            length += cost;
            if (length >= ret.second) {
                ret.first = leaf;
                ret.second = length;
            }
        }
    }
    return ret;
}

ll getMaxDiameter(int node) {
    auto [oneLeaf, oneDiameter] = getMaxLength(node, -1);
    auto [anotherLeaf, ret] = getMaxLength(oneLeaf, -1);
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int N; cin >> N;
    vEdge.reserve(N-1);
    graph.resize(N);
    for (int i = 1; i < N; i++) {
        ll from, to, cost; cin >> from >> to >> cost;

        vEdge.emplace_back(from, to, cost);
    }

    ll ans = 0;
    for (int i = 0; i < vEdge.size(); i++) {
        for (int j = 0; j < N; j++) {
            graph[j].clear();
        }
        memset(visited, 0, sizeof(visited));

        auto &[erasedFrom, erasedTo, erasedCost] = vEdge[i];
        for (int j = 0; j < i; j++) {
            auto &[from, to, cost] = vEdge[j];

            graph[from].emplace_back(to, cost);
            graph[to].emplace_back(from, cost);
        }
        for (int j = i + 1; j < vEdge.size(); j++) {
            auto &[from, to, cost] = vEdge[j];

            graph[from].emplace_back(to, cost);
            graph[to].emplace_back(from, cost);
        }
        ll curDiameter = erasedCost;
        for (int node = 0; node < N; node++) {
            if (!visited[node]) {
                curDiameter += getMaxDiameter(node);
            }
        }
        ans = max(ans, curDiameter);
    }
    cout << ans;
    return 0;
}