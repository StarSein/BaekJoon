#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

int m, n;
vector<int> p, sp;
vector<int> s, ss;
vector<int> tree;
vector<pi> ranks;
vector<int> fails;
vector<int> answers;
void compressCoord(vector<int>& v, vector<int>& sv) {
    sv.reserve(v.size());
    copy(v.begin(), v.end(), back_inserter(sv));
    sort(sv.begin(), sv.end());
    sv.erase(unique(sv.begin(), sv.end()), sv.end());
    for (int & e : v) {
        e = lower_bound(sv.begin(), sv.end(), e) - sv.begin() + 1;
    }
}

void update(int x, int v) {
    while (x < tree.size()) {
        tree[x] += v;
        x += x & -x;
    }
}

int sum(int x) {
    int ret = 0;
    while (x) {
        ret += tree[x];
        x -= x & -x;
    }
    return ret;
}

void makeRanks() {
    tree.resize(sp.size() + 1, 0);
    ranks.resize(m);
    for (int i = 0; i < m; i++) {
        update(p[i], 1);
        ranks[i] = make_pair(sum(p[i] - 1), sum(p[i]));
    }
}

void makeFails() {
    tree.clear();
    tree.resize(sp.size() + 1, 0);
    fails.resize(m);
    int j = 0;
    for (int i = 1; i < m; i++) {
        update(p[i], 1);
        while (j && make_pair(sum(p[i] - 1), sum(p[i])) != ranks[j]) {
            const int nj = fails[j - 1];
            while (j > nj) {
                update(p[i - j], -1);
                j--;
            }
        }
        fails[i] = ++j;
    }
}

void kmp() {
    tree.clear();
    tree.resize(ss.size() + 1, 0);
    int j = 0;
    for (int i = 0; i < n; i++) {
        update(s[i], 1);
        while (j && make_pair(sum(s[i] - 1), sum(s[i])) != ranks[j]) {
            const int nj = fails[j - 1];
            while (j > nj) {
                update(s[i - j], -1);
                j--;
            }
        }
        if (j == m - 1) {
            answers.push_back(i - j + 1);
            const int nj = fails[j];
            while (j > nj) {
                update(s[i - j], -1);
                j--;
            }
            update(s[i - j], -1);
        } else {
            ++j;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> m >> n;
    p.resize(m);
    for (int i = 0; i < m; i++) {
        cin >> p[i];
    }
    s.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> s[i];
    }
    
    compressCoord(p, sp);
    compressCoord(s, ss);

    makeRanks();
    makeFails();
    kmp();    

    if (answers.size()) {
        copy(answers.begin(), answers.end(), ostream_iterator<int>(cout, " "));
    } else {
        cout << 0;
    }
    return 0;
}