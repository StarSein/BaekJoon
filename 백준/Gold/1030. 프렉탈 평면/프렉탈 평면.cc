#include <iostream>
#include <cmath>
using namespace std;

int n, k;

bool isBlack(int sizeE, int sizeC, int r, int c) {
    if (sizeE == 1) {
        return false;
    }
    
    int leftCenter = (sizeE - sizeC) / 2;
    int rightCenter = leftCenter + sizeC - 1;
    if (leftCenter <= r && r <= rightCenter && leftCenter <= c && c <= rightCenter) {
        return true;
    }
    int nse = sizeE / n, nsc = sizeC / n;
    return isBlack(nse, nsc, r % nse, c % nse);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int s, r1, r2, c1, c2;
    cin >> s >> n >> k >> r1 >> r2 >> c1 >> c2;
    if (s == 0) {
        cout << 0;
        return 0;
    }
    
    int t1 = static_cast<int>(pow(n, s));
    int t2 = t1 / n;
    for (int row = r1; row <= r2; row++) {
        for (int col = c1; col <= c2; col++) {
            cout << isBlack(t1, k*t2, row, col);
        }
        cout << '\n';
    }
}