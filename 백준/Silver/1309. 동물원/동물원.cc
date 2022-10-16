#include <iostream>
using namespace std;


const int MAX_N = 1e5 + 5;
const int MOD = 9901;
int dp[MAX_N][3];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    dp[0][0] = dp[0][1] = dp[0][2] = 1;
    for (int i = 0; i < N - 1; i++) {
        dp[i + 1][0] = (dp[i][0] + dp[i][1] + dp[i][2]) % MOD;
        dp[i + 1][1] = (dp[i][2] + dp[i][0]) % MOD;
        dp[i + 1][2] = (dp[i][1] + dp[i][0]) % MOD;
    }
    cout << (dp[N - 1][0] + dp[N - 1][1] + dp[N - 1][2]) % MOD;
    return 0;
}