#include <iostream>
using namespace std;


const int MAX_N = 1e4;
int A[MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M; cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> A[i];
    }

    int l = 0, r = 0, curSum = 0;
    int ans = 0;
    while (l < N) {
        if (r < N && curSum < M) {
            curSum += A[r++];
        } else {
            if (curSum == M) {
                ans++;
            }
            curSum -= A[l++];
        }
    }
    cout << ans;
    return 0;
}