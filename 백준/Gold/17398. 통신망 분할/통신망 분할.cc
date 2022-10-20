#include <iostream>
#include <vector>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;

vector<pi> edges;
vector<int> queries;
vector<bool> check;

vector<int> roots;
vector<ll> sizes;

int findRoot(int node) {
    if (roots[node] != node) {
        roots[node] = findRoot(roots[node]);
    }
    return roots[node];
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    roots[b] = a;
    sizes[a] += sizes[b];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M, Q; cin >> N >> M >> Q;
    edges.resize(M + 1);
    for (int i = 1; i <= M; i++) {
        cin >> edges[i].first >> edges[i].second;
    }

    queries.resize(Q + 1);
    check.resize(M + 1);
    for (int i = 1; i <= Q; i++) {
        int q; cin >> q;
        queries[i] = q;
        check[q] = true;
    }

    roots.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        roots[i] = i;
    }

    sizes.resize(N + 1, 1);
    for (int i = 1; i <= M; i++) {
        if (!check[i]) {
            const auto &[a, b] = edges[i];
            int ra = findRoot(a);
            int rb = findRoot(b);
            if (ra != rb) {
                merge(ra, rb);
            }
        }
    }

    ll cost = 0;
    for (int i = Q; i > 0; i--) {
        auto &[a, b] = edges[queries[i]];
        int ra = findRoot(a);
        int rb = findRoot(b);
        if (ra != rb) {
            cost += sizes[ra] * sizes[rb];
            merge(ra, rb);
        }
    }
    cout << cost;
    return 0;
}