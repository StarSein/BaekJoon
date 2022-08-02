#include <iostream>
#include <queue>
#include <tuple>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;

struct cmp {
    bool operator() (pi &a, pi &b) {
        return a.second < b.second;
    }
};

priority_queue<pi, vector<pi>, cmp> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int L, N, rf, rb; cin >> L >> N >> rf >> rb;
    for (int i = 0; i < N; i++) {
        int x, c; cin >> x >> c;
        pq.emplace(x, c);
    }

    int curX, curC, prevX = 0;
    ll ans = 0;
    while (!pq.empty()) {
        while (!pq.empty() && pq.top().first <= prevX) {
            pq.pop();
        }
        if (!pq.empty()) {
            tie(curX, curC) = pq.top();
            ans += (ll)curC * (rf - rb) * (curX - prevX);
            prevX = curX;
        }
    }
    cout << ans;
    return 0;
}