#include <iostream>
#include <vector>
using namespace std;

const int MAX_X = 1e5+1;
vector<int> bitree(MAX_X, 0);

void update(int i, int v) {
    while (i < bitree.size()) {
        bitree[i] += v;
        i += (i & -i);
    }
}

void rangeUpdate(int l, int r, int v) {
    update(l, v);
    update(r + 1, -v);
}

int sum(int i) {
    int ret = 0;
    while (i > 0) {
        ret += bitree[i];
        i -= (i & -i);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    
    int l, r;
    int answer, resL, resR;
    for (int i = 0; i < n; i++) {
        answer = 0;

        cin >> l >> r;

        resL = sum(l);
        answer += resL;
        resR = sum(r);
        answer += resR;

        rangeUpdate(l, l, -resL);
        rangeUpdate(r, r, -resR);
        rangeUpdate(l+1, r-1, 1);

        cout << answer << '\n';
    }
}