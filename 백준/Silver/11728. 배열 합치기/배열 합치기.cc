#include <iostream>
#include <algorithm>
#include <iterator>
using namespace std;

const int SZ = 1e6;

int A[SZ], B[SZ], C[2*SZ];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m; cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> A[i];
    }
    for (int i = 0; i < m; i++) {
        cin >> B[i];
    }
    merge(A, A + n, B, B + m, C);
    copy(C, C + n + m, ostream_iterator<int>(cout, " "));
}