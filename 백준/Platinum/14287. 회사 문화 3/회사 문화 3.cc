#include <iostream>
#include <vector>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 1e5 + 1;
pi interval[MAX_N];
int seg[4 * MAX_N];

const int ROOT = 1;
vector<vector<int>> childs;
int cnt = 0;

void EulerTour(int node) {
    interval[node].first = ++cnt;
    for (int &child : childs[node]) {
        EulerTour(child);
    }
    interval[node].second = cnt;
}

void update(int node, int start, int end, int target, int val) {
    if (end < target || start > target) {
        return;
    }
    if (start == target && end == target) {
        seg[node] += val;
        return;
    }
    int mid = (start + end) >> 1;
    update(node << 1, start, mid, target, val);
    update(node << 1 | 1, mid + 1, end, target, val);
    seg[node] = seg[node << 1] + seg[node << 1 | 1];
}

int query(int node, int start, int end, int left, int right) {
    if (left > end || right < start) {
        return 0;
    }
    if (left <= start && end <= right) {
        return seg[node];
    }
    int mid = (start + end) >> 1;
    return query(node << 1, start, mid, left, right) + query(node << 1 | 1, mid + 1, end, left, right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m; cin >> n >> m;
    childs.resize(n + 1);
    int dummy; cin >> dummy;
    for (int i = 2; i <= n; i++) {
        int parent; cin >> parent;
        childs[parent].push_back(i);
    }

    EulerTour(ROOT);

    for (int i = 0; i < m; i++) {
        int q, node, w;
        cin >> q >> node;
        if (q == 1) {
            cin >> w;
            update(1, 1, n, interval[node].first, w);
        } else {
            cout << query(1, 1, n, interval[node].first, interval[node].second) << '\n';
        }
    }
    return 0;
}