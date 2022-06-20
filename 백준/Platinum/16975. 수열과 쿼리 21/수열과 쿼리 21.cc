#include <iostream>
typedef long long ll;
using namespace std;

const int MAX_N = 1e5+1;
ll arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N];

void makeSegTree(int node, int s, int e) {
    if (s == e) {
        seg[node] = arr[s];
        return;
    }
    int mid = (s + e) >> 1;
    makeSegTree(2*node, s, mid);
    makeSegTree(2*node+1, mid+1, e);
    seg[node] = seg[2*node] + seg[2*node+1];
}

void updateLazy(int node, int s, int e) {
    if (lazy[node]) {
        seg[node] += (e - s + 1) * lazy[node];
        if (s != e) {
            lazy[2*node] += lazy[node];
            lazy[2*node+1] += lazy[node];
        }
        lazy[node] = 0;
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
    updateRange(2*node, s, mid, l, r, v);
    updateRange(2*node+1, mid+1, e, l, r, v);
    seg[node] = seg[2*node] + seg[2*node+1];
}

ll query(int node, int s, int e, int x) {
    updateLazy(node, s, e);
    if (s > x || e < x) {
        return 0;
    }
    if (x == s && e == x) {
        return seg[node];
    }
    int mid = (s + e) >> 1;
    return query(2*node, s, mid, x) + query(2*node+1, mid+1, e, x);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    makeSegTree(1, 1, n);
    int m; cin >> m;
    int type, i, j, k, x;
    for (int q = 0; q < m; q++) {
        cin >> type;
        if (type == 1) {
            cin >> i >> j >> k;
            updateRange(1, 1, n, i, j, k);
        } else {
            cin >> x;
            cout << query(1, 1, n, x) << '\n';
        }
    }
}