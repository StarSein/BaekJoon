#include <iostream>
using namespace std;
#define X first
#define Y second

typedef long long ll;
typedef pair<ll, ll> pll;

pll p[4];

int getSA(pll p1, pll p2, pll p3) {
    ll sa = (p1.X * p2.Y + p2.X * p3.Y + p3.X * p1.Y) - (p2.X * p1.Y + p3.X * p2.Y + p1.X * p3.Y);
    if (sa > 0) return 1;
    else if (sa == 0) return 0;
    else    return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    ll x, y;
    for (int i = 0; i < 4; i++) {
        cin >> x >> y;
        p[i] = {x, y};
    }
    cout << (getSA(p[0], p[1], p[2]) * getSA(p[0], p[1], p[3]) < 0 && \
             getSA(p[2], p[3], p[0]) * getSA(p[2], p[3], p[1]) < 0);
}