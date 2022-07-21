#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int MAX_N = 20;
const ll NINF = -4e10;

ll grid[MAX_N][MAX_N];
vector<ll> leftSumA[MAX_N], leftSumB[MAX_N];
int N;
ll K;
ll ans;

void dfs1(int l, int r, ll maxSum, ll lSum) {
    lSum = max(lSum + grid[l][r], grid[l][r]);
    maxSum = max(maxSum, lSum);
    if (maxSum <= K) {
        if (l + r == N - 1) {
            if (maxSum == K) {
                leftSumA[l].push_back(lSum);
            } else {
                leftSumB[l].push_back(lSum);
            }
            return;
        }
        dfs1(l + 1, r, maxSum, lSum);
        dfs1(l, r + 1, maxSum, lSum);
    }
}

void dfs2(int l, int r, ll maxSum, ll rSum) {
    if (l + r == N - 1) {
        if (maxSum == K) {
            ans += upper_bound(leftSumA[l].begin(), leftSumA[l].end(), K - rSum) - leftSumA[l].begin();
            ans += upper_bound(leftSumB[l].begin(), leftSumB[l].end(), K - rSum) - leftSumB[l].begin();
        } else {
            ans += upper_bound(leftSumB[l].begin(), leftSumB[l].end(), K - rSum) - upper_bound(leftSumB[l].begin(), leftSumB[l].end(), K - rSum - 1);
            ans += upper_bound(leftSumA[l].begin(), leftSumA[l].end(), K - rSum) - leftSumA[l].begin();
        }
        return;
    }
    rSum = max(rSum + grid[l][r], grid[l][r]);
    maxSum = max(maxSum, rSum);
    if (maxSum <= K) {
        dfs2(l - 1, r, maxSum, rSum);
        dfs2(l, r - 1, maxSum, rSum);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> K;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> grid[i][j];
        }
    }
    dfs1(0, 0, NINF, 0);
    for (int i = 0; i < N; i++) {
        sort(leftSumA[i].begin(), leftSumA[i].end());
        sort(leftSumB[i].begin(), leftSumB[i].end());
    }
    dfs2(N-1, N-1, NINF, 0);
    cout << ans;
}