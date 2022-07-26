#include <iostream>
using namespace std;

const int MAX_N = 2e5, MAX_H = 5e5+1;
int pSum[MAX_H], sSum[MAX_H];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N, H; cin >> N >> H;
    for (int i = 0; i < (N >> 1); i++) {
        int h; cin >> h;
        sSum[h]++;
        cin >> h;
        pSum[H-h]++;
    }
    for (int i = H - 1; i >= 0; i--) {
        sSum[i] += sSum[i+1];
    }
    for (int i = 1; i <= H; i++) {
        pSum[i] += pSum[i-1];
    }    

    int ans1 = MAX_N, ans2 = 0;
    for (int i = 0; i < H; i++) {
        int curSum = sSum[i+1] + pSum[i];
        if (curSum < ans1) {
            ans1 = curSum;
            ans2 = 1;
        } else if (curSum == ans1) {
            ans2++;
        }
    }
    cout << ans1 << ' ' << ans2;
}