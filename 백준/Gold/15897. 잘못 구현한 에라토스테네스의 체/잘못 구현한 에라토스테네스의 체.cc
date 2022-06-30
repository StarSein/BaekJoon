#include <iostream>
#include <cmath>
using namespace std;
typedef long long ll;
ll answer = 0;
int n;

int binarySearch(int l, int r, int v) {
    double mid;
    int ret;
    while (l <= r) {
        mid = (l + r) >> 1;
        if (ceil(n/mid) == v) {
            ret = mid;
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return ret;
}

int main() {
    cin >> n;
    
    for (int i = 1; i < sqrt(n); i++) {
        answer += ceil(n/(double) i);
    }
    int i = ceil(sqrt(n));
    int ub, val;
    while (i <= n) {
        val = ceil(n/(double) i);
        ub = binarySearch(i, (ll) n, val);
        answer += val * (ub - i + 1);
        i = ub + 1;
    }
    cout << answer;
}