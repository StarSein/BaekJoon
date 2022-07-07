#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <cmath>
using namespace std;

vector<int> vNum;
vector<int> ans;

int gcd(int a, int b) {
    int tmp;
    while (b) {
        tmp = a;
        a = b;
        b = tmp % b;
    }
    return a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n; cin >> n;
    vNum.resize(n, 0);
    for (int i = 0; i < n; i++) {
        cin >> vNum[i];
    }
    int g = abs(vNum[0] - vNum[1]);
    for (int i = 0; i < n-1; i++) {
        for (int j = i+1; j < n; j++) {
            g = gcd(g, abs(vNum[i] - vNum[j]));
        }
    }
    for (int i = 1; i <= sqrt(g); i++) {
        if (g % i == 0) {
            ans.push_back(i);
            ans.push_back(g/i);
        }
    }
    sort(ans.begin(), ans.end(), less<int>());
    ans.erase(unique(ans.begin(), ans.end()), ans.end());
    copy(next(ans.begin(), 1), ans.end(), ostream_iterator<int>(cout, " "));
}