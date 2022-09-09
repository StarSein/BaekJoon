#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const pi LEAF = {0, 0};
vector<pi> vChild;
vector<int> dp;

int ans = 0;

inline bool isLeaf(int node) {
    return vChild[node] == LEAF;
}

void calcAns(int node) {
    if (!isLeaf(node)) {
        int &leftDist = dp[vChild[node].first];
        int &rightDist = dp[vChild[node].second];
        ans += leftDist + rightDist;
        calcAns(vChild[node].first);
        calcAns(vChild[node].second);
        ans += abs(leftDist - rightDist);
        dp[node] += max(leftDist, rightDist);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int k; cin >> k;
    vChild.resize(1 << (k + 1));
    dp.resize(vChild.size());
    for (int node = 1; node < (1 << k); node++) {
        int leftWeight, rightWeight; cin >> leftWeight >> rightWeight;
        vChild[node] = {node << 1, node << 1 | 1};
        dp[node << 1] = leftWeight;
        dp[node << 1 | 1] = rightWeight;
    }
    calcAns(1);
    cout << ans;
    return 0;
}