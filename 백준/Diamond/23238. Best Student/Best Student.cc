#include <bits/stdc++.h>
using namespace std;

struct Query {
    int i, s, e;

    Query() = default;
    Query(int i, int s, int e): i(i), s(s), e(e) {};
};

struct Student {
    int idx, num;
};


int n, q, SQRT;
vector<Student> studVec;
vector<int> sortedVec, ansVec;
vector<Query> qVec;
unordered_map<int, int> table;

const int MAX_N = 1e5;
int cnt[MAX_N + 350], groupCnt[350], groupNum[MAX_N + 1][350];

void include(int s, int e) {
    for (int i = s; i <= e; i++) {
        int index = studVec[i].idx;
        int groupIdx = index / SQRT;
        int curCnt = cnt[index];
        int nextCnt = ++cnt[index];
        groupNum[curCnt][groupIdx]--;
        if (curCnt == groupCnt[groupIdx]) {
            groupCnt[groupIdx]++;
        }
        groupNum[nextCnt][groupIdx]++;
    }
}

void exclude(int s, int e) {
    for (int i = s; i <= e; i++) {
        int index = studVec[i].idx;
        int groupIdx = index / SQRT;
        int curCnt = cnt[index];
        int nextCnt = --cnt[index];
        groupNum[curCnt][groupIdx]--;
        if (curCnt == groupCnt[groupIdx] && groupNum[curCnt][groupIdx] == 0) {
            groupCnt[groupIdx]--;
        }
        groupNum[nextCnt][groupIdx]++;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> q;
    studVec.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> studVec[i].num;
    }

    qVec.reserve(q);
    for (int i = 0; i < q; i++) {
        int s, e; cin >> s >> e;
        qVec.emplace_back(i, s-1, e-1);
    }

    sortedVec.resize(n);
    for (int i = 0; i < studVec.size(); i++) {
        sortedVec[i] = studVec[i].num;
    }
    sort(sortedVec.begin(), sortedVec.end());
    sortedVec.erase(unique(sortedVec.begin(), sortedVec.end()), sortedVec.end());
    
    SQRT = static_cast<int>(sqrt(n));
    for (int i = 0; i < sortedVec.size(); i++) {
        table[sortedVec[i]] = i;
    }
    for (int i = 0; i < studVec.size(); i++) {
        studVec[i].idx = table[studVec[i].num];
    }

    sort(qVec.begin(), qVec.end(), [](const Query &a, const Query &b) {return a.s/SQRT != b.s/SQRT ? a.s/SQRT < b.s/SQRT : a.e < b.e;});


    ansVec.resize(q);
    int curS = 0, curE = -1;
    for (const auto &que : qVec) {
        if (que.e > curE)   include(curE + 1, que.e);
        else                exclude(que.e + 1, curE);
        if (que.s < curS)   include(que.s, curS - 1);
        else                exclude(curS, que.s - 1);

        int maxCnt = 0, maxGroup = -1;
        for (int g = size(groupCnt) - 1; g >= 0; g--) {
            if (groupCnt[g] > maxCnt) {
                maxCnt = groupCnt[g];
                maxGroup = g;
            }
        }

        int st = maxGroup * SQRT, ed = st + SQRT - 1;
        for (int index = ed; index >= st; index--) {
            if (cnt[index] == maxCnt) {
                ansVec[que.i] = sortedVec[index];
                break;
            }
        }

        curS = que.s;
        curE = que.e;
    }
    copy(ansVec.begin(), ansVec.end(), ostream_iterator<int>(cout, "\n"));
    return 0;
}