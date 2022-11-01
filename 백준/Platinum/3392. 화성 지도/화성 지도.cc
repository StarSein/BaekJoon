#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Segment {
    bool start;
    int x;
    int y1;
    int y2;

    Segment() = default;
    Segment(bool s, int x, int y1, int y2): start(s), x(x), y1(y1), y2(y2) {};
};

const int SZ = 3e4;
int cnt[4 * SZ];
int seg[4 * SZ];
vector<Segment> segVec;

void updateRange(int node, int start, int end, int left, int right, int val) {
    if (start > right || end < left) {
        return;
    }
    if (left <= start && end <= right) {
        cnt[node] += val;
    } else {
        int mid = (start + end) >> 1;
        updateRange(node << 1, start, mid, left, right, val);
        updateRange(node << 1 | 1, mid + 1, end, left, right, val);
    }

    if (cnt[node]) {
        seg[node] = end - start + 1;
    } else {
        seg[node] = (start == end ? 0 : seg[node << 1] + seg[node << 1 | 1]);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    segVec.reserve(2 * N);
    for (int i = 0; i < N; i++) {
        int x1, y1, x2, y2; cin >> x1 >> y1 >> x2 >> y2;
        segVec.emplace_back(true, x1, y1, y2);
        segVec.emplace_back(false, x2, y1, y2);
    }

    sort(segVec.begin(), segVec.end(), [](Segment &a, Segment &b) {return a.x < b.x;});

    int prevX = -1;
    int ans = 0;
    for (int i = 0; i < segVec.size(); i++) {
        ans += (segVec[i].x - prevX) * seg[1];

        prevX = segVec[i].x;
        int v = segVec[i].start ? 1 : -1;
        updateRange(1, 0, SZ - 1, segVec[i].y1, segVec[i].y2 - 1, v);
    }
    cout << ans;
    return 0;
}