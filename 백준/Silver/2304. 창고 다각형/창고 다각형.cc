#include <iostream>
#include <stack>
#include <algorithm>
#include <vector>
using namespace std;
typedef pair<int, int> pi;

vector<pi> vColumn;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    vColumn.reserve(N);
    for (int i = 0; i < N; i++) {
        int L, H; cin >> L >> H;
        vColumn.emplace_back(L, H);
    }

    sort(vColumn.begin(), vColumn.end(), [](pi &a, pi &b) {return a.first < b.first;});
    
    int maxH = 0, midPos = -1;
    for (int i = 0; i < vColumn.size(); i++) {
        if (vColumn[i].second > maxH) {
            maxH = vColumn[i].second;
            midPos = i;
        }
    }

    int ans = maxH;
    int prevL = 0, prevH = 0;
    for (int i = 0; i <= midPos; i++) {
        ans += prevH * (vColumn[i].first - prevL);
        prevL = vColumn[i].first;
        prevH = max(prevH, vColumn[i].second);
    }
    prevL = 1001, prevH = 0;
    for (int i = vColumn.size() - 1; i >= midPos; i--) {
        ans += prevH * (prevL - vColumn[i].first);
        prevL = vColumn[i].first;
        prevH = max(prevH, vColumn[i].second);
    }
    cout << ans;
    return 0;
}