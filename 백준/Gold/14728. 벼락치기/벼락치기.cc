#include <iostream>
#include <vector>
using namespace std;

vector<int> dp;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, t; cin >> n >> t;
    dp.resize(t + 1);
    int k, s;
    for (int i = 0; i < n; i++) {
        cin >> k >> s;
        for (int j = t; j >= k; j--)
            dp[j] = max(dp[j], dp[j-k] + s);
    }
    cout << dp[t];
}