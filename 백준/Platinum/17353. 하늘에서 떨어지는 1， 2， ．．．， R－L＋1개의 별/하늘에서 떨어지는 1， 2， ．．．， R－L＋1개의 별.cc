#include <iostream>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5+1;
ll arr[MAX_N], segtree[4*MAX_N], lazy[4*MAX_N];

void makeSegTree(int node, int s, int e) {
    if (s == e) {
        segtree[node] = arr[s] - arr[s-1];
        return;
    }
    int mid = (s + e) >> 1;
    makeSegTree(2*node, s, mid);
    makeSegTree(2*node+1, mid+1, e);
    segtree[node] = segtree[2*node] + segtree[2*node+1];
}

void updateLazy(int node, int s, int e) {
    if (lazy[node]) {
        segtree[node] += (e - s + 1) * lazy[node];
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
    segtree[node] = segtree[2*node] + segtree[2*node+1];
}

ll query(int node, int s, int e, int l, int r) {
    updateLazy(node, s, e);

    if (s > r || e < l) {
        return 0;
    }
    if (l <= s && e <= r) {
        return segtree[node];
    }
    int mid = (s + e) >> 1;
    return query(2*node, s, mid, l, r) + query(2*node+1, mid+1, e, l, r);
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

    int q; cin >> q;
    int type, l, r, x;
    for (int i = 0; i < q; i++) {
        cin >> type;
        if (type == 1) {
            cin >> l >> r;
            updateRange(1, 1, n, l, r, 1);
            updateRange(1, 1, n, r+1, r+1, -(r-l+1));
        } else {
            cin >> x;
            cout << query(1, 1, n, 1, x) << '\n';
        }
    }
}