#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;

priority_queue<pi, vector<pi>, greater<pi>> pq;
queue<pi> q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    int l, r;
    for (int i = 0; i < n; i++) {
        cin >> l >> r;
        pq.emplace(l, r);
    }

    auto [curL, curR] = pq.top();
    pq.pop();
    while (!pq.empty()) {
        tie(l, r) = pq.top();
        pq.pop();
        if (curR >= l) {
            curR = max(curR, r);
        } else {
            q.emplace(curL, curR);
            curL = l;
            curR = r;
        }
    }
    q.emplace(curL ,curR);

    int ableJump = 0, pos = 0;
    while (!q.empty()) {
        tie(l, r) = q.front();
        q.pop();
        if (ableJump >= l) {
            ableJump = max(ableJump, 2 * r - l);
            pos = r;
        } else {
            break;
        }
    }
    cout << pos;
}