#include <bits/stdc++.h>
using namespace std;

const int MAX_NUM = 1e4;
vector<int> numVec;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    numVec.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> numVec[i];
    }

    for (int k = 0; k <= MAX_NUM; k++) {
        int highCnt = 0, equalCnt = 0;
        for (int &e : numVec) {
            if (e > k)       highCnt++;
            else if (e == k) equalCnt++;
        }

        if (highCnt == k || highCnt < k && highCnt + equalCnt >= k) {
            cout << k;
            break;
        }
    }
    return 0;
}