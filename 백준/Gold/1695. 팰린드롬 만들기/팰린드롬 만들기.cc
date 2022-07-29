#include <iostream>
#include <algorithm>
using namespace std;


const int MAX_N = 5e3, INF = 5e3;
int dp[MAX_N][MAX_N];
int arr[MAX_N];

int getMinNum(int l, int r) {
    int &ret = dp[l][r];
    if (ret != INF) {
        return ret;
    }
    if (arr[l] == arr[r]) {
        return ret = getMinNum(l + 1, r - 1);
    } else {
        return ret = min(getMinNum(l, r - 1) + 1, getMinNum(l + 1, r) + 1);
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    fill(&dp[0][0], &dp[0][0] + MAX_N * MAX_N, INF);
    for (int i = 0; i < N; i++) {
        dp[i][i] = 0;
    }
    for (int i = 0; i < N - 1; i++) {
        dp[i][i+1] = (arr[i] == arr[i+1] ? 0 : 1);
    }

    cout << getMinNum(0, N - 1);
}