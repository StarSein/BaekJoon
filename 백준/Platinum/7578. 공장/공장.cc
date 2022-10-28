#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;


const int SZ = 1 << 19;
const int MAX_ID = 1e6 + 1;
int tree[SZ];

int N;
int pos[MAX_ID];
vector<int> qVec;

void update(int i) {
    while (i <= N) {
        tree[i]++;
        i += i & -i;
    }
}

int query(int i) {
    int ret = 0;
    while (i) {
        ret += tree[i];
        i -= i & -i;
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    qVec.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> qVec[i];
    }
    for (int i = 1; i <= N; i++) {
        int id; cin >> id;
        pos[id] = i;
    }

    ll ans = 0;
    for (const int &q: qVec) {
        int p = pos[q];
        ans += query(N) - query(p);
        update(p);
    }
    cout << ans;
    return 0;
}