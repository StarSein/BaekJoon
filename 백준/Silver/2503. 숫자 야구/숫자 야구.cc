#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
using namespace std;
typedef tuple<vector<int>, int, int> tup;

vector<tup> query;

bool isAble(const vector<int> &vec) {
    vector<int> num; int s, b;
    for (auto &e : query) {
        tie(num, s, b) = e;

        int cntS = 0, cntB = 0;
        for (int i = 0; i < 3; i++) {
            if (num[i] == vec[i]) {
                cntS++;
            } else if (find(vec.begin(), vec.end(), num[i]) != vec.end()) {
                cntB++;
            }
        }
        if (cntS != s || cntB != b) {
            return false;
        }
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    query.reserve(N);
    for (int i = 0; i < N; i++) {
        string num; int s, b; cin >> num >> s >> b;

        vector<int> vec(3);
        for (int j = 0; j < 3; j++) {
            vec[j] = num[j] - '0';
        }
        query.emplace_back(vec, s, b);
    }
    int ans = 0;
    for (int i = 1; i <= 9; i++) {
        for (int j = 1; j <= 9; j++) {
            for (int k = 1; k <= 9; k++) {
                if (i != j && j != k && i != k) {
                    if (isAble({i, j, k})) {
                        ans++;
                    }
                }
            }
        }
    }
    cout << ans;
}