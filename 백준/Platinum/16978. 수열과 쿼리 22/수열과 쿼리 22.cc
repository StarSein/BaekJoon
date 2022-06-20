#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <iterator>

using namespace std;
typedef long long ll;
typedef tuple<int, int, int, int, int> t5i;


int n, firstLeaf;
const int MAX_N = 1e5+1;
ll arr[MAX_N], seg[4*MAX_N];

vector<t5i> vQuery;
vector<ll> vAnswer;


void makeSegTree() {
    for (int i = 1; i <= n; i++) {
        seg[firstLeaf+i-1] = arr[i];
    }

    for (int s = firstLeaf >> 1; s >= 1; s >>= 1) {
        for (int node = s; node < 2*s; node++) {
            seg[node] = seg[2*node] + seg[2*node+1];
        } 
    }
}

void update(int i, int v) {
    int node = firstLeaf + i - 1;
    seg[node] = v;

    for (node >>= 1; node >= 1; node >>= 1) {
        seg[node] = seg[2*node] + seg[2*node+1];
    }
}

ll query(int l, int r) {
    int lp = firstLeaf + l - 1;
    int rp = firstLeaf + r - 1;
    ll ret = 0;
    while (lp <= rp) {
        if (lp & 1) {
            ret += seg[lp];
            lp++;
        }
        if (!(rp & 1)) {
            ret += seg[rp];
            rp--;
        }
        lp >>= 1;
        rp >>= 1;
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }
    firstLeaf = 1 << static_cast<int>(ceil(log2(n)));
    makeSegTree();

    int m; cin >> m;
    vQuery.reserve(m);
    int type, i, j, v, k;
    int cnt1 = 1, cnt2 = 0;
    for (int q = 0; q < m; q++) {
        cin >> type;
        if (type == 1) {
            cin >> i >> v;
            vQuery.emplace_back(cnt1++, type, i, v, 0);
        } else {
            cin >> k >> i >> j;
            vQuery.emplace_back(k, type, i, j, cnt2++);
        }
    }
    sort(vQuery.begin(), vQuery.end(), [](t5i& a, t5i& b)->bool{
        return get<0>(a) == get<0>(b) ? get<1>(a) < get<1>(b) : get<0>(a) < get<0>(b);
        });
        
    vAnswer.resize(cnt2, 0);
    int x;
    for (auto& e : vQuery) {
        if (get<1>(e) == 1) {
            update(get<2>(e), get<3>(e));
        } else {
            vAnswer[get<4>(e)] = query(get<2>(e), get<3>(e));
        }
    }
    copy(vAnswer.begin(), vAnswer.end(), ostream_iterator<ll>(cout, "\n"));
}