#include <iostream>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 1e5 + 1, INF = 1e9 + 1;
int arr[MAX_N], minSeg[4 * MAX_N], maxSeg[4 * MAX_N], diffSeg[4 * MAX_N];
int maxDiff = 0;


void init(int node, int start, int end) {
    if (start == end) {
        minSeg[node] = arr[start];
        maxSeg[node] = arr[start];
        return;
    }
    int mid = (start + end) >> 1;
    init(node << 1, start, mid);
    init(node << 1 | 1, mid + 1, end);
    minSeg[node] = min(minSeg[node << 1], minSeg[node << 1 | 1]);
    maxSeg[node] = max(maxSeg[node << 1], maxSeg[node << 1 | 1]);
    diffSeg[node] = max({diffSeg[node << 1], diffSeg[node << 1 | 1], maxSeg[node << 1 | 1] - minSeg[node << 1]});
}

void update(int node, int start, int end, int target, int val) {
    if (start > target || end < target) {
        return;
    }
    if (start == target && end == target) {
        minSeg[node] = val;
        maxSeg[node] = val;
        return;
    }
    int mid = (start + end) >> 1;
    update(node << 1, start, mid, target, val);
    update(node << 1 | 1, mid + 1, end, target, val);
    minSeg[node] = min(minSeg[node << 1], minSeg[node << 1 | 1]);
    maxSeg[node] = max(maxSeg[node << 1], maxSeg[node << 1 | 1]);
    diffSeg[node] = max({diffSeg[node << 1], diffSeg[node << 1 | 1], maxSeg[node << 1 | 1] - minSeg[node << 1]});
}

pi query(int node, int start, int end, int left, int right) {
    if (start > right || end < left) {
        return {INF, -INF};
    }
    if (left <= start && end <= right) {
        maxDiff = max(maxDiff, diffSeg[node]);
        return {minSeg[node], maxSeg[node]};
    }
    int mid = (start + end) >> 1;
    const auto [leftMin, leftMax] = query(node << 1, start, mid, left, right);
    const auto [rightMin, rightMax] = query(node << 1 | 1, mid + 1, end, left, right);
    maxDiff = max(maxDiff, rightMax - leftMin);
    return {min(leftMin, rightMin), max(leftMax, rightMax)};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    init(1, 1, N);
    int Q; cin >> Q;
    for (int i = 0; i < Q; i++) {
        int cmd; cin >> cmd;
        if (cmd == 1) {
            int k, x; cin >> k >> x;
            update(1, 1, N, k, x);
        } else {
            int l, r; cin >> l >> r;
            query(1, 1, N, l, r);
            cout << maxDiff << '\n';
            maxDiff = 0;
        }
    }
    return 0;
}