#include <iostream>
using namespace std;

int gcd(int a, int b) {
    while (b) {
        int r = a % b;
        a = b;
        b = r;
    }
    return a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M; cin >> N >> M;
    int g = gcd(N, M);
    N /= g;
    M /= g;
    int ans = g * (M - 1);
    cout << ans;
    return 0;
}