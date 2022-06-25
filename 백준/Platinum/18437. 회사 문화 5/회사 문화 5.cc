#include <iostream>
#include <vector>
using namespace std;

const int MAX_N = 1e5+1;
int arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N], lb[MAX_N], ub[MAX_N];
vector<vector<int>> vChild;
int cnt = 0;

void dfs(int curr) {
    lb[curr] = ++cnt;
    for (int& next : vChild[curr]) {
        dfs(next);
    }
    ub[curr] = cnt;
}

void makeSegTree(int node, int s, int e) {
    if (s == e) {
        seg[node] = arr[s];
        return;
    }
    int mid = (s + e) >> 1;
    makeSegTree(node << 1, s, mid);
    makeSegTree(node << 1 | 1, mid+1, e);
    seg[node] = seg[node << 1] + seg[node << 1 | 1];
}

void updateLazy(int node, int s, int e) {
    if (lazy[node] != -1) {
        seg[node] = (e - s + 1) * lazy[node];
        if (s != e) {
            lazy[node << 1] = lazy[node];
            lazy[node << 1 | 1] = lazy[node];
        }
        lazy[node] = -1;
    }
}

void updateRange(int node, int s, int e, int l, int r, int v) {
    updateLazy(node, s, e);

    if (s > r || e < l) {
        return;
    }
    if (l <= s && e <= r) {
        lazy[node] = v;
        updateLazy(node, s, e);
        return;
    }
    int mid = (s + e) >> 1;
    updateRange(node << 1, s, mid, l, r, v);
    updateRange(node << 1 | 1, mid+1, e, l, r, v);
    seg[node] = seg[node << 1] + seg[node << 1 | 1];
}

int query(int node, int s, int e, int l, int r) {
    updateLazy(node, s, e);

    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return query(node << 1, s, mid, l, r) + query(node << 1 | 1, mid+1, e, l, r);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    vChild.resize(n+1, vector<int>());
    fill(arr + 1, arr + n + 1, 1);
    fill(lazy + 1, lazy + 4 * n + 1, -1);
    int parent;
    for (int i = 1; i <= n; i++) {
        cin >> parent;
        vChild[parent].push_back(i);
    }
    dfs(1);
    makeSegTree(1, 1, n);

    int m; cin >> m;
    int cmd, i;
    for (int q = 0; q < m; q++) {
        cin >> cmd >> i;
        if (cmd == 1) {
            if (lb[i] != ub[i]) {
                updateRange(1, 1, n, lb[i]+1, ub[i], 1);
            }
        } else if (cmd == 2) {
            if (lb[i] != ub[i]) {
                updateRange(1, 1, n, lb[i]+1, ub[i], 0);
            }
        } else {
            if (lb[i] != ub[i]) {
                cout << query(1, 1, n, lb[i]+1, ub[i]) << '\n';
            } else {
                cout << 0 << '\n';
            }
        }
    }
}