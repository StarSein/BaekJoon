#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 2501;
const int INF = 2501;
string inp;
bool pal[MAX_N][MAX_N];
int dp[MAX_N];

int dfs(int pos) {
    int &ret = dp[pos];
    if (ret != INF) {
        return ret;
    }
    if (pos == 0) {
        return ret = 0;
    }
    for (int i = 1; i <= pos; i++) {
        if (pal[i][pos]) {
            ret = min(ret, dfs(i - 1) + 1);
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> inp;
    inp.insert(inp.begin(), ' ');
    for (int i = 1; i < inp.size(); i++) {
        pal[i][i] = true;
    }
    for (int i = 1; i < inp.size() - 1; i++) {
        pal[i][i + 1] = (inp[i] == inp[i + 1]);
    }
    for (int k = 2; k < inp.size() - 1; k++) {
        for (int i = 1; i + k < inp.size(); i++) {
            pal[i][i + k] = (inp[i] == inp[i + k] && pal[i + 1][i + k - 1]);
        }
    }

    fill(dp + 1, dp + MAX_N, INF);
    cout << dfs(inp.size() - 1);
    return 0;
}