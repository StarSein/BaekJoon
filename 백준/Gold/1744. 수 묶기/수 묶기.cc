#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 50;
int arr[MAX_N];
bool check[MAX_N];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    if (n == 1) {
        cout << arr[0];
        return 0;
    }
    sort(arr, arr + n, [](int &a, int &b) {return a > b;});
    int ans = 0;
    for (int i = 0; i < n - 1; i += 2) {
        if (arr[i] > 1 && arr[i+1] > 1) {
            ans += (arr[i] * arr[i+1]);
            check[i] = true;
            check[i+1] = true;
        }
    }
    for (int i = n - 1; i > 0; i -= 2) {
        if (arr[i] <= 0 && arr[i-1] <= 0) {
            ans += (arr[i] * arr[i-1]);
            check[i] = true;
            check[i-1] = true;
        }
    }
    for (int i = 0; i < n; i++) {
        if (!check[i]) {
            ans += arr[i];
        }
    }
    cout << ans;
}