#include <iostream>
#include <vector>
using namespace std;

typedef struct Point {
    int x, y;

    Point() = default;
    Point(int x, int y) : x(x), y(y) {};
    bool operator >= (Point& p) {
        return (x == p.x) ? y >= p.y : x >= p.x;
    }
} Point;

typedef struct Seg {
    Point p1, p2;

    Seg(int x1, int y1, int x2, int y2) : p1(x1, y1), p2(x2, y2) {};
} Seg;

vector<Seg> vSeg;
vector<int> roots;
vector<int> vNumCompo;

Point& max(Point& a, Point& b) {
    if (a >= b) return a;
    else        return b;
}

Point& min(Point& a, Point& b) {
    if (a >= b) return b;
    else        return a;
}

inline int getCCW(Point& p1, Point& p2, Point& p3) {
    int signedArea = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);
    if (signedArea > 0)      return 1;
    else if (signedArea < 0) return -1;
    else                     return 0;
}

bool isIntersect(Seg& a, Seg& b) {
    int sa1 = getCCW(a.p1, a.p2, b.p1), sa2 = getCCW(a.p1, a.p2, b.p2), sa3 = getCCW(b.p1, b.p2, a.p1), sa4 = getCCW(b.p1, b.p2, a.p2);
    if (sa1 * sa2 == 0 && sa3 * sa4 == 0) {
        return max(a.p1, a.p2) >= min(b.p1, b.p2) && max(b.p1, b.p2) >= min(a.p1, a.p2);
    }
    return sa1 * sa2 <= 0 && sa3 * sa4 <= 0;
}

int find(int x) {
    if (roots[x] == x)
        return x;

    return roots[x] = find(roots[x]);
}

void merge(int a, int b) {
    if (a > b)
        swap(a, b);
    roots[b] = a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);


    int n; cin >> n;
    vSeg.reserve(n);

    int x1, y1, x2, y2;
    for (int i = 0; i < n; i++) {
        cin >> x1 >> y1 >> x2 >> y2;
        vSeg.emplace_back(x1, y1, x2, y2);
    }

    roots.reserve(n);
    for (int i = 0; i < n; i++)
        roots.push_back(i);
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            int rootI = find(i), rootJ = find(j);
            if (isIntersect(vSeg[i], vSeg[j]))
                merge(rootI, rootJ);
        }
    }

    vNumCompo.resize(n, 0);
    for (int i = 0; i < n; i++)
        vNumCompo[find(i)]++;
    int cntGroup = 0, maxNumCompo = 0;
    for (int i = 0; i < n; i++) {
        if (vNumCompo[i]) {
            cntGroup++;
            maxNumCompo = max(maxNumCompo, vNumCompo[i]);
        }
    }
    cout << cntGroup << '\n' << maxNumCompo;
}