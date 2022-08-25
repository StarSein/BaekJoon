#include <bits/stdc++.h>
using namespace std;

vector<int> vec;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    vec.resize(2 * n);
    for (int i = 0; i < vec.size(); i++) {
        cin >> vec[i];
    }

    sort(vec.begin(), vec.end(), less<int>());
    int ans = vec[0] + vec[vec.size()-1];
    for (int i = 1; i < n; i++) {
        ans = min(ans, vec[i] + vec[vec.size()-1-i]);
    }
    cout << ans;
    return 0;
}