#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX_N = 1e4, DIGIT = 10, INF = 1e8;
int dp[MAX_N + 1][DIGIT], choice[MAX_N + 1][DIGIT];

int N;
string inpS, inpT;
vector<int> S, T;

int ans1 = INF;
int ans2[MAX_N + 1];

void trackChoice(int pos, int stat) {
    if (pos == 0) {
        return;
    }
    ans2[pos] = choice[pos][stat];
    if (choice[pos][stat] > 0) {
        int nextStat = stat - choice[pos][stat];
        nextStat = (nextStat >= 0 ? nextStat : DIGIT + nextStat);
        trackChoice(pos - 1, nextStat);
    } else {
        trackChoice(pos - 1, stat);
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    fill(&dp[0][0], &dp[0][0] + (MAX_N + 1) * DIGIT, INF);

    cin >> N;
    cin >> inpS >> inpT;

    S.resize(N);
    T.resize(N);
    for (int i = 0; i < N; i++) {
        S[i] = inpS[i] - '0';
        T[i] = inpT[i] - '0';
    }

    dp[0][0] = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < DIGIT; j++) {
            if (dp[i][j] != INF) {
                int curNum = S[i] + j;
                if (curNum >= DIGIT) {
                    curNum -= DIGIT;
                }

                int leftCnt = T[i] - curNum;
                if (leftCnt < 0) {
                    leftCnt += DIGIT;
                }
                int lj = j + leftCnt;
                if (lj >= DIGIT) {
                    lj -= DIGIT;
                }
                if (dp[i][j] + leftCnt < dp[i + 1][lj]) {
                    dp[i + 1][lj] = dp[i][j] + leftCnt;
                    choice[i + 1][lj] = leftCnt;
                }

                int rightCnt = curNum - T[i];
                if (rightCnt < 0) {
                    rightCnt += DIGIT;
                }
                if (dp[i][j] + rightCnt < dp[i + 1][j]) {
                    dp[i + 1][j] = dp[i][j] + rightCnt;
                    choice[i + 1][j] = -rightCnt;
                }
            }
        }
    }
    for (int i = 0; i < DIGIT; i++) {
        ans1 = min(ans1, dp[N][i]);
    }
    for (int i = 0; i < DIGIT; i++) {
        if (dp[N][i] == ans1) {
            trackChoice(N, i);
        }
    }
    cout << ans1 << '\n';
    for (int i = 1; i <= N; i++) {
        cout << i << ' ' << ans2[i] << '\n';
    }
    return 0;
}