#include <iostream>
#include <cstring>
using namespace std;

const int MAX_N = 1001;
int N;
int arr[MAX_N];
int sum[MAX_N];
int dp[MAX_N][MAX_N];

int score(int l, int r) {
    int &ret = dp[l][r];
    if (ret == 0) {
        if (l == r) {
            ret = arr[l];
        }
        else {
            ret = max(arr[l] + sum[r] - sum[l] - score(l + 1, r),
                    arr[r] + sum[r - 1] - sum[l - 1] - score(l, r - 1));
        }
    }
    return ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T; cin >> T;
    while (T--) {
        memset(arr, 0, sizeof(arr));
        memset(sum, 0, sizeof(sum));
        memset(dp, 0, sizeof(dp));
        cin >> N;
        for (int i = 1; i <= N; i++) {
            cin >> arr[i];
        }

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        cout << score(1, N) << '\n';
    }
    return 0;
}