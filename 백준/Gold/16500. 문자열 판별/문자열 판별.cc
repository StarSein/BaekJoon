#include <iostream>
#include <vector>
using namespace std;

const int MAX_N = 100;
int dp[MAX_N];

string S;
int N;
vector<string> A;

int isAble(int i) {
    if (i == -1) {
        return 1;
    }
    if (dp[i] != -1) {
        return dp[i];
    }
    
    int &ret = dp[i];
    for (string &e : A) {
        bool isMatch = true;
        if (e.size() > i + 1) {
            continue;
        }
        for (int j = 0; j < e.size(); j++) {
            if (S[i - j] != e[e.size() - 1 - j]) {
                isMatch = false;
                break;
            }
        }
        if (isMatch) {
            ret = max(ret, isAble(i - e.size()));
        }
    }
    ret = (ret != -1 ? ret : 0);
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    fill(dp, dp + MAX_N, -1);

    cin >> S >> N;
    A.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }

    cout << isAble(S.size() - 1);
    return 0;
}