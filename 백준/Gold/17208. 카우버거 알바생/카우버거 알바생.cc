#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> dp;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k; cin >> n >> m >> k;
    dp.resize(m+1, vector<int>(k+1, 0));
    int x, y;
    for (int i = 0; i < n; i++) {
        cin >> x >> y;
        for (int r = m; r >= 1; r--) {
            for (int c = k; c >= 1; c--) {
                if (r >= x && c >= y) {
                    dp[r][c] = max(dp[r][c], dp[r-x][c-y] + 1);
                }
            }
        }
    }
    cout << dp[m][k];
}