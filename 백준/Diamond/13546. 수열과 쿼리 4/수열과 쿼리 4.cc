#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <iterator>
#include <list>
using namespace std;

struct Query {
    int q, s, e;

    Query() = default;
    Query(int q, int s, int e): q(q), s(s), e(e) {};
};

const int MAX_N = 1e5, MAX_K = 1e5;
int A[MAX_N + 1], diffCnt[MAX_K + 1001];
list<int> numList[MAX_K + 1];
vector<Query> qVec;
vector<int> groupCnt, ansVec;

int SQRT;

void include(int i, bool fromBack) {
    int curNum = A[i];
    list<int> &curList = numList[curNum];
    if (!curList.empty()) {
        int diff = curList.back() - curList.front();
        diffCnt[diff]--;
        groupCnt[diff/SQRT]--;
    }
    if (fromBack)   curList.push_back(i);
    else            curList.push_front(i);
    int diff = curList.back() - curList.front();
    diffCnt[diff]++;
    groupCnt[diff/SQRT]++;
}

void exclude(int i, bool fromBack) {
    int curNum = A[i];
    list<int> &curList = numList[curNum];
    int diff = curList.back() - curList.front();
    diffCnt[diff]--;
    groupCnt[diff/SQRT]--;
    if (fromBack)   curList.pop_back();
    else            curList.pop_front();
    if (!curList.empty()) {
        int diff = curList.back() - curList.front();
        diffCnt[diff]++;
        groupCnt[diff/SQRT]++;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, K; cin >> N >> K;
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }
    int M; cin >> M;
    qVec.reserve(M);
    for (int i = 0; i < M; i++) {
        int l, r; cin >> l >> r;
        qVec.emplace_back(i, l, r);
    }
    SQRT = static_cast<int>(sqrt(N));

    sort(qVec.begin(), qVec.end(), [](Query &a, Query &b) {return a.s/SQRT != b.s/SQRT ? a.s/SQRT < b.s/SQRT : a.e < b.e;});

    ansVec.resize(M);
    groupCnt.resize(N / SQRT + 1, 0);
    int curS = 1, curE = 0;
    for (Query &x : qVec) {
        while (curE < x.e) include(++curE, true);
        while (curE > x.e) exclude(curE--, true);
        while (curS > x.s) include(--curS, false);
        while (curS < x.s) exclude(curS++, false);

        for (int group = groupCnt.size() - 1; group >= 0; group--) {
            if (groupCnt[group]) {
                int upper = (group + 1) * SQRT - 1;
                int lower = group * SQRT;
                for (int diff = upper; diff >= lower; diff--) {
                    if (diffCnt[diff]) {
                        ansVec[x.q] = diff;
                        break;
                    }
                }
                break;
            }
        }
    }
    copy(ansVec.begin(), ansVec.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}