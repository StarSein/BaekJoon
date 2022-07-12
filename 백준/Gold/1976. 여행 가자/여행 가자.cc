#include <iostream>
using namespace std;


const int MAX_N = 200;
int grid[MAX_N][MAX_N];



int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    int m; cin >> m;
    for (int r = 1; r <= n; r++) {
        for (int c = 1; c <= n; c++) {
            cin >> grid[r][c];
        }
    }

    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] |= grid[i][k] & grid[k][j];
            }
        }
    }
    int prevC, currC;
    cin >> prevC;
    for (int i = 1; i < m; i++) {
        cin >> currC;
        if (prevC != currC && !grid[prevC][currC]) {
            cout << "NO";
            return 0;
        }
        prevC = currC;
    }
    cout << "YES";
    return 0;
}