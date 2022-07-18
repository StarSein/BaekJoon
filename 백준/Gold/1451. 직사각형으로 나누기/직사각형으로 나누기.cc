#include <iostream>
using namespace std;
typedef long long ll;

const int SZ = 50;

int arr[SZ][SZ], pSum[SZ+1][SZ+1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m; cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string s; cin >> s;
        for (int j = 0; j < m; j++) {
            arr[i][j] = s[j] - '0';
        }
    }
    for (int r = 1; r <= n; r++) {
        for (int c = 1; c <= m; c++) {
            pSum[r][c] = pSum[r][c-1] + arr[r-1][c-1];
        }
    }
    for (int c = 1; c <= m; c++) {
        for (int r = 1; r <= n; r++) {
            pSum[r][c] += pSum[r-1][c];
        }
    }
    ll ans = 0;
    for (int r = 1; r < n; r++) {
        for (int c = 1; c < m; c++) {
            ll part1 = pSum[r][c];
            ll part2 = pSum[r][m] - pSum[r][c];
            ll part3 = pSum[n][c] - pSum[r][c];
            ll part4 = pSum[n][m] - part1 - part2 - part3;

            ans = max(ans, (part1 + part2) * part3 * part4);
            ans = max(ans, part1 * part2 * (part3 + part4));
            ans = max(ans, (part1 + part3) * part2 * part4);
            ans = max(ans, part1 * part3 * (part2 + part4));
        }
    }
    for (int i = 1; i <= m - 2; i++) {
        for (int j = i + 1; j <= m - 1; j++) {
            ll part1 = pSum[n][i];
            ll part2 = pSum[n][j] - pSum[n][i];
            ll part3 = pSum[n][m] - pSum[n][j];
            ans = max(ans, part1 * part2 * part3);
        }
    }
    for (int i = 1; i <= n - 2; i++) {
        for (int j = i + 1; j <= n - 1; j++) {
            ll part1 = pSum[i][m];
            ll part2 = pSum[j][m] - pSum[i][m];
            ll part3 = pSum[n][m] - pSum[j][m];
            ans = max(ans, part1 * part2 * part3);
        }
    }
    cout << ans;
}