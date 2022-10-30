#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <vector>
#include <cstring>
using namespace std;
typedef long long ll;

struct Point {
    int x, y;
};


const int MAX_N = 75000;
int n;
Point arr[MAX_N];
int tree[MAX_N + 1];

void update(int i) {
    while (i <= n) {
        tree[i]++;
        i += (i & -i);
    }
}

int query(int i) {
    int ret = 0;
    while (i) {
        ret += tree[i];
        i -= (i & -i);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T; cin >> T;
    while (T--) {
        memset(tree, 0, sizeof(tree));

        cin >> n;
        vector<int> yVec(n);
        unordered_map<int, int> table;
        for (int i = 0; i < n; i++) {
            cin >> arr[i].x >> arr[i].y;
            yVec[i] = arr[i].y;
        }

        sort(yVec.begin(), yVec.end(), greater<int>());
        
        yVec.erase(unique(yVec.begin(), yVec.end()), yVec.end());
        for (int i = 0; i < yVec.size(); i++) {
            table[yVec[i]] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            arr[i].y = table[arr[i].y];
        }

        sort(arr, arr + n, [](Point &a, Point &b) {return (a.x != b.x ? a.x < b.x : a.y < b.y);});
        
        ll ans = 0;
        for (int i = 0; i < n; i++) {
            const auto &[x, y] = arr[i];
            ans += query(y);
            update(y);
        }
        cout << ans << '\n';
    }
    return 0;
}