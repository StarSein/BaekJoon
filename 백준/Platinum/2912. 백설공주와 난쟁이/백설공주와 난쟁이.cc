#include <iostream>
#include <unordered_set>
#include <algorithm>
#include <vector>
#include <iterator>
#include <cmath>
using namespace std;
typedef pair<int, int> pi;

struct Query {
    int q, s, e;
};

const int MAX_N = 3e5, MAX_C = 1e5;
int hat[MAX_N + 1], cnt[MAX_C + 1];
vector<Query> qVec;
vector<pi> ansVec;
int SQRT;

void include(int start, int end) {
    for (int i = start; i <= end; i++) {
        cnt[hat[i]]++;
    }
}

void exclude(int start, int end) {
    for (int i = start; i <= end; i++) {
        cnt[hat[i]]--;
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int N, C; cin >> N >> C;
    for (int i = 1; i <= N; i++) {
        cin >> hat[i];
    }
    int M; cin >> M;
    qVec.reserve(M);
    for (int i = 0; i < M; i++) {
        int A, B; cin >> A >> B;
        qVec.push_back({i, A, B});
    }
    SQRT = sqrt(N);
    sort(qVec.begin(), qVec.end(), [](Query &a, Query &b) {return (a.s/SQRT != b.s/SQRT ? a.s/SQRT < b.s/SQRT : a.e < b.e);});

    ansVec.resize(M);
    int curS = 0, curE = -1;
    for (Query &x : qVec) {
        if (curE < x.e) include(curE + 1, x.e);
        else            exclude(x.e + 1, curE);
        if (x.s < curS) include(x.s, curS - 1);
        else            exclude(curS, x.s - 1);

        curS = x.s;
        curE = x.e;
        int half = (curE - curS + 1) / 2;
        bool pretty = false;
        for (int color = 1; color <= C; color++) {
            if (cnt[color] > half) {
                ansVec[x.q] = {1, color};
                pretty = true;
                break;
            }
        }
        if (!pretty) {
            ansVec[x.q] = {0, 0};
        }
    }

    for (auto &[pretty, color] : ansVec) {
        if (pretty) {
            cout << "yes " << color << '\n';
        } else {
            cout << "no\n";
        }
    }
    return 0;
}