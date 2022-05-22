#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;
vector<pi> vec;
priority_queue<int, vector<int>, less<int>> pq;
int main() {
    int n; cin >> n;
    vec.reserve(n);
    int p, d;
    for (int i = 0; i < n; i++) {
        cin >> p >> d;
        vec.push_back({p, d});
    }
    sort(vec.begin(), vec.end(), [](auto& a, auto& b) {return a.second > b.second;});

    int i = 0, time = 10'000, earn = 0;
    while (time) {
        while (i < vec.size() && vec[i].second == time) {
            pq.push(vec[i].first);
            i++;
        }
        if (!pq.empty()) {
            earn += pq.top();
            pq.pop();
        }
        time--;
    }
    cout << earn;
}