#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 2e5;
int N, C;
int X[MAX_N];

bool pSearch(int dist) {
    int prevPos = X[0];
    int cnt = 1;
    for (int i = 1; i < N && cnt < C; i++) {
        if (X[i] - prevPos >= dist) {
            prevPos = X[i];
            cnt++;
        }
    }
    return (cnt == C);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> C;
    for (int i = 0; i < N; i++) {
        cin >> X[i];
    }
    sort(X, X + N, [](int &a, int &b) {return a < b;});
    int left = 1, right = 1e9;
    int ans;
    while (left <= right) {
        int mid = (left + right) >> 1;
        if (pSearch(mid)) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    cout << ans;
}