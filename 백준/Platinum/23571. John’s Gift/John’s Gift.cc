#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 1e6 + 2;
const int INF = 1e9 + 1;
int n;
int goods[MAX_N];
int prices[MAX_N];
int dp[MAX_N][4];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> goods[i];
    }
    for (int i = 1; i <= n; i++) {
        cin >> prices[i];
    }

    sort(goods + 1, goods + 1 + n);
    sort(prices + 1, prices + 1 + n);

    for (int i = 1; i <= n; i++) {
        dp[i][0] = max(
            dp[i - 1][0], 
            abs(goods[i] - prices[i])
            );
        dp[i][1] = min(
            dp[i][0], 
            max(dp[i - 1][1], abs(goods[i] - prices[i + 1]))
            );
    }
    for (int i = n; i >= 1; i--) {
        dp[i][2] = max(
            dp[i + 1][2], 
            abs(goods[i] - prices[i])
            );
        dp[i][3] = min(
            dp[i][2], 
            max(dp[i + 1][3], abs(goods[i] - prices[i - 1]))
            );
    }

    int minScore = INF;
    int ans = -1;
    for (int i = 1; i <= n; i++) {
        int curScore = min(
            max(dp[i - 1][1], dp[i + 1][2]),
            max(dp[i - 1][0], dp[i + 1][3])
            );
        if (curScore < minScore) {
            minScore = curScore;
            ans = i;
        }
    }
    cout << goods[ans];
    return 0;
}