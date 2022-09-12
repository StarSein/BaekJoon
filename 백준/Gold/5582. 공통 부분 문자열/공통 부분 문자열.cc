#include <iostream>
using namespace std;

const int SZ = 4000;
int dp[SZ + 1][SZ + 1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s1, s2; cin >> s1 >> s2;
    for (int i = 0; i < s1.size(); i++) {
        for (int j = 0; j < s2.size(); j++) {
            if (s1[i] == s2[j]) {
                dp[i+1][j+1] = dp[i][j] + 1;
            }
        }
    }

    int ans = 0;
    for (int i = 1; i <= s1.size(); i++) {
        for (int j = 1; j <= s2.size(); j++) {
            ans = max(ans, dp[i][j]);
        }
    }
    cout << ans;
    return 0;
}