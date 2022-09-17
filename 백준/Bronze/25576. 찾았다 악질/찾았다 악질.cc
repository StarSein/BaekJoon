#include <bits/stdc++.h>
using namespace std;

const int MAX_M = 100;
int ralpa[MAX_M];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M; cin >> N >> M;

    for (int i = 0; i < M; i++) {
        cin >> ralpa[i];
    }
    int cntBad = 0;
    for (int i = 0; i < N - 1; i++) {
        int absDiffSum = 0;
        for (int j = 0; j < M; j++) {
            int num; cin >> num;
            absDiffSum += abs(num - ralpa[j]);
        }
        if (absDiffSum > 2000) {
            cntBad++;
        }
    }
    if (2 * cntBad >= N - 1) {
        cout << "YES";
    } else {
        cout << "NO";
    }
    return 0;
}