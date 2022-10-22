#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;
typedef pair<int, int> pi;


const int MAX_N = 2e5 + 1;
ll depth[MAX_N];
pi sub[MAX_N];
int seg[4 * MAX_N];
int cnt = 0;

vector<vector<int>> graph;

void EulerRoute(int cur, int par) {
    depth[cur] = depth[par] + 1;
    sub[cur].first = ++cnt;
    for (int &child : graph[cur]) {
        if (child != par) {
            EulerRoute(child, cur);
        }
    }
    sub[cur].second = cnt;
}

void update(int node, int start, int end, int target) {
    if (start > target || end < target) {
        return;
    }
    if (start == target && end == target) {
        seg[node]++;
        return;
    }
    int mid = (start + end) >> 1;
    update(node << 1, start, mid, target);
    update(node << 1 | 1, mid + 1, end, target);
    seg[node] = seg[node << 1] + seg[node << 1 | 1];
}

int query(int node, int start, int end, int left, int right) {
    if (right < start || left > end) {
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

    int N, C; cin >> N >> C;
    graph.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int x, y; cin >> x >> y;
        graph[x].push_back(y);
        graph[y].push_back(x);
    }

    EulerRoute(C, 0);
    int Q; cin >> Q;
    for (int i = 0; i < Q; i++) {
        int q, A; cin >> q >> A;
        if (q == 1) {
            update(1, 1, N, sub[A].first);
        } else {
            cout << depth[A] * query(1, 1, N, sub[A].first, sub[A].second) << '\n';
        }
    }
    return 0;
}