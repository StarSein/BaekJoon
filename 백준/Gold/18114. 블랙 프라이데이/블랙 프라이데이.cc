#include <iostream>
#include <algorithm>
using namespace std;
typedef long long ll;

const int MAX_N = 5e3;
ll arr[MAX_N];

int N, C; 

bool binarySearch(int target, int lp) {
    int rp = N - 1;
    while (lp <= rp) {
        int mid = (lp + rp) >> 1;
        if (arr[mid] < target) {
            lp = mid + 1;
        } else if (arr[mid] > target) {
            rp = mid - 1;
        } else {
            return true;
        }
    }
    return false;
}

bool solve() {
    if (binarySearch(C, 0)) {
        return true;
    }
    for (int i = 0; i < N - 1; i++) {
        for (int j = i + 1; j < N; j++) {
            int diff = C - (arr[i] + arr[j]);
            if (diff == 0 || diff > 0 && binarySearch(diff, j + 1)) {
                return true;
            }
        }
    }
    return false;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> C;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    sort(arr, arr + N);

    cout << (solve() ? 1 : 0);
    return 0;
}