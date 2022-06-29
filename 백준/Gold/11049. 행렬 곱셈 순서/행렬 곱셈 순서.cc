#include <iostream>
#include <vector>
using namespace std;
#define X first
#define Y second

typedef pair<int, int> pi;

const int MAX_N = 500, INF = ((long long) 1 << 31 - 1);
int n;
vector<pi> vec;

int dp[MAX_N][MAX_N];

void init() {
    fill(&dp[0][0], &dp[0][0] + MAX_N * MAX_N, -1);
    for (int i = 0; i < MAX_N; i++) {
        dp[i][i] = 0;
    }
}

int getOpt(int l, int r) {
    if (dp[l][r] != -1) {
        return dp[l][r];
    }
    int v, optV = INF;
    for (int m = l; m < r; m++) {
        optV = min(optV, getOpt(l, m) + getOpt(m+1, r) + vec[l].X * vec[m].Y * vec[r].Y);
    }
    return dp[l][r] = optV;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    vec.reserve(n);
    int r, c;
    for (int i = 0; i < n; i++) {
        cin >> r >> c;
        vec.emplace_back(r, c);
    }

    init();
    cout << getOpt(0, n-1);
}