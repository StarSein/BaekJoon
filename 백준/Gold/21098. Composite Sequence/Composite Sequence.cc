/*

1. n >= 4 이면 Yes
2. n = 3 이면 셋 중 1이 아닌 수가 하나라도 있으면 Yes
3. n = 2 이면 a, b, a + b 의 소수 판정
4. n = 1 이면 a 의 소수 판정

*/
#include <iostream>
#include <cmath>
using namespace std;

const int MAX_N = 1e5;
int S[MAX_N];

bool isPrime(int num) {
    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> S[i];
    }

    bool ans = false;
    if (n >= 4) {
        ans = true;
    } else if (n == 3) {
        for (int i = 0; i < n; i++) {
            if (S[i] > 1) {
                ans = true;
                break;
            }
        }
    } else {
        if (n == 2) {
            n++;
            S[2] = S[0] + S[1];
        }
        for (int i = 0; i < n; i++) {
            if (!isPrime(S[i])) {
                ans = true;
                break;
            }
        }
    }
    cout << (ans ? "Yes" : "No");
    return 0;
}