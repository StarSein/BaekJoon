#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 1e5;

int arr[3], maxi[3], mini[3], tmp[3];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 3; j++) {
            cin >> arr[j];
        }
        tmp[0] = *max_element(maxi, maxi + 2) + arr[0];
        tmp[1] = *max_element(maxi, maxi + 3) + arr[1];
        tmp[2] = *max_element(maxi + 1, maxi + 3) + arr[2];
        copy(tmp, tmp + 3, maxi);

        tmp[0] = *min_element(mini, mini + 2) + arr[0];
        tmp[1] = *min_element(mini, mini + 3) + arr[1];
        tmp[2] = *min_element(mini + 1, mini + 3) + arr[2];
        copy(tmp, tmp + 3, mini);
    }
    cout << *max_element(maxi, maxi + 3) << ' ' << *min_element(mini, mini + 3);
    
}