#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;

vector<pi> vPos;
vector<ll> vDiff;



int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    vPos.reserve(n);
    vDiff.reserve(n);
    int x, a;
    for (int i = 0; i < n; i++) {
        cin >> x >> a;
        vPos.emplace_back(x, a);
    }
    sort(vPos.begin(), vPos.end(), [](pi& a, pi& b)->bool{return a.first < b.first;});
    vDiff.resize(n, 0);
    ll prefSum = 0, suffSum = 0;
    for (int i = 1; i < n; i++) {
        prefSum += vPos[i-1].second;
        vDiff[i] = prefSum;
    }
    for (int i = n-2; i >= 0; i--) {
        suffSum += vPos[i+1].second;
        vDiff[i] = abs(suffSum - vDiff[i]);
    }
    ll bestDiff = vDiff[0], bestIdx = 0;
    for (int i = 1; i < n; i++) {
        if (vDiff[i] < bestDiff) {
            bestDiff = vDiff[i];
            bestIdx = i;
        }
    }
    cout << vPos[bestIdx].first;
}