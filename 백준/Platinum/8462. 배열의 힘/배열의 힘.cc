#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <cmath>
using namespace std;
typedef long long ll;

struct Query {
    int q, s, e;
};

const int MAX_N = 1e5, MAX_A = 1e6;
ll a[MAX_N + 1], cnt[MAX_A + 1];
vector<Query> qVec;
vector<ll> ansVec;
ll curPower;


void include(int start, int end) {
    for (int i = start; i <= end; i++) {
        ll curNum = a[i];
        ll &curCnt = cnt[curNum];
        curPower -= curCnt * curCnt * curNum;
        curCnt++;
        curPower += curCnt * curCnt * curNum;
    }
}

void exclude(int start, int end) {
    for (int i = start; i <= end; i++) {
        ll curNum = a[i];
        ll &curCnt = cnt[curNum];
        curPower -= curCnt * curCnt * curNum;
        curCnt--;
        curPower += curCnt * curCnt * curNum;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, t; cin >> n >> t;
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    qVec.reserve(t);
    for (int i = 0; i < t; i++) {
        int l, r; cin >> l >> r;
        qVec.push_back({i, l, r});
    }

    const int SQRT = static_cast<int>(sqrt(n));
    sort(qVec.begin(), qVec.end(), [&](Query &a, Query &b) {return a.s/SQRT != b.s/SQRT ? a.s/SQRT < b.s/SQRT : a.e < b.e;});
    
    ansVec.resize(t);
    int curS = 0, curE = -1;
    for (Query &x : qVec) {
        if (curE < x.e) include(curE + 1, x.e);
        else            exclude(x.e + 1, curE);
        if (x.s < curS) include(x.s, curS - 1);
        else            exclude(curS, x.s - 1);

        curS = x.s;
        curE = x.e;
        ansVec[x.q] = curPower;
    }

    copy(ansVec.begin(), ansVec.end(), ostream_iterator<ll>(cout, "\n"));
    return 0;
}