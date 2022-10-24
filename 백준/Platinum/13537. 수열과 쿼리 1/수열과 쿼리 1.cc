#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int SZ = 1e5 + 1;
int A[SZ];
vector<int> tree[4 * SZ];

void init(int node, int start, int end) {
    if (start == end) {
        tree[node].push_back(A[start]);
        return;
    }
    int mid = (start + end) >> 1;
    init(node << 1, start, mid);
    init(node << 1 | 1, mid + 1, end);
    tree[node].resize(tree[node << 1].size() + tree[node << 1 | 1].size());
    merge(tree[node << 1].begin(), tree[node << 1].end(), tree[node << 1 | 1].begin(), tree[node << 1 | 1].end(), tree[node].begin());
}

int query(int node, int start, int end, int left, int right, int k) {
    if (start > right || end < left) {
        return 0;
    }
    if (left <= start && end <= right) {
        return tree[node].end() - upper_bound(tree[node].begin(), tree[node].end(), k);
    }
    int mid = (start + end) >> 1;
    return query(node << 1, start, mid, left, right, k) + query(node << 1 | 1, mid + 1, end, left, right, k);
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }

    init(1, 1, N);

    int M; cin >> M;
    for (int q = 0; q < M; q++) {
        int i, j, k; cin >> i >> j >> k;
        cout << query(1, 1, N, i, j, k) << '\n';
    }
    return 0;
}