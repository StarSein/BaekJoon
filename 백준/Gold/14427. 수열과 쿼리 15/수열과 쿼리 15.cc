#include <iostream>
using namespace std;
typedef pair<int, int> pi;

const int MAX_N = 1e5 + 1;
int arr[MAX_N];
pi seg[4 * MAX_N];

void initSeg(int node, int start, int end) {
    if (start == end) {
        seg[node] = {start, arr[start]};
        return;
    }
    int mid = (start + end) >> 1;
    initSeg(node << 1, start, mid);
    initSeg(node << 1 | 1, mid + 1, end);
    if (seg[node << 1].second <= seg[node << 1 | 1].second) {
        seg[node] = seg[node << 1];
    } else {
        seg[node] = seg[node << 1 | 1];
    }
}

void update(int node, int start, int end, int i, int v) {
    if (start > i || end < i) {
        return;
    }
    if (start == i && end == i) {
        seg[node].second = v;
        return;
    }
    int mid = (start + end) >> 1;
    update(node << 1, start, mid, i, v);
    update(node << 1 | 1, mid + 1, end, i, v);
    if (seg[node << 1].second <= seg[node << 1 | 1].second) {
        seg[node] = seg[node << 1];
    } else {
        seg[node] = seg[node << 1 | 1];
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
    }

    initSeg(1, 1, N);

    int M; cin >> M;
    for (int i = 0; i < M; i++) {
        int q; cin >> q;
        if (q == 1) {
            int i, v; cin >> i >> v;
            update(1, 1, N, i, v);
        } else {
            cout << seg[1].first << '\n';
        }
    }
    return 0;
}