#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <iterator>
using namespace std;
typedef struct Query {
    int q, s, e;
} Query;

const int MAX_N = 1e5, MAX_A = 1e5;
int A[MAX_N + 1], cnt[MAX_A + 1], num[MAX_N + 1];
vector<Query> qVec;
vector<int> ansVec;
int SQRT, curAns = 0;


void include(int start, int end) {
    for (int i = start; i <= end; i++) {
        int curCnt = cnt[A[i]];
        int nextCnt = ++cnt[A[i]];
        num[curCnt]--;
        num[nextCnt]++;
        if (curCnt == curAns) {
            curAns++; 
        }
    }
}

void exclude(int start, int end) {
    for (int i = start; i <= end; i++) {
        int curCnt = cnt[A[i]];
        int nextCnt = --cnt[A[i]];
        num[curCnt]--;
        num[nextCnt]++;
        if (curCnt == curAns && num[curCnt] == 0) {
            curAns--;
        }
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
        qVec.push_back({i, s, e});
    }
    ansVec.resize(M);

    SQRT = static_cast<int>(sqrt(N));
    sort(qVec.begin(), qVec.end(), [](Query &a, Query &b) {return (a.s / SQRT != b.s / SQRT ? a.s / SQRT < b.s / SQRT : a.e < b.e);});

    num[0] = MAX_N;

    int curL = 0, curR = -1;
    for (Query &x : qVec) {
        if (x.e > curR) include(curR + 1, x.e);
        else            exclude(x.e + 1, curR);
        if (x.s < curL) include(x.s, curL - 1);
        else            exclude(curL, x.s - 1);

        curL = x.s;
        curR = x.e;
        ansVec[x.q] = curAns;
    }
    copy(ansVec.begin(), ansVec.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}