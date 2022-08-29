#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <iterator>
using namespace std;
typedef pair<int, int> pi;
typedef tuple<int, int, int> ti;

const int INF = 1e9;

vector<pi> vCoord;
vector<vector<ti>> dp;

int ans1 = INF;
vector<int> ans2;

int getDist(pi &p1, pi &p2) {
    return abs(p1.first - p2.first) + abs(p1.second - p2.second);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    int W; cin >> W;
    vCoord.reserve(W + 2);
    dp.resize(W + 2, vector<ti>(W + 2, make_tuple(INF, 0, 0)));

    vCoord.emplace_back(1, 1);
    vCoord.emplace_back(N, N);
    for (int i = 0; i < W; i++) {
        int r, c; cin >> r >> c;
        vCoord.emplace_back(r, c);
    }
    /*
    dp[r][c]: r번째 점에 출동. c번째 점에 다른 경찰차를 두고 있음.
    first: c번째 점에 경찰차를 두고 r번째 점에 다른 경찰차가 출동하는 경우 중 최단 거리
    second: r번째 점에 출동한 경찰차의 번호
    third: r-1번 행의 몇 번째 열에서 최적해를 가져온 것인가에 관한 정보
    */
    dp[2][0] = {getDist(vCoord[1], vCoord[2]), 2, -1};
    dp[2][1] = {getDist(vCoord[0], vCoord[2]), 1, -1};
    for (int i = 3; i < W + 2; i++) {
        for (int j = 0; j < W + 2; j++) {
            if (get<0>(dp[i-1][j]) != INF) {
                int val1 = get<0>(dp[i-1][j]) + getDist(vCoord[i-1], vCoord[i]);
                if (val1 < get<0>(dp[i][j])) {
                    dp[i][j] = {val1, get<1>(dp[i-1][j]), j};
                }
                int val2 = get<0>(dp[i-1][j]) + getDist(vCoord[j], vCoord[i]);
                if (val2 < get<0>(dp[i][i-1])) {
                    dp[i][i-1] = {val2, 3 - get<1>(dp[i-1][j]), j};
                }
            }
        }
    }

    for (int j = 0; j < W + 2; j++) {
        ans1 = min(ans1, get<0>(dp[W + 1][j]));
    }

    ans2.resize(W + 2);
    for (int j = 0; j < W + 2; j++) {
        if (get<0>(dp[W + 1][j]) == ans1) {
            int r = W + 1;
            int c = j;
            while (r > 1) {
                ans2[r] = get<1>(dp[r][c]);
                c = get<2>(dp[r][c]);
                r--;
            }
        }
    }

    cout << ans1 << '\n';
    copy(next(ans2.begin(), 2), ans2.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}