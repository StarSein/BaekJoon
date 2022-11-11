#include <iostream>
using namespace std;


const int MAX_N = 53;
const int MOD = 10007;
int dp[MAX_N][MAX_N];

int comb(int n, int r) {
    int &ret = dp[n][r];
    if (ret) {
        return ret;
    }
    if (n == 1 || r == 0 || n == r) {
        return ret = 1;
    }
    return ret = (comb(n - 1, r) + comb(n - 1, r - 1)) % MOD;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;

    int k = N / 4;
    int ans = 0;
    for (int i = 1, j = 1; i <= k; i++, j *= -1) {
        ans += j * comb(52 - 4 * i, N - 4 * i) * comb(13, i);
    }
    ans %= MOD;
    ans = (ans + MOD) % MOD;
    cout << ans;
    return 0;
}
