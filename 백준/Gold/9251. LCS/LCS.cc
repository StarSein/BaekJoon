#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
using namespace std;

const int MAX_N = 1000;
vector<int> dp, tmp;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string a, b; cin >> a >> b;
    dp.resize(a.size(), 0);
    tmp.resize(a.size(), 0);
    int maxCnt = 0;
    char target;
    for (int i = 0; i < b.size(); i++) {
        target = b[i];
        maxCnt = 0;
        for (int j = 0; j < a.size(); j++) {
            if (a[j] == target) tmp[j] = max(dp[j], maxCnt + 1);
            maxCnt = max(maxCnt, dp[j]);
        }
        copy(tmp.begin(), tmp.end(), dp.begin());
    }
    cout << *max_element(dp.begin(), dp.end());
}