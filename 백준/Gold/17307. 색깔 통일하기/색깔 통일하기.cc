#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

vector<ll> arr, prefSum, suffSum;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, c; cin >> n >> c;
    arr.resize(n);
    prefSum.resize(n);
    suffSum.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    prefSum[0] = 0;
    for (int i = 1; i < n; i++) {
        prefSum[i] = prefSum[i-1] + (arr[i] >= arr[i-1] ? arr[i] - arr[i-1] : c + arr[i] - arr[i-1]);
    }
    suffSum[n-1] = 0;
    for (int i = n-2; i >= 0; i--) {
        suffSum[i] = suffSum[i+1] + (arr[i] >= arr[i+1] ? arr[i] - arr[i+1] : c + arr[i] - arr[i+1]);
    }

    ll minCnt = prefSum[n-1] - prefSum[0], minPos = 0;
    ll curCnt;
    for (int i = 1; i < n; i++) {
        curCnt = max(prefSum[n-1] - prefSum[i], suffSum[0] - suffSum[i]);
        if (curCnt < minCnt) {
            minCnt = curCnt;
            minPos = i;
        }
    }
    cout << minPos + 1 << '\n' << minCnt;
}