#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 1e5;
int height[MAX_N];

int getCurArea(int l, int r, int mid) {
    int curHeight = height[mid], curArea = curHeight;
    int lp = mid - 1, rp = mid + 1;
    while (l <= lp || rp <= r) {
        if (l <= lp && height[lp] > height[rp] || rp > r) {
            curHeight = min(curHeight, height[lp]);
            curArea = max(curArea, curHeight * (rp - lp));
            lp--;
        } else {
            curHeight = min(curHeight, height[rp]);
            curArea = max(curArea, curHeight * (rp - lp));
            rp++;
        }
    }
    return curArea;
}

int getMaxArea(int l, int r) {
    if (l == r) {
        return height[l];
    }
    int mid = (l + r) >> 1;
    return max({getMaxArea(l, mid), getMaxArea(mid+1, r), getCurArea(l, r, mid)});
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> height[i];
    }

    cout << getMaxArea(0, N - 1);
}