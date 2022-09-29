#include <iostream>
#include <algorithm>
#include <iterator>
#include <cmath>
using namespace std;
typedef long long ll;

const int NUM_DIGIT = 10;
ll cnt[NUM_DIGIT];


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N; cin >> N;
    string nStr = to_string(N);
    for (int pos = 0; pos < nStr.size(); pos++) {
        int curDigit = nStr[nStr.size() - 1 - pos] - '0';
        ll div = pow(10, pos + 1);
        int mod = N % (div / 10);
        int leftPart = N / div;
        cnt[0] += leftPart * (div / 10);
        for (int digit = 1; digit <= curDigit; digit++) {
            cnt[digit] += (leftPart + 1) * (div / 10);
        }
        cnt[curDigit] -= (div / 10) - 1 - mod;
        for (int digit = curDigit + 1; digit <= 9; digit++) {
            cnt[digit] += leftPart * (div / 10);
        }
    }
    copy(cnt, cnt + NUM_DIGIT, ostream_iterator<ll>(cout, " "));
    return 0;
}