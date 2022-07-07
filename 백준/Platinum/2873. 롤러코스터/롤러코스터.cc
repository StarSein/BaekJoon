#include <iostream>
using namespace std;

const int SZ = 1'000;

int grid[SZ][SZ];
char prev[SZ][SZ];
string ans;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int r, c; cin >> r >> c;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            cin >> grid[i][j];
        }
    }
    ans.reserve(r * c);
    if (r & 1) {
        for (int i = 0; i < r-1; i++) {
            if (i & 1) {
                for (int j = 0; j < c-1; j++) {
                    ans += 'L';
                }
            } else {
                for (int j = 0; j < c-1; j++) {
                    ans += 'R';
                }
            }
            ans += 'D';
        }
        for (int j = 0; j < c-1; j++) {
            ans += 'R';
        }
    } else if (c & 1) {
        for (int i = 0; i < c-1; i++) {
            if (i & 1) {
                for (int j = 0; j < r-1; j++) {
                    ans += 'U';
                }
            } else {
                for (int j = 0; j < r-1; j++) {
                    ans += 'D';
                }
            }
            ans += 'R';
        }
        for (int j = 0; j < r-1; j++) {
            ans += 'D';
        }
    } else {
        int mr, mc, minVal = 1'001;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (((i + j) & 1) && grid[i][j] < minVal) {
                    minVal = grid[i][j];
                    mr = i;
                    mc = j;
                }
            }
        }
        mr = (mr & 1 ? mr-1 : mr);

        for (int i = 0; i < mr; i++) {
            if (i & 1) {
                for (int j = 0; j < c-1; j++) {
                    ans += 'L';
                }
            } else {
                for (int j = 0; j < c-1; j++) {
                    ans += 'R';
                }
            }
            ans += 'D';
        }
        for (int i = 0; i < mc; i++) {
            if (i & 1) {
                ans += "UR";
            } else {
                ans += "DR";
            }
        }
        for (int i = mc; i < c-1; i++) {
            if (i & 1) {
                ans += "RU";
            } else {
                ans += "RD";
            }
        }
        for (int i = mr+1; i < r-1; i++) {
            ans += 'D';
            if (i & 1) {
                for (int j = 0; j < c-1; j++) {
                    ans += 'L';
                }
            } else {
                for (int j = 0; j < c-1; j++) {
                    ans += 'R';
                }
            }
        }
    }
    cout << ans;
}