#include <iostream>
using namespace std;


const int MAX_D = 1e5+1;
int dp[MAX_D];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int d, p; cin >> d >> p;
    dp[0] = 1e9;
    for (int i = 0; i < p; i++) {
        int l, c; cin >> l >> c;
        for (int j = d; j >= l; j--) {
            dp[j] = max(dp[j], min(dp[j-l], c));
        }
    }
    cout << dp[d];
}