#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

const int N = 11;
vector<int> vTime;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int ans = 0;
    for (int i = 0; i < N; i++) {
        int D, V; cin >> D >> V;

        vTime.push_back(D);
        ans += 20 * V;
    }
    sort(vTime.begin(), vTime.end(), less<int>());
    partial_sum(vTime.begin(), vTime.end(), vTime.begin(), [](int &a, int &b) {return a + b;});
    for (int &pSum : vTime) {
        ans += pSum;
    }
    cout << ans;
}