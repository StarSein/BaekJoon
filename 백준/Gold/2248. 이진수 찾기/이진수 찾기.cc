#include <iostream>
#include <cstring>
using namespace std;


const int MAX_N = 31;

int dp[MAX_N][MAX_N];
char ans[MAX_N];

int N, L, I;

int getNumBin(int n, int l) {
    int &ret = dp[n][l];
    if (ret != -1) {
        return ret;
    }
    if (n == 0 || l == 0) {
        return ret = 1;
    }
    return ret = getNumBin(n - 1, l) + getNumBin(n - 1, l - 1);
}

void makeAns(int n, int l, int i) {
    if (n == 0) {
        return;
    }
    int pos = N - n;
    int prevNum = getNumBin(n - 1, l);
    if (prevNum < i) {
        ans[pos] = '1';
        makeAns(n - 1, l - 1, i - prevNum);
    } else {
        ans[pos] = '0';
        makeAns(n - 1, l, i);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    memset(dp, -1, sizeof(dp));

    cin >> N >> L >> I;

    makeAns(N, L, I);

    cout << ans;
    return 0;
}