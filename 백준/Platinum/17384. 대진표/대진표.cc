#include <bits/stdc++.h>
using namespace std;

vector<char> ans;

void deploy(int pos, int num) {
    if (num == 1) {
        ans[pos] = '#';
        return;
    }
    int LOG = pow(2, ceil(log2(num)));
    int left = (LOG >> 1);
    while (floor(log2(left)) > floor(log2(num-left)) + 1) {
        left >>= 1;
    }
    int right = num-left;
    if (left < right) swap(left, right);
    deploy(pos, left);
    deploy(pos + (LOG >> 1), right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int n; cin >> n;
    int LOG = pow(2, ceil(log2(n)));
    ans.resize(LOG, '.');
    deploy(0, n);
    copy(ans.begin(), ans.end(), ostream_iterator<char>(cout, ""));
}