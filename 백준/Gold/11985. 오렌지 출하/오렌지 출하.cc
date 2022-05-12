#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> oranges;
vector<long long> dp;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, m, k; cin >> n >> m >> k;
    oranges.resize(n + 1, 0);
    dp.resize(n + 1, 0);
    for (int i = 1; i <= n; i++) {
        cin >> oranges[i];
        dp[i] = static_cast<long long>(k) * i;
    }
    int minSize, maxSize;
    for (int i = 0; i < n; i++) {
        minSize = oranges[i+1]; maxSize = oranges[i+1]; 
        for (int j = 1; j <= m && i + j <= n; j++) {
            minSize = min(minSize, oranges[i+j]);
            maxSize = max(maxSize, oranges[i+j]);
            dp[i+j] = min(dp[i+j], dp[i] + k + static_cast<long long>(j) * (maxSize - minSize));
        }
    }
    cout << dp[n];
}