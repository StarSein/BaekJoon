#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pi;

int n, k;
vector<int> a, sa;
vector<int> b, sb;
vector<int> tree;
vector<pi> ranks;
vector<int> fails;
int ans = 0;
void compactCoord(vector<int> &v, vector<int> &sv) {
    sv.reserve(v.size());
    copy(v.begin(), v.end(), back_inserter(sv));
    sort(sv.begin(), sv.end());
    sv.erase(unique(sv.begin(), sv.end()), sv.end());
    for (int &e : v) {
        e = lower_bound(sv.begin(), sv.end(), e) - sv.begin() + 1;
    }
}

void update(int x, int v) {
    for (; x < tree.size(); x += x & -x) {
        tree[x] += v;
    }
}

int sum(int x) {
    int ret = 0;
    for (; x > 0; x -= x & -x) {
        ret += tree[x];
    }
    return ret;
}

void makeRanks() {
    tree.resize(sb.size() + 1);
    ranks.resize(b.size());
    for (int i = 0; i < b.size(); i++) {
        update(b[i], 1);
        ranks[i] = make_pair(sum(b[i] - 1), sum(b[i]));
    }
}

void makeFails() {
    tree.clear();
    tree.resize(sb.size() + 1);
    fails.resize(b.size());
    int j = 0;
    for (int i = 1; i < b.size(); i++) {
        update(b[i], 1);
        while (j && make_pair(sum(b[i] - 1), sum(b[i])) != ranks[j]) {
            const int nj = fails[j - 1];
            while (j > nj) {
                update(b[i - j], -1);
                j--;
            }
        }
        fails[i] = ++j;
    }
}

void kmp() {
    tree.clear();
    tree.resize(sa.size() + 1);
    int j = 0;
    for (int i = 0; i < a.size(); i++) {
        update(a[i], 1);
        while (j && make_pair(sum(a[i] - 1), sum(a[i])) != ranks[j]) {
            const int nj = fails[j - 1];
            while (j > nj) {
                update(a[i - j], -1);
                j--;
            }
        }
        if (j == k - 1) {
            ans++;
            const int nj = fails[j];
            while (j > nj) {
                update(a[i - j], -1);
                j--;
            }
            update(a[i - j], -1);
        } else {
            ++j;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> k;
    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    b.resize(k);
    for (int i = 0; i < k; i++) {
        cin >> b[i];
    }
    a.reserve(n + k - 1);
    copy(a.begin(), next(a.begin(), k - 1), back_inserter(a));

    compactCoord(a, sa);
    compactCoord(b, sb);

    makeRanks();
    makeFails();
    kmp();
    cout << ans;
    return 0;
}