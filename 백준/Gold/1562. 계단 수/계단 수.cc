#include <iostream>
#include <cstring>
using namespace std;
#define MOD static_cast<int>(1e9)
#define NUM_DIGIT 10
#define MAX_N 100
typedef long long ll;

ll dp[NUM_DIGIT][MAX_N + 1][1 << NUM_DIGIT];

ll getNumStair(int digit, int length, int mask) {
    if (digit == -1 || digit == 10 || length == 0 || !(mask & 1 << digit)) {
        return 0;
    }

    ll &ret = dp[digit][length][mask];
    if (ret != -1) {
        return ret;
    }
    ret = getNumStair(digit - 1, length - 1, mask)
            + getNumStair(digit - 1, length - 1, mask ^ 1 << digit)
            + getNumStair(digit + 1, length - 1, mask)
            + getNumStair(digit + 1, length - 1, mask ^ 1 << digit);
    ret %= MOD;
    return ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    memset(dp, -1, sizeof(dp));
    dp[0][1][1 << 0] = 0;
    for (int i = 1; i < NUM_DIGIT; i++) {
        dp[i][1][1 << i] = 1;
    }

    int N; cin >> N;
    ll ans = 0;
    for (int i = 0; i < NUM_DIGIT; i++) {
        ans += getNumStair(i, N, (1 << NUM_DIGIT) - 1);
    }
    ans %= MOD;
    cout << ans;
    return 0;
}