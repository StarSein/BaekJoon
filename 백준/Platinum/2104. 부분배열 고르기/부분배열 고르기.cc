#include <iostream>
#include <algorithm>
using namespace std;
typedef long long ll;

const int MAX_N = 1e5;
ll A[MAX_N];

ll getCurScore(int l, int r, int m) {
    ll minVal = A[m], curSum = A[m];
    ll score = curSum * minVal;
    int lp = m - 1, rp = m + 1;
    while (l <= lp || rp <= r) {
        if (rp > r || l <= lp && A[lp] > A[rp]) {
            minVal = min(minVal, A[lp]);
            curSum += A[lp];
            lp--;
        } else {
            minVal = min(minVal, A[rp]);
            curSum += A[rp];
            rp++;
        }
        score = max(score, curSum * minVal);
    }
    return score;
}

ll getMaxScore(int l, int r) {
    if (l == r) {
        return A[l] * A[l];
    }
    int mid = (l + r) >> 1;
    return max({getMaxScore(l, mid), getCurScore(l, r, mid), getMaxScore(mid + 1, r)});
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }

    cout << getMaxScore(0, N - 1);
}