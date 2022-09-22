#include <iostream>
#include <cstring>
#include <algorithm>
#include <iterator>
using namespace std;


const int MAX_N = 1e5 + 1;
int arr[MAX_N], seg[4 * MAX_N];
char ans[MAX_N];

void init(int node, int start, int end) {
    if (start == end) {
        seg[node] = (arr[start] > 0 ? 1 : (arr[start] < 0 ? -1 : 0));
        return;
    }
    int mid = (start + end) >> 1;
    init(node << 1, start, mid);
    init(node << 1 | 1, mid + 1, end);
    seg[node] = seg[node << 1] * seg[node << 1 | 1];
}

void update(int node, int start, int end, int target, int val) {
    if (target < start || target > end) {
        return;
    }
    if (start == target && end == target) {
        seg[node] = (val > 0 ? 1 : (val < 0 ? -1 : 0));
        return;
    }
    int mid = (start + end) >> 1;
    update(node << 1, start, mid, target, val);
    update(node << 1 | 1, mid + 1, end, target, val);
    seg[node] = seg[node << 1] * seg[node << 1 | 1];
}

int query(int node, int start, int end, int left, int right) {
    if (left > end || right < start) {
        return 1;
    }
    if (left <= start && end <= right) {
        return seg[node];
    }
    int mid = (start + end) >> 1;
    return query(node << 1, start, mid, left, right) * query(node << 1 | 1, mid + 1, end, left, right);
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, K;
    while (cin >> N >> K) {
        memset(arr, 0, sizeof(arr));
        memset(seg, 0, sizeof(seg));


        for (int i = 1; i <= N; i++) {
            cin >> arr[i];
        }

        init(1, 1, N);

        int queryCnt = 0;
        for (int q = 0; q < K; q++) {
            char cmd; cin >> cmd;
            if (cmd == 'C') {
                int i, V; cin >> i >> V;
                update(1, 1, N, i, V);
            } else {
                int i, j; cin >> i >> j;
                int res = query(1, 1, N, i, j);
                ans[queryCnt++] = (res > 0 ? '+' : (res < 0 ? '-' : '0'));
            }
        }
        copy(ans, ans + queryCnt, ostream_iterator<char>(cout, ""));
        cout << '\n';
    }
    return 0;
}