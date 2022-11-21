#include <iostream>
using namespace std;
#define SZ 101
#define INF 1e9
int a, b, c;
int dp[SZ][SZ][SZ][2];

int getDP(int i, int j, int k, int turn) {
    int &ret = dp[i][j][k][turn % 2];
    if (ret == INF) {
        int val = -INF;
        if (i) {
            int ni = max(0, i - turn);
            int gain = min(i, turn);
            val = max(val, gain - getDP(ni, j, k, turn + 1));
        }
        if (j) {
            int nj = max(0, j - turn);
            int gain = min(j, turn);
            val = max(val, gain - getDP(i, nj, k, turn + 1));
        }
        if (k) {
            int nk = max(0, k - turn);
            int gain = min(k, turn);
            val = max(val, gain - getDP(i, j, nk, turn + 1));
        }
        ret = (val == -INF ? 0 : val);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fill(&dp[0][0][0][0], &dp[0][0][0][0] + SZ * SZ * SZ * 2, INF);

    cin >> a >> b >> c;
    int res = getDP(a, b, c, 1);
    if (res > 0) {
        cout << 'F';
    } else if (res == 0) {
        cout << 'D';
    } else {
        cout << 'S';
    }
    return 0;
}