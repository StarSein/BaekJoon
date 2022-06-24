#include <iostream>
using namespace std;

const int MAX_N = 2000;

int n, m;
string grid[MAX_N];

int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};

inline int countAdj(int r, int c) {
    int nr, nc;
    int cnt = 0;
    for (int i = 0; i < 4; i++) {
        nr = r + dy[i];
        nc = c + dx[i];
        if (0 <= nr && nr < n && 0 <= nc && nc < m) {
            cnt++;
        }
    }
    return cnt;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int r = 0; r < n; r++) {
        cin >> grid[r];
    }
    cout << '1' << '\n';
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            if (countAdj(r, c) & 1) {
                if (grid[r][c] == 'B') {
                    cout << '2';
                } else {
                    cout << '3';
                }
            } else {
                if (grid[r][c] == 'B') {
                    cout << '3';
                } else {
                    cout << '2';
                }
            }
        }
        cout << '\n';
    }
}