#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <iterator>
using namespace std;

typedef struct Query {
    int s, e, q;
} Query;

const int MAX_N = 1e5, MAX_NUM = 1e6;
int A[MAX_N + 1], cnt[MAX_NUM + 1];
vector<Query> qVec;
vector<int> ansVec;
int SQRT;
int uniqueCnt = 0;

void include(int start, int end) {
    for (int i = start; i <= end; i++) {
        cnt[A[i]]++;
        if (cnt[A[i]] == 1) uniqueCnt++;
    }
}

void exclude(int start, int end) {
    for (int i = start; i <= end; i++) {
        cnt[A[i]]--;
        if (cnt[A[i]] == 0) uniqueCnt--;
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }
    int M; cin >> M;
    qVec.reserve(M);
    for (int i = 0; i < M; i++) {
        int s, e; cin >> s >> e;
        qVec.push_back({s, e, i});
    }

    SQRT = static_cast<int>(sqrt(N));
    sort(qVec.begin(), qVec.end(), [](Query &a, Query &b) {return (a.s/SQRT != b.s/SQRT ? a.s/SQRT < b.s/SQRT : a.e < b.e);});
    
    int curS = 0, curE = -1;
    ansVec.resize(M);
    for (auto &query : qVec) {
        if (curE < query.e) {
            include(curE + 1, query.e);
        } else {
            exclude(query.e + 1, curE);
        }

        if (query.s < curS) {
            include(query.s, curS - 1);
        } else {
            exclude(curS, query.s - 1);
        }

        curS = query.s;
        curE = query.e;
        ansVec[query.q] = uniqueCnt;
    }
    copy(ansVec.begin(), ansVec.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}