#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;

const int SZ = 2001;
int m, n, w, h;
int wi[SZ];
int dj[SZ];

struct cmp {
    bool operator() (const int &a, const int &b) {
        return wi[a] < wi[b];
    }
};

priority_queue<int, vector<int>, cmp> waiting;
queue<pi> working;
queue<pi> healing;
vector<int> ans[SZ];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> m >> n >> w >> h;
    for (int i = 0; i < m; i++) {
        cin >> wi[i];
        if (wi[i]) {
            waiting.push(i);
        }
    }
    for (int j = 0; j < n; j++) {
        cin >> dj[j];
    }
    for (int t = 1; t <= n; t++) {
        while (!working.empty() && working.front().first == t) {
            int cur = working.front().second;
            working.pop();
            wi[cur] -= w;
            if (wi[cur]) {
                healing.emplace(t + h, cur);
            }
        }
        dj[t - 1] -= working.size();
        while (!healing.empty() && healing.front().first == t) {
            int cur = healing.front().second;
            healing.pop();
            waiting.push(cur);
        }
        for (int i = 0; i < dj[t - 1]; i++) {
            if (waiting.empty()) {
                cout << -1;
                return 0;
            }
            int cur = waiting.top();
            waiting.pop();
            working.emplace(t + w, cur);
            ans[cur].push_back(t);
        }
    }
    cout << 1 << '\n';
    for (int i = 0; i < m; i++) {
        copy(ans[i].begin(), ans[i].end(), ostream_iterator<int>(cout, " "));
        cout << '\n';
    }
    return 0;
}