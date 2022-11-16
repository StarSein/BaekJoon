#include <iostream>
#include <algorithm>
using namespace std;
typedef long long ll;

struct Mineral {
    int s, e, t;
};

const int MAX_M = 100, MAX_N = 10000, SZ = 15000;
int m, n;
int prices[MAX_M + 1];
Mineral minerals[MAX_N];
ll dp[SZ];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> m >> n;
    for (int i = 1; i <= m; i++) {
        cin >> prices[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> minerals[i].s >> minerals[i].e >> minerals[i].t;
    }

    sort(minerals, minerals + n, [](Mineral &a, Mineral &b) {return a.s < b.s;});

    for (int i = 1, j = 0; i < SZ; i++) {
        dp[i] = max(dp[i], dp[i - 1]);
        for (; j < n && minerals[j].s == i; j++) {
            const Mineral &cur = minerals[j];
            dp[cur.e] = max(dp[cur.e] , dp[i] + (cur.e - i) * prices[cur.t]);
        }
    }
    cout << dp[SZ - 1];
    return 0;
}