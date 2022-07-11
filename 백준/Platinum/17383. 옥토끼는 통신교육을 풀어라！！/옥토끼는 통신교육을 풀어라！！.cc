#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

vector<ll> times;

bool pSearch(int interval) {
    deque<ll> dq;
    for (int i = 0; i < times.size(); i++) {
        dq.push_back((times[i] % interval ? \
        (interval * (times[i] / interval) + interval) : \
        (interval * (times[i] / interval))));
    }
    if (dq.front() > interval) {
        return false;
    }
    ll curTime = 0;
    set<ll> sTime;
    sTime.insert(curTime + dq.back());
    dq.pop_back();
    while (!dq.empty()) {
        if (sTime.count(curTime + interval)) {
            sTime.insert(curTime + dq.back());
            dq.pop_back();
        } else {
            if (dq.front() > interval) {
                return false;
            } else {
                sTime.insert(curTime + dq.front());
                dq.pop_front();
            }
        }
        curTime += interval;
    }
    return true;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    times.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> times[i];
    }
    sort(times.begin(), times.end());
    int ans, left = 1, right = 1e9;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (pSearch(mid)) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    cout << ans;
}