#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;


int n, x, y, bit, answer;
vector<int> seq;

void dfs(int pos) {
    if (pos > 2*n) {
        answer++;
        return;
    }
    if (seq[pos]) {
        dfs(pos + 1);
        return;
    }
    for (int i = 1; i <= n; i++) {
        int np = pos + i + 1;
        if ((bit & 1 << i) == 0 && np <= 2 * n && !seq[np]) {
            seq[pos] = seq[np] = i;
            bit |= 1 << i;
            dfs(pos + 1);
            seq[pos] = seq[np] = 0;
            bit ^= 1 << i;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> x >> y;
    seq.resize(2 * n + 1, 0);
    seq[x] = seq[y] = y - x - 1;
    bit |= 1 << (y - x - 1);
    dfs(1);
    cout << answer;
}