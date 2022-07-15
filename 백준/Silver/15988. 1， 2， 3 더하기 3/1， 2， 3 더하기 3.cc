/*

1   1 (1)

2   1+1 (2)
    2


3   1+1+1 (4)
    2+1

    1+2

    3

4   1+1+1+1 (7)
    2+1+1
    1+2+1
    3+1

    1+1+2
    2+2

    1+3

5   1+1+1+1+1 (13)
    2+1+1+1
    1+2+1+1
    1+1+2+1
    2+2+1
    3+1+1
    1+3+1

    1+1+1+2
    2+1+2
    1+2+2
    3+2

    1+1+3
    2+3

6   13 + 7 + 3 + 1 = 24
7   24 + 13 + 7 = 44
*/
#include <iostream>
using namespace std;
typedef long long ll;

const int MAX_N = 1e6+1, MOD = 1e9+9;
ll dp[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for (int i = 4; i < MAX_N; i++) {
        dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
    }

    int t; cin >> t;
    while (t--) {
        int n; cin >> n;

        cout << dp[n] << '\n';
    }
    return 0;
}