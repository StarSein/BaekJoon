#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <iterator>
using namespace std;
typedef long long ll;

vector<int> vec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K, T; cin >> N >> K >> T;
    
    ll aSum = 0;
    vec.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> vec[i];
    }
    sort(vec.begin(), vec.end());
    int l = 0, r = vec.size() - 1;
    int cnt = 0;
    bool ans = true;
    while (l < r) {
        if (vec[l] == 0) {
            l++;
            continue;
        }

        if (vec[l] + vec[r] == K) {
            cnt += vec[l];
            vec[l] = 0;
            vec[r] = 0;
            l++;
            r--;
        } else if (vec[l] + vec[r] < K) {
            cnt += vec[l];
            vec[r] += vec[l];
            vec[l] = 0;
            l++;
        } else {
            cnt += K - vec[r];
            vec[l] += vec[r] - K;
            vec[r] = 0;
            r--;
        }

        if (cnt > T) {
            ans = false;
            break;
        }
    }
    if (accumulate(vec.begin(), vec.end(), 0)) {
        ans = false;
    }
    cout << (ans ? "YES" : "NO");
    return 0;
}