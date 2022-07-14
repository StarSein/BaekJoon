#include <iostream>
#include <vector>
using namespace std;

const int MOD = 1e6;
vector<int> dp;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s; cin >> s;
    if (s[0] == '0') {
        cout << 0;
        return 0;
    }
    dp.resize(s.size()+1);
    dp[0] = dp[1] = 1;
    for (int i = 2; i <= s.size(); i++) {
        int num = stoi(s.substr(i-2, 2));
        if (num % 10) {
            dp[i] += dp[i-1];
        } else if (num >= 30 || num == 0) {
            cout << 0;
            return 0;
        }
        if (10 <= num && num <= 26) {
            dp[i] += dp[i-2];
        }
        dp[i] %= MOD;
    }
    cout << dp[s.size()];
}