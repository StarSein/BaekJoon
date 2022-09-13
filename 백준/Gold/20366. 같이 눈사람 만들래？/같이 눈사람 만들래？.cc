#include <iostream>
#include <vector>
#include <algorithm>
#include <tuple>
#include <unordered_set>
using namespace std;
typedef tuple<int, int, int> ti;

const int INF = 2e9;
vector<int> hVec;
vector<ti> tVec;


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int N; cin >> N;
    hVec.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> hVec[i];
    }

    tVec.reserve(N * (N - 1) / 2);
    for (int i = 0; i < N - 1; i++) {
        for (int j = i + 1; j < N; j++) {
            tVec.emplace_back(hVec[i] + hVec[j], i, j);
        }
    }

    sort(tVec.begin(), tVec.end());

    int ans = INF;
    for (int i = 0; i < tVec.size() - 1; i++) {
        unordered_set<int> s;
        s.insert(get<1>(tVec[i]));
        s.insert(get<2>(tVec[i]));
        s.insert(get<1>(tVec[i+1]));
        s.insert(get<2>(tVec[i+1]));
        if (s.size() == 4) {
            ans = min(ans, get<0>(tVec[i+1]) - get<0>(tVec[i]));
        }
    }
    cout << ans;
    return 0;
}