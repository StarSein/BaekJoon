#include <iostream>
#include <cmath>
using namespace std;
typedef long long ll;

const int MAX_N = 1'000'001;
ll arr[MAX_N], seg[4*MAX_N], lazy[4*MAX_N];

void makeSegTree(int node, int start, int end) {
    if (start == end) {
        seg[node] = arr[start];
        return;
    }
    int mid = (start + end) / 2;
    makeSegTree(2*node, start, mid);
    makeSegTree(2*node+1, mid+1, end);
    seg[node] = seg[2*node] + seg[2*node+1];
}

void updateLazy(int node, int start, int end) {
    if (lazy[node]) {
        seg[node] += (static_cast<ll>(end) - start + 1) * lazy[node];
        if (start != end) {
            lazy[2*node] += lazy[node];
            lazy[2*node+1] += lazy[node];
        }
        lazy[node] = 0;
    }
}

void updateRange(int node, int start, int end, int l, int r, ll val) {
    updateLazy(node, start, end);
    
    if (start > r || end < l) {
        return;
    }
    if (l <= start && end <= r) {
        seg[node] += (static_cast<ll>(end) - start + 1) * val;
        if (start != end) {
            lazy[2*node] += val;
            lazy[2*node+1] += val;
        }
        return;
    }
    int mid = (start + end) / 2;
    updateRange(2*node, start, mid, l, r, val);
    updateRange(2*node+1, mid+1, end, l, r, val);
    seg[node] = seg[2*node] + seg[2*node+1];
}

ll query(int node, int start, int end, int l, int r) {
    updateLazy(node, start, end);

    if (start > r || end < l) {
        return 0;
    }
    if (l <= start && end <= r) {
        return seg[node];
    }
    int mid = (start + end) / 2;
    return query(2*node, start, mid, l, r) + query(2*node+1, mid+1, end, l, r);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, k; cin >> n >> m >> k;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    makeSegTree(1, 1, n);

    int a, b, c;
    ll d;
    for (int i = 0; i < m+k; i++) {
        cin >> a;
        if (a == 1) {
            cin >> b >> c >> d;
            updateRange(1, 1, n, b, c, d);
        } else {
            cin >> b >> c;
            cout << query(1, 1, n, b, c) << '\n';
        }
    }
}