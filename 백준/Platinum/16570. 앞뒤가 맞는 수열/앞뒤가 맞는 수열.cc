#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> A;
vector<int> fails;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    A.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }

    reverse(A.begin(), A.end());

    fails.resize(N);
    int j = 0;
    for (int i = 1; i < N; i++) {
        while (j && A[i] != A[j]) {
            j = fails[j - 1];
        }
        if (A[i] == A[j]) {
            fails[i] = ++j;
        }
    }

    int k = 0;
    int cnt = 0;
    for (int i = 0; i < N; i++) {
        if (fails[i] > k) {
            k = fails[i];
            cnt = 1;
        } else if (fails[i] == k) {
            cnt++;
        }
    }
    if (k) {
        cout << k << ' ' << cnt;
    } else {
        cout << -1;
    }
    return 0;
}