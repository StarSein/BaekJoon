#include <iostream>
using namespace std;

const int MAX_N = 100'001;

int arr[MAX_N], seg[4*MAX_N];
bool lazy[4*MAX_N];

void updateLazy(int node, int start, int end) {
    if (lazy[node]) {
        seg[node] = (end - start + 1) - seg[node];
        if (start != end) {
            lazy[2*node] = !lazy[2*node];
            lazy[2*node+1] = !lazy[2*node+1];
        }
        lazy[node] = !lazy[node];
    }
}

void updateRange(int node, int start, int end, int l, int r) {
    updateLazy(node, start, end);

    if (start > r || end < l) {
        return;
    }
    if (l <= start && end <= r) {
        seg[node] = (end - start + 1) - seg[node];
        if (start != end) {
            lazy[2*node] = !lazy[2*node];
            lazy[2*node+1] = !lazy[2*node+1];
        }
        return;
    }
    int mid = (start + end) / 2;
    updateRange(2*node, start, mid, l, r);
    updateRange(2*node+1, mid+1, end, l, r);
    seg[node] = seg[2*node] + seg[2*node+1];
}

int query(int node, int start, int end, int l, int r) {
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

    int n, m; cin >> n >> m;

    int o, s, t;
    for (int i = 0; i < m; i++) {
        cin >> o >> s >> t;
        if (o == 0) {
            updateRange(1, 1, n, s, t);
        } else {
            cout << query(1, 1, n, s, t) << '\n';
        }
    }
}