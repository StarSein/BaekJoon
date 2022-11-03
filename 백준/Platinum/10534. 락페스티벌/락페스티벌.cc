#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

struct Seg {
    int axis;
    int left;
    int right;
    int id;

    Seg() = default;
    Seg(int a, int l, int r, int i): axis(a), left(l), right(r), id(i) {};
};

const int MAX_N = 5e4;
int root[MAX_N];
ll area[MAX_N];

vector<Seg> xVec;
vector<Seg> yVec;

int findRoot(int x) {
    if (root[x] != x) {
        root[x] = findRoot(root[x]);
    }
    return root[x];
}

void merge(int a, int b) {
    if (a > b) {
        swap(a, b);
    }
    root[b] = a;
    area[a] += area[b];
}

void sweep(vector<Seg> vec) {
    int prevI = vec[0].id;
    int prevA = vec[0].axis;
    int prevR = vec[0].right;

    for (int i = 1; i < vec.size(); i++) {
        const Seg &e = vec[i];
        if (prevA == e.axis && prevR >= e.left) {
            int prevRoot = findRoot(prevI);
            int curRoot = findRoot(e.id);
            if (prevRoot != curRoot) {
                merge(prevRoot, curRoot);
            }
            if (prevR > e.right) {
                continue;
            }
        }
        prevI = e.id;
        prevA = e.axis;
        prevR = e.right;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    xVec.reserve(2 * N);
    yVec.reserve(2 * N);
    for (int i = 0; i < N; i++) {
        int x, y, w, h; cin >> x >> y >> w >> h;
        root[i] = i;
        area[i] = w * h;
        xVec.emplace_back(x, y, y + h, i);
        xVec.emplace_back(x + w, y, y + h, i);
        yVec.emplace_back(y, x, x + w, i);
        yVec.emplace_back(y + h, x, x + w, i);
    }

    sort(xVec.begin(), xVec.end(), [](Seg &a, Seg &b) {return a.axis != b.axis ? a.axis < b.axis : a.left < b.left;});
    sort(yVec.begin(), yVec.end(), [](Seg &a, Seg &b) {return a.axis != b.axis ? a.axis < b.axis : a.left < b.left;});
    sweep(xVec);
    sweep(yVec);

    ll ans = *max_element(area, area + N);
    cout << ans;
    return 0;
}