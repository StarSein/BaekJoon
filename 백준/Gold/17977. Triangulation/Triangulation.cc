#include <iostream>
using namespace std;

const int MAX_N = 1e6;
int n;
int dp[MAX_N + 1];

void makeDP() {
    dp[3] = 0;
    dp[4] = 1;
    dp[5] = 2;
    for (int i = 6; i <= MAX_N; i++) {
        dp[i] = 2 + dp[(i + 1) / 2];
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    makeDP();
    cout << dp[n];
    return 0;
}