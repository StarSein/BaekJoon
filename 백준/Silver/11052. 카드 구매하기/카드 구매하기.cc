#include <iostream>
using namespace std;

const int MAX_N = 1e3;
int dp[MAX_N+1], P[MAX_N+1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> P[i];
    }

    for (int i = 1; i <= N; i++) {
        for (int j = i; j <= N; j++) {
            dp[j] = max(dp[j], dp[j-i] + P[i]);
        }
    }
    cout << dp[N];
}