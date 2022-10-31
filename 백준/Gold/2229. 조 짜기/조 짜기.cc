#include <iostream>
using namespace std;

const int MAX_N = 1e3 + 1;
int arr[MAX_N];
int dp[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
    }

    if (N == 1) {
        cout << 0;
        return 0;
    }

    for (int i = 2; i <= N; i++) {
        int minScore = arr[i];
        int maxScore = arr[i];
        for (int j = i - 1; j > 0; j--) {
            minScore = min(minScore, arr[j]);
            maxScore = max(maxScore, arr[j]);
            dp[i] = max(dp[i], dp[j - 1] + maxScore - minScore);
        }
    }
    cout << dp[N];
    return 0;
}