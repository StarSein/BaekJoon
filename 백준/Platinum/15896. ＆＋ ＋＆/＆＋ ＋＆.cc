#include <iostream>
#include <climits>
using namespace std;
typedef long long ll;

const int MAX_N = 1e6, MAX_P = 30, MOD = 1'999;
int A[MAX_N], B[MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> A[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> B[i];
    }

    int ans1 = 0;
    for (int pos = 0; pos < MAX_P; pos++) {
        ll cntA = 0, cntB = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] & 1 << pos) cntA++;
            if (B[i] & 1 << pos) cntB++;
        }
        ans1 = (ans1 + ((cntA * cntB) % MOD) * (1 << pos)) % MOD;
    }
    

    int ans2 = 0, mask = 1, firstBit = 1;
    for (int pos = 0; pos < MAX_P; pos++) {
        int minA[2] = {INT_MAX, INT_MAX}, maxA[2] = {INT_MIN, INT_MIN};
        int minB[2] = {INT_MAX, INT_MAX}, maxB[2] = {INT_MIN, INT_MIN};

        for (int i = 0; i < n; i++) {
            int C = A[i] & mask;
            int D = B[i] & mask;

            int firstA = (C & firstBit ? 1 : 0);
            int firstB = (D & firstBit ? 1 : 0);

            minA[firstA] = min(minA[firstA], C);
            maxA[firstA] = max(maxA[firstA], C);
            minB[firstB] = min(minB[firstB], D);
            maxB[firstB] = max(maxB[firstB], D);
        }

        if (minA[0] != INT_MAX && minB[0] != INT_MAX && (((minA[0] + minB[0]) & firstBit) == 0) ||
            minA[1] != INT_MAX && minB[1] != INT_MAX && (((minA[1] + minB[1]) & firstBit) == 0) ||
            maxA[0] != INT_MIN && maxB[1] != INT_MIN && (((maxA[0] + maxB[1]) & firstBit) == 0) ||
            maxA[1] != INT_MIN && maxB[0] != INT_MIN && (((maxA[1] + maxB[0]) & firstBit) == 0)) {
            ans2 += 0;
        } else {
            ans2 += firstBit;
        }
        mask = (mask << 1) + 1;
        firstBit <<= 1;
    }
    cout << ans1 << ' ' << ans2;
}