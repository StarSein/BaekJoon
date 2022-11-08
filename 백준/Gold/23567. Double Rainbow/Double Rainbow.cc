#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 1e4;
int n, k;
int arr[MAX_N];
int inCnt[MAX_N];
int outCnt[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];

        outCnt[arr[i]]++;
    }

    int inNum = 0;
    int outNum = k;
    int lp = 0;
    int rp = 0;
    int ans = MAX_N + 1;
    while (lp < n) {
        if (rp < n && inNum < k) {
            int color = arr[rp++];
            if (++inCnt[color] == 1) {
                inNum++;
            }
            if (--outCnt[color] == 0) {
                outNum--;
            }
        } else {
            int color = arr[lp++];
            if (--inCnt[color] == 0) {
                inNum--;
            }
            if (++outCnt[color] == 1) {
                outNum++;
            }
        }
        if (inNum == k && outNum == k) {
            ans = min(ans, rp - lp);
        }
    }
    cout << (ans == MAX_N + 1 ? 0 : ans);
    return 0;
}