#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

const int W = 1e6;
pi cmd[2 * W + 1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    int i, k;
    char j;
    while (N--) {
        cin >> i >> j >> k;
        cmd[i + W] = {(j == 'R' ? 1 : - 1), k};
    }
    int curPos; cin >> curPos;
    curPos += W;
    while (cmd[curPos].first) {
        curPos += (cmd[curPos].first) * cmd[curPos].second;
    }
    cout << curPos - W;
    return 0;
}