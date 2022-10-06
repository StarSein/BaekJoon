#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int SZ = 300;
int grid[SZ][SZ];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int m, n; cin >> m >> n;
    for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
            cin >> grid[r][c];
        }
    }

    ll ans = 0;
    for (int sr = 0; sr < m; sr++) {
        for (int sc = 0; sc < n; sc++) {
            for (int er = sr; er <= sr + 9 && er < m; er++) {
                for (int ec = sc; ec <= sc + 9 && ec < n; ec++) {
                    int curSum = 0;
                    for (int r = sr; r <= er; r++) {
                        for (int c = sc; c <= ec; c++) {
                            curSum += grid[r][c];
                        }
                    }
                    if (curSum == 10) {
                        ans++;
                    }
                }
            }
        }
    }
    cout << ans;
    return 0;
}