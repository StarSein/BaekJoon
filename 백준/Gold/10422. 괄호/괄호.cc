#include <iostream>
#include <cstring>
using namespace std;
typedef long long ll;

const int MAX_L = 5000, INIT = -1, MOD = 1e9 + 7;
int dp[MAX_L + 1][MAX_L + 1];


int getDP(int n, int k) {
    int &ret = dp[n][k];
    if (ret != INIT) {
        return ret;
    }
    if (n == 1 || k == 0 || k == n) {
        return ret = 1;
    }
    return ret = (getDP(n-1, k) + getDP(n-1, k-1)) % MOD;
}

int getAns(int l) {
    ll ret = 0;
    if (~l & 1) {
        int k = l / 2;
        ret = getDP(l - 1, k);
        for (int i = l - 2; i >= k; i--) {
            ret -= getDP(i, k);
        }
        ret %= MOD;
        if (ret < 0) {
            ret += MOD;
        }
    }
    return (int)ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    memset(dp, -1, sizeof(dp));

    int T; cin >> T;
    for (int i = 0; i < T; i++) {
        int L; cin >> L;
        cout << getAns(L) << '\n';
    }
    return 0;
}