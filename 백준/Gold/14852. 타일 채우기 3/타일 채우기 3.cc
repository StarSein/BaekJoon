#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

const int MOD = 1e9+7;
vector<vector<int>> dp;


int main() {
    int n; cin >> n;
    dp.resize(n+1, vector<int>(2, 0));
    dp[0][0] = 1; dp[1][0] = 2; dp[1][1] = 2;
    for (int i = 2; i <= n; i++) {
        dp[i][0] = ((ll)dp[i-1][0] * 2 + dp[i-1][1] + dp[i-2][0]) % MOD;
        dp[i][1] = ((ll)dp[i-1][0] * 2 + dp[i-1][1]) % MOD;
    }
    cout << dp[n][0];
}