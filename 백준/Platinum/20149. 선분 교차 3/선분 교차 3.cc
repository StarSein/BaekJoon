#include <iostream>
#include <map>
#include <vector>
#include <iomanip>
using namespace std;
#define X first
#define Y second
typedef long long ll;
typedef pair<ll, ll> pll;
typedef pair<pll, pll> ppll;


bool isOnePointMeet = false;
pll meetingPoint;

int getSA(pll& p1, pll& p2, pll& p3) {
    long long sa = (p1.X * p2.Y + p2.X * p3.Y + p3.X * p1.Y) \
            - (p2.X * p1.Y + p3.X * p2.Y + p1.X * p3.Y);
    if (sa > 0)      return 1;
    else if (sa < 0) return -1;
    else             return 0;

}

bool isIntersect(ppll& seg1, ppll& seg2) {
    int ccw1 = getSA(seg1.X, seg1.Y, seg2.X) * getSA(seg1.X, seg1.Y, seg2.Y);
    int ccw2 = getSA(seg2.X, seg2.Y, seg1.X) * getSA(seg2.X, seg2.Y, seg1.Y);
    if (ccw1 == 0 && ccw2 == 0) {
        if (seg1.X > seg1.Y)    swap(seg1.X, seg1.Y);
        if (seg2.X > seg2.Y)    swap(seg2.X, seg2.Y);
        if (seg1.Y == seg2.X && seg2.Y != seg1.X) {
            isOnePointMeet = true;
            meetingPoint = seg1.Y;
        } else if (seg1.Y != seg2.X && seg2.Y == seg1.X) {
            isOnePointMeet = true;
            meetingPoint = seg2.Y;
        }
        return seg1.Y >= seg2.X && seg2.Y >= seg1.X;
    }
    return ccw1 <= 0 && ccw2 <= 0;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    ll x1, y1, x2 ,y2 ,x3, y3, x4, y4;
    cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4;

    ppll seg1 = {{x1, y1}, {x2, y2}}, seg2 = {{x3, y3}, {x4, y4}};
    if (isIntersect(seg1, seg2)) {
        cout << 1 << '\n';
        if (isOnePointMeet) {
            cout << meetingPoint.X << ' ' << meetingPoint.Y;
            return 0;
        }
        ll a = y2 - y1, b = x1 - x2, c = y4 - y3, d = x3 - x4;
        ll det = a * d - b * c;
        if (det == 0)
            return 0;
        ll p = x1 * y2 - y1 * x2, q = x3 * y4 - y3 * x4;
        double x = (d * p - b * q) / static_cast<double>(det);
        double y = (-c * p + a * q) / static_cast<double>(det);
        cout.precision(16);
        cout << x << ' ' << y;
    } else {
        cout << 0;
    }
}