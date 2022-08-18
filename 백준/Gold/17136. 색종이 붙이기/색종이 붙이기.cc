#include <iostream>
#include <algorithm>
using namespace std;

const int SZ = 10, NUM_PAPER = 5, LIMIT = 5, INF = NUM_PAPER * LIMIT;
bool grid[SZ][SZ];
int cnt[NUM_PAPER + 1];
int curSum = 0, ans = INF;

void dfs(int pos) {
    if (curSum >= ans) {
        return;
    }
    if (pos == -1) {
        ans = curSum;
        return;
    }
    int r = pos / 10, c = pos % 10;
    if (grid[r][c]) {
        for (int sz = min({r + 1, c + 1, NUM_PAPER}); sz > 0; sz--) {
            bool flag = false;
            for (int i = r; i > r - sz; i--) {
                for (int j = c; j > c - sz; j--) {
                    if (!grid[i][j]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (flag) {
                continue;
            }
            if (cnt[sz] < LIMIT) {
                cnt[sz]++;
                curSum++;
                for (int i = r; i > r - sz; i--) {
                    for (int j = c; j > c - sz; j--) {
                        grid[i][j] = false;
                    }
                }
                dfs(pos - sz);
                cnt[sz]--;
                curSum--;
                for (int i = r; i > r - sz; i--) {
                    for (int j = c; j > c - sz; j--) {
                        grid[i][j] = true;
                    }
                }
            }
        }
    } else {
        dfs(pos - 1);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int r = 0; r < SZ; r++) {
        for (int c = 0; c < SZ; c++) {
            cin >> grid[r][c];
        }
    }

    dfs(99);
    cout << (ans == INF ? -1 : ans);
    return 0;
}