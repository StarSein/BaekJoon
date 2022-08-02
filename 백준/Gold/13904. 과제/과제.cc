#include <iostream>
#include <queue>
using namespace std;
typedef pair<int, int> pi;

struct cmp {
    bool operator() (pi &a, pi &b) {
        return a.first < b.first;
    }
};

priority_queue<pi, vector<pi>, cmp> pqPi;
priority_queue<int, vector<int>, less<int>> pqInt;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        int d, w; cin >> d >> w;
        pqPi.emplace(d, w);
    }

    int ans = 0;
    while (N) {
        while (pqPi.size() && pqPi.top().first >= N) {
            pqInt.push(pqPi.top().second);
            pqPi.pop();
        }
        if (!pqInt.empty()) {
            ans += pqInt.top();
            pqInt.pop();
        }
        N--;
    }
    cout << ans;
}