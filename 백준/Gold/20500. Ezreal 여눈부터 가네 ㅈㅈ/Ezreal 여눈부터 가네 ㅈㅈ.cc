#include <iostream>
#include <cstring>
using namespace std;

const int MAX_N = 1515;
const int MOD = 1e9 + 7;

int dp[MAX_N + 1][3];

int getDP(int n, int m) {
    int &ret = dp[n][m];
    if (ret != -1) {
        return ret;
    }

    return ret = (getDP(n - 1, (m + 2) % 3) + getDP(n - 1, (m + 1) % 3)) % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    memset(dp, -1, sizeof(dp));

    int N; cin >> N;

    dp[1][0] = 0;
    dp[1][1] = 0;
    dp[1][2] = 1;

    cout << getDP(N, 0);
    return 0;
}