#include <iostream>
using namespace std;

const int SIZE = 1e6+1;
int tree[SIZE];

void update(int i, int v) {
    while (i < SIZE) {
        tree[i] += v;
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

    int n; cin >> n;
    int a, b, c, num, mid, left, right;
    while (n--) {
        cin >> a;
        if (a == 1) {
            cin >> b;
            left = 1, right = SIZE-1;
            while (left <= right) {
                mid = (left + right) >> 1;
                if (query(mid) >= b) {
                    num = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            cout << num << '\n';
            update(num, -1);
        } else {
            cin >> b >> c;
            update(b, c);
        }
    }
}