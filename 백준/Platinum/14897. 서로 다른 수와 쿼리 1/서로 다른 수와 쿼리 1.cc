#pragma GCC optimize("03")
#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <cmath>
#include <iterator>
using namespace std;

struct Query {
    int id, l, r;
};

const int MAX_N = 1e6 + 1;
vector<int> A, B;
int cnt[MAX_N], ans[MAX_N];
Query qArr[MAX_N];
unordered_map<int, int> table;
int curAns;

void include(int s, int e) {
    for (int i = s; i <= e; i++) {
        cnt[A[i]]++;
        if (cnt[A[i]] == 1) {
            curAns++;
        }
    }
}

void exclude(int s, int e) {
    for (int i = s; i <= e; i++) {
        cnt[A[i]]--;
        if (cnt[A[i]] == 0) {
            curAns--;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    A.resize(N + 1);
    B.resize(N + 1);
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
        B[i] = A[i];
    }

    int Q; cin >> Q;
    for (int i = 0; i < Q; i++) {
        int l, r; cin >> l >> r;
        qArr[i] = {i, l, r};
    }

    sort(B.begin(), B.end());
    B.erase(unique(B.begin(), B.end()), B.end());
    for (int i = 0; i < B.size(); i++) {
        table[B[i]] = i;
    }
    for (int i = 0; i < A.size(); i++) {
        A[i] = table[A[i]];
    }

    const int SQRT = sqrt(N);
    sort(qArr, qArr + Q, [&](Query &a, Query &b) {return a.l/SQRT != b.l/SQRT ? a.l/SQRT < b.l/SQRT : a.r < b.r;});
    int curL = 1, curR = 0;
    for (int i = 0; i < Q; i++) {
        Query &q = qArr[i];
        if (q.r > curR) include(curR + 1, q.r);
        else            exclude(q.r + 1, curR);
        if (curL > q.l) include(q.l, curL - 1);
        else            exclude(curL, q.l - 1);
    
        ans[q.id] = curAns;

        curL = q.l;
        curR = q.r;
    }
    copy(ans, ans + Q, ostream_iterator<int>(cout, "\n"));
    return 0;
}