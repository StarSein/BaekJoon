#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5 + 1;
int B[MAX_N], P[MAX_N];
int leftBound[MAX_N + 1], rightBound[MAX_N + 1];
ll pSum[MAX_N];

int getLB(int x) {
    if (leftBound[x] == x) {
        return x;
    } else if (leftBound[x] == -1) {
        return -1;
    } else {
        return leftBound[x] = getLB(leftBound[x]);
    }
}

int getRB(int x) {
    if (rightBound[x] == x) {
        return x;
    } else if (rightBound[x] == -1) {
        return -1;
    } else {
        return rightBound[x] = getRB(rightBound[x]);
    }
}

void merge(int a, int b) {
    leftBound[b] = leftBound[a];
    rightBound[a] = rightBound[b];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> B[i];
    }
    for (int i = 1; i <= N; i++) {
        cin >> P[i];
    }

    for (int i = 1; i <= N; i++) {
        pSum[i] = pSum[i-1] + B[i];
    }

    memset(leftBound, -1, sizeof(leftBound));
    memset(rightBound, -1, sizeof(rightBound));

    ll bestScore = 0, curScore = 0;
    for (int i = N; i > 1; i--) {
        int pos = P[i];
        leftBound[pos] = pos;
        rightBound[pos] = pos;

        int lp = pos - 1, rp = pos + 1;
        int llb = getLB(lp), rrb = getRB(rp);
        if (llb != -1 && rrb != -1) {
            curScore -= (pSum[lp] - pSum[llb - 1]) * (lp - llb + 1)
                        + (pSum[rrb] - pSum[rp - 1]) * (rrb - rp + 1);
            merge(llb, pos);
            merge(pos, rrb);
        } else if (llb != -1) {
            curScore -= (pSum[lp] - pSum[llb - 1]) * (lp - llb + 1);
            merge(llb, pos);
        } else if (rrb != -1) {
            curScore -= (pSum[rrb] - pSum[rp - 1]) * (rrb - rp + 1);
            merge(pos, rrb);
        }
        int lb = getLB(pos);
        int rb = getRB(pos);
        curScore += (pSum[rb] - pSum[lb - 1]) * (rb - lb + 1);
        bestScore = max(bestScore, curScore);
    }
    cout << bestScore;
    return 0;
}