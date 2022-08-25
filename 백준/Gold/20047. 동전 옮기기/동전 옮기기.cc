#include <bits/stdc++.h>
using namespace std;

int n;
string S, T;
vector<char> twoCoin(2);
int i, j;
string ans = "NO";

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    cin >> S >> T;
    cin >> i >> j;

    twoCoin[0] = S[i];
    twoCoin[1] = S[j];
    S.erase(j, 1);
    S.erase(i, 1);

    for (int k = 0; k < 2; k++) {
        bool isAble = true;

        int si = 0, ti = 0, idx = 0;
        for (; si < S.size() && ti < T.size(); si++, ti++) {
            if (S[si] != T[ti]) {
                if (idx < twoCoin.size() && twoCoin[idx] == T[ti]) {
                    si--;
                    idx++;
                } else {
                    isAble = false;
                    break;
                }
            }
        }
        if (isAble) {
            for (; idx < twoCoin.size() && ti < T.size(); idx++, ti++) {
                if (twoCoin[idx] != T[ti]) {
                    isAble = false;
                }
            }
        }
        if (isAble) {
            ans = "YES";
            break;
        }
        reverse(S.begin(), S.end());
        reverse(T.begin(), T.end());
        reverse(twoCoin.begin(), twoCoin.end());
    }
    cout << ans;
    return 0;
}