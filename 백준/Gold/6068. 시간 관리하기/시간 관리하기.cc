#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;
vector<pi> vTask;


bool is_able(int initTime) {
    int i = 0, curTime = initTime;
    while (i < vTask.size() && curTime <= vTask[i].first - vTask[i].second) {
        curTime += vTask[i].second;
        i++;
    }
    return i == vTask.size();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, t, s;
    cin >> n;
    vTask.reserve(n);
    for (int i = 0; i < n; i++) {
        cin >> t >> s;
        vTask.push_back(make_pair(s, t));
    }
    sort(vTask.begin(), vTask.end());
    const int MAX_TIME = 1e6;
    int mid, lp = 0, rp = MAX_TIME - 1, answer = -1;
    while (lp <= rp) {
        mid = lp + (rp - lp) / 2;
        if (is_able(mid)) {
            answer = mid;
            lp = mid + 1;
        }
        else {
            rp = mid - 1;
        }
    }
    cout << answer;
}