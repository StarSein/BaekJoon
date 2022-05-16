#include <iostream>
#include <vector>
using namespace std;

const int MAX_K = 500;
vector<int> sizes(MAX_K, 0);
vector<int> prefSum(MAX_K + 1, 0);
vector<vector<int>> dp(MAX_K, vector<int>(MAX_K, 0));

void calcDP(int l, int r) {
    int minCost = dp[l][l] + dp[l+1][r];
    for (int k = l + 1; k < r; k++)
        minCost = min(minCost, dp[l][k] + dp[k+1][r]);
    minCost += prefSum[r+1] - prefSum[l];
    dp[l][r] = minCost;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t; cin >> t;
    for (int tc = 0; tc < t; tc++) {
        int k; cin >> k;
        for (int i = 0; i < k; i++) {
            cin >> sizes[i];
            prefSum[i+1] = prefSum[i] + sizes[i];
        }
        for (int i = 1; i < k; i++)
            for (int j = 0; j < k - i; j++)
                calcDP(j, j + i);
        cout << dp[0][k-1] << '\n';
    }
}