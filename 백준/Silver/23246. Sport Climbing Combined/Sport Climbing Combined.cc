#include <bits/stdc++.h>
using namespace std;
typedef tuple<int, int, int> ti;

const int MAX_N = 100;
ti arr[MAX_N];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        int id, p, q, r; cin >> id >> p >> q >> r;
        arr[i] = {p * q * r, p + q + r, id};
    }
    sort(arr, arr + n);
    for (int i = 0; i < 3; i++) {
        cout << get<2>(arr[i]) << ' ';
    }
    return 0;
}