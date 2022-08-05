#include <iostream>
#include <vector>
using namespace std;

int N, M;
vector<int> vLec;

bool pSearch(int limit) {
    int cntLec = 1, curSize = 0;
    for (int &lec : vLec) {
        if (lec > limit) {
            return false;
        }
        if (lec + curSize > limit) {
            cntLec++;
            curSize = lec;
        } else {
            curSize += lec;
        }
    }
    return cntLec <= M;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    vLec.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> vLec[i];
    }

    int ans, left = 1, right = 1e9;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (pSearch(mid)) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    cout << ans;
    return 0;
}