#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;
typedef long long ll;

int N, M, K;
vector<pi> vScore;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> N >> M >> K;
    vScore.reserve(N);
    for (int i = 0; i < N; i++) {
        int a, b; cin >> a >> b;
        vScore.emplace_back(a, b);
    }
    sort(vScore.begin(), vScore.end(), [](pi &x, pi &y) {return x.second > y.second;});
    ll ans = 0;
    for (int i = 0; i < K; i++) {
        ans += vScore[i].first;
    }
    sort(next(vScore.begin(), K), vScore.end(), [](pi &x, pi &y) {return x.first > y.first;});
    for (int i = K; i < M + K; i++) {
        ans += vScore[i].first;
    }
    cout << ans;
}