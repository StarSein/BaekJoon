#include <iostream>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;
typedef pair<pll, pll> ppll;

int getSA(pll p1, pll p2, pll p3) {
    ll sa = p1.first * p2.second + p2.first * p3.second + p3.first * p1.second \
            - p2.first * p1.second - p3.first * p2.second - p1.first * p3.second;
    if (sa > 0)         return 1;
    else if (sa < 0)    return -1;
    else                return 0;
}

bool isIntersect(ppll seg1, ppll seg2) {
    int ccw1 = getSA(seg1.first, seg1.second, seg2.first) * getSA(seg1.first, seg1.second, seg2.second);
    int ccw2 = getSA(seg2.first, seg2.second, seg1.first) * getSA(seg2.first, seg2.second, seg1.second);
    if (ccw1 == 0 && ccw2 == 0) {
        if (seg1.first > seg1.second)   swap(seg1.first, seg1.second);
        if (seg2.first > seg2.second)   swap(seg2.first, seg2.second);
        return (seg1.second >= seg2.first && seg2.second >= seg1.first);
    }
    return (ccw1 <= 0 && ccw2 <= 0);
}

int main() {
    ll x1, y1, x2, y2, x3, y3, x4, y4;
    cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4;
    cout << (isIntersect({{x1, y1}, {x2, y2}}, {{x3, y3}, {x4, y4}}) ? 1 : 0) << '\n';
}