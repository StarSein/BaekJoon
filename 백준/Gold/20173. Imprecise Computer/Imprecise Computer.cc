#include <iostream>
using namespace std;

const int MAX_N = 1e6;
int n;
int D[MAX_N];
bool dp[MAX_N][2][2];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> D[i];
    }

    for (int i = 0; i < n; i++) {
        if (D[i] > 2) {
            cout << "NO";
            return 0;
        }
    }

    dp[1][0][0] = (D[0] == 0);
    dp[1][0][1] = (D[0] == 1);
    dp[1][1][0] = (D[0] == 1);
    dp[1][1][1] = (D[0] == 0);
    for (int i = 2; i < n; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                for (int pj = 0; pj < 2; pj++) {
                    for (int pk = 0; pk < 2; pk++) {
                        if (dp[i - 1][pj][pk]) {
                            int r1 = pj + 1 - j;
                            int r2 = pk + 1 - k;
                            if (abs(r1 - r2) == D[i - 1]) {
                                dp[i][j][k] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
            if (dp[n - 1][j][k] && abs(j - k) == D[n - 1]) {
                cout << "YES";
                return 0;
            }
        }
    }
    cout << "NO";
    return 0;
}