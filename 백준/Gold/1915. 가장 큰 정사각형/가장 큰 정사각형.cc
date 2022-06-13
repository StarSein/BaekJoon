#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 1'000;
string grid[MAX_N];
int dp[MAX_N][MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    for (int i = 0; i < n; i++)
        cin >> grid[i];
    
    fill(&dp[0][0], &dp[0][0] + MAX_N * MAX_N, 1);
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (grid[i][j] == '0')
                dp[i][j] = 0;

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            if (dp[i][j])
                dp[i][j] = min({dp[i-1][j-1], dp[i-1][j], dp[i][j-1]}) + 1;
        }
    }
    int answer = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            answer = max(answer, dp[i][j]);
    cout << answer * answer;
}