#include <iostream>
using namespace std;
typedef long long ll;

const int MAX_N = 100;
int grid[MAX_N][MAX_N];
ll dp[MAX_N][MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            cin >> grid[r][c];
        }
    }

    dp[0][0] = 1;
    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            if (dp[r][c] && grid[r][c]) {
                int &step = grid[r][c];
                if (r + step < N) {
                    dp[r + step][c] += dp[r][c];
                }
                if (c + step < N) {
                    dp[r][c + step] += dp[r][c];
                }
            }
        }
    }
    cout << dp[N-1][N-1];
    return 0;
}