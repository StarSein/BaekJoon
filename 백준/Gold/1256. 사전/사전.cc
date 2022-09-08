#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

const int SZ = 100, INF = 1e9 + 1;
int dp[2 * SZ + 1][SZ + 1];
vector<char> ans;

int getNumStr(int pos, int numZ) {
    int &ret = dp[pos][numZ];
    if (ret != -1) {
        return ret;
    }
    if (numZ == 0) {
        return ret = 1;
    }
    if (pos == 0) {
        return ret = 0;
    }
    return ret = min(getNumStr(pos - 1, numZ) + getNumStr(pos - 1, numZ - 1), INF);
}

void makeAns(int pos, int numZ, int k) {
    if (pos == 0) {
        return;
    }
    int prevNum = dp[pos-1][numZ];
    if (prevNum < k) {
        ans.push_back('z');
        makeAns(pos - 1, numZ - 1, k - prevNum);
    } else {
        ans.push_back('a');
        makeAns(pos - 1, numZ, k);
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    memset(dp, -1, sizeof(dp));

    int N, M, K; cin >> N >> M >> K;

    ans.reserve(N + M);
    getNumStr(N + M, M);
    makeAns(N + M, M, K);

    if (dp[N+M][M] >= K) {
        copy(ans.begin(), ans.end(), ostream_iterator<char>(cout, ""));
    } else {
        cout << -1;
    }
    return 0;
}