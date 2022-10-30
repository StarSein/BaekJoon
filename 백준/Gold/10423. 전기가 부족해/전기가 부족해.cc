#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
struct Edge {
    int u, v, w;
};

vector<int> roots;
vector<Edge> edges;

int findRoot(int x) {
    if (roots[x] != x) {
        roots[x] = findRoot(roots[x]);
    }
    return roots[x];
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    roots[b] = a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, M, K; cin >> N >> M >> K;
    roots.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        roots[i] = i;
    }
    for (int i = 0; i < K; i++) {
        int supply; cin >> supply;
        roots[supply] = 0;
    }
    edges.resize(M);
    for (Edge &e: edges) {
        cin >> e.u >> e.v >> e.w;
    }
    sort(edges.begin(), edges.end(), [](Edge &a, Edge &b) {return a.w < b.w;});

    int ans = 0;
    for (Edge &cur: edges) {
        int ru = findRoot(cur.u);
        int rv = findRoot(cur.v);
        if (ru != rv) {
            merge(ru, rv);
            ans += cur.w;
        }
    }
    cout << ans;
    return 0;
}