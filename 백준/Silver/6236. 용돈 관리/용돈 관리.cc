#include <iostream>
#include <vector>
using namespace std;

int N, M;
vector<int> vCost; 

bool pSearch(int withdrawal) {
    int cntWithdraw = 1, curBalance = withdrawal;
    for (int &cost : vCost) {
        if (cost > withdrawal) {
            return false;
        }
        if (cost > curBalance) {
            cntWithdraw++;
            curBalance = withdrawal;
        }
        curBalance -= cost;
    }
    return cntWithdraw <= M;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    vCost.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> vCost[i];
    }

    int K, left = 1, right = 1e9;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (pSearch(mid)) {
            K = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    cout << K;
    return 0;
}