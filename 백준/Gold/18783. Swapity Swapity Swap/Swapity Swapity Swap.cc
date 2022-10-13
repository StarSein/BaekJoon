#include <iostream>
#include <algorithm>
using namespace std;


const int MAX_N = 1e5 + 1;
const int LOG = 30;
int arr[MAX_N], table[MAX_N][LOG];

int getLabel(int node, int k) {
    for (int i = LOG - 1, bit = 1 << i; bit > 0; i--, bit >>= 1) {
        if (bit & k) {
            node = table[node][i];
        }
    }
    return node;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, M, K; cin >> N >> M >> K;
    for (int i = 1; i <= N; i++) {
        arr[i] = i;
    }

    for (int i = 0; i < M; i++) {
        int l, r; cin >> l >> r;
        reverse(arr + l, arr + r + 1);
    }
    for (int i = 1; i <= N; i++) {
        table[i][0] = arr[i];
    }

    for (int i = 1; i < LOG; i++) {
        for (int j = 1; j <= N; j++) {
            table[j][i] = table[table[j][i-1]][i-1];
        }
    }

    for (int i = 1; i <= N; i++) {
        cout << getLabel(i, K) << '\n';
    }
    return 0;
}