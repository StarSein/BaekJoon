#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

const int MAX_N = 2e5+1, ROOT = 1, MOD = 1e9+7;
int tmp[MAX_N], arr[MAX_N], lb[MAX_N], ub[MAX_N];
vector<int> seg[4*MAX_N];
vector<vector<int>> graph;
int cnt = 0;

ll answer = 0;


void dfs(int cur, int par) {
    lb[cur] = ++cnt;
    for (int& adj : graph[cur]) {
        if (adj != par) {
            dfs(adj, cur);
        }
    }
    ub[cur] = cnt;
}

void init(int node, int s, int e) {
    if (s == e) {
        seg[node].push_back(arr[s]);
        return;
    }
    int mid = (s + e) >> 1;
    init(node<<1, s, mid);
    init(node<<1|1, mid+1, e);
    seg[node].resize(seg[node<<1].size() + seg[node<<1|1].size());
    merge(seg[node<<1].begin(), seg[node<<1].end(), seg[node<<1|1].begin(), seg[node<<1|1].end(), seg[node].begin());
}

int query(int node, int s, int e, int l, int r, int v) {
    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return upper_bound(seg[node].begin(), seg[node].end(), v) - seg[node].begin();
    }
    int mid = (s + e) >> 1;
    return query(node<<1, s, mid, l, r, v) + query(node<<1|1, mid+1, e, l, r, v);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, c; cin >> n >> m >> c;
    for (int i = 1; i <= n; i++) {
        cin >> tmp[i];
    }

    graph.resize(n+1, vector<int>());
    int a, b;
    for (int i = 1; i < n; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    dfs(ROOT, 0);
    for (int i = 1; i <= n; i++) {
        arr[lb[i]] = tmp[i];
    }
    init(1, 1, n);
    for (int i = 0; i < m; i++) {
        cin >> a >> b;
        answer += query(1, 1, n, lb[a], ub[a], b);
    }
    cout << answer % MOD;
}