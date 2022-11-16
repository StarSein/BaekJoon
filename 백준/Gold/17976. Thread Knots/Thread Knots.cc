#include <iostream>
#include <algorithm>
using namespace std;
struct Thread {
    int s, e;
};
const int MAX_N = 1e5;
int n;
Thread arr[MAX_N];

bool isAble(int limit) {
    int loc = arr[0].s;
    for (int i = 1; i < n; i++) {
        loc = max(arr[i].s, loc + limit);
        if (loc > arr[i].e) {
            return false;
        }
    }
    return true;
}

int pSearch(int l, int r) {
    int ans = 0;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (isAble(mid)) {
            ans = mid;
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, l; cin >> x >> l;
        arr[i] = {x, x + l};
    }

    sort(arr, arr + n, [](Thread &a, Thread &b) {return a.s < b.s;});

    cout << pSearch(1, 2e9);
    return 0;
}