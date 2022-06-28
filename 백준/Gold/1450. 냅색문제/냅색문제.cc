#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const int MAX_N = 30;
ll weight[MAX_N];
vector<int> vW1, vW2;


int binarySearch(vector<int> &vec, int target) {
    int mid, lp = 0, rp = vec.size()-1;
    int ret = -1;
    while (lp <= rp) {
        mid = (lp + rp) >> 1;
        if (vec[mid] <= target) {
            lp = mid + 1;
            ret = mid;
        } else {
            rp = mid - 1;
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, c; cin >> n >> c;
    for (int i = 0; i < n; i++) {
        cin >> weight[i];
    }
    int mid = n / 2;
    for (int bit1 = 0; bit1 < 1 << mid; bit1++) {
        ll w = 0;
        for (int i = 0; i < mid; i++) {
            w += (bit1 & (1 << i) ? weight[i] : 0);
        }
        if (w <= c) {
            vW1.push_back(w);
        }
    }
    for (int bit2 = 0; bit2 < 1 << n; bit2 += 1 << mid) {
        ll w = 0;
        for (int i = mid; i < n; i++) {
            w += (bit2 & (1 << i) ? weight[i] : 0);
        }
        if (w <= c) {
            vW2.push_back(w);
        }
    }
    int answer = 0;
    sort(vW1.begin(), vW1.end(), less<int>());
    sort(vW2.begin(), vW2.end(), less<int>());
    int ub = binarySearch(vW1, c);
    for (int i = 0; i <= ub; i++) {
        answer += binarySearch(vW2, c-vW1[i]) + 1;
    }
    cout << answer;
}