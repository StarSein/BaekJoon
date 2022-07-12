#include <iostream>
#include <vector>
#include <stack>
#include <tuple>
using namespace std;
typedef tuple<int, int, int, int> ti;

inline int charToInt(char &c) {
    return c - 'A';
}

vector<string> grid;
stack<ti> st;

int dy[4] {0, 1, 0, -1};
int dx[4] {1, 0, -1, 0};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int r, c; cin >> r >> c;
    grid.resize(r);
    for (int i = 0; i < r; i++) {
        cin >> grid[i];
    }
    st.emplace(1 << charToInt(grid[0][0]), 1, 0, 0);
    int mask, cnt, cr, cc, nr, nc, bit;
    int ans = 0;
    while (!st.empty()) {
        tie(mask, cnt, cr, cc) = st.top();
        st.pop();
        ans = max(ans, cnt);
        for (int i = 0; i < 4; i++) {
            nr = cr + dy[i], nc = cc + dx[i];
            if (0 <= nr && nr < r && 0 <= nc && nc < c) {
                bit = 1 << charToInt(grid[nr][nc]);
                if (~mask & bit) {
                    st.emplace(mask | bit, cnt + 1, nr, nc);
                }
            }
        }
    }
    cout << ans;
}