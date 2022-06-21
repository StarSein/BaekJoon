#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> vLoc;

bool isAble(int maxLen, int cnt) {
    for (int i = 1; i < vLoc.size(); i++) {
        cnt -= (vLoc[i] - vLoc[i-1] - 1) / maxLen;
    }
    return cnt >= 0;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, l; cin >> n >> m >> l;
    vLoc.resize(n+2);
    for (int i = 0; i < n; i++) {
        cin >> vLoc[i];
    }
    vLoc[n] = 0, vLoc[n+1] = l;
    sort(vLoc.begin(), vLoc.end(), less<int>());
    int mid, lp = 1, rp = l;
    int answer;
    while (lp <= rp) {
        mid = (lp + rp) / 2;
        if (isAble(mid, m)) {
            answer = mid;
            rp = mid - 1;
        } else {
            lp = mid + 1;
        }
    }
    cout << answer;
}