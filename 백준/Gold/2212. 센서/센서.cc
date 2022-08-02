#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> vPos, vDist;

int main() {
    ios_base::sync_with_stdio(false);

    int N, K; cin >> N >> K;
    vPos.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> vPos[i];
    }
    
    sort(vPos.begin(), vPos.end(), [](int &a, int &b) {return a < b;});
    vPos.erase(unique(vPos.begin(), vPos.end()), vPos.end());
    vDist.reserve(vPos.size()-1);
    for (int i = 1; i < vPos.size(); i++) {
        vDist.push_back(vPos[i] - vPos[i-1]);
    }
    sort(vDist.begin(), vDist.end(), [](int &a, int &b) {return a > b;});
    int ans = 0;
    for (int i = K - 1; i < vDist.size(); i++) {
        ans += vDist[i];
    }
    cout << ans;
}