#include <iostream>
using namespace std;

int dice[6][2];

int gcd(int a, int b) {
    int tmp;
    while (b) {
        tmp = a;
        a = b;
        b = tmp % b;
    }
    return a;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 6; j++) {
            cin >> dice[j][i];
        }
    }

    int cnt = 0;
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            if (dice[j][1] < dice[i][0]) {
                cnt++;
            }
        }
    }
    int g = gcd(cnt, 36);
    cout << (cnt / g) << '/' << (36 / g);
    return 0;
}