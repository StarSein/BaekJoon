#include <iostream>
#include <vector>
#include <numeric>
#include <map>
#include <algorithm>
#include <iterator>
using namespace std;
typedef long long ll;

vector<ll> vec;
map<ll, vector<int>> pos;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k; cin >> n >> k;
    vec.resize(n+1);
    vec[0] = 0;
    for (int i = 1; i <= n; i++) {
        cin >> vec[i];
    }
    partial_sum(vec.begin(), vec.end(), vec.begin(), [](ll& a, ll& b)->ll{return a + b;});
    for (int i = 0; i <= n; i++) {
        ll pSum = vec[i];
        if (pos.count(pSum)) {
            pos[pSum].push_back(i);
        } else {
            pos[pSum] = {i};
        }
    }
    ll ans = 0;
    for (int i = 1; i <= n; i++) {
        ll pSum = vec[i];
        ll qSum = pSum - k;
        if (pos.count(qSum)) {
            ans += lower_bound(pos[qSum].begin(), pos[qSum].end(), i) - pos[qSum].begin();
        }
    }
    cout << ans;
}