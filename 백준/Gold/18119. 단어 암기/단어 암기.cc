#include <iostream>
using namespace std;

const int MAX_N = 1e4, NUM_CHAR = 26;

int n, m;
string arrStr[MAX_N];
int bit[MAX_N];
int forget;

int countWords() {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        cnt += ((bit[i] & forget) == 0);
    }
    return cnt;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> arrStr[i];
    }
    for (int i = 0; i < n; i++) {
        for (char& c : arrStr[i]) {
            bit[i] |= 1 << (c-'a');
        }
    }
    int o; char x;
    for (int i = 0; i < m; i++) {
        cin >> o >> x;
        if (o == 1) {
            forget |= 1 << (x-'a');
        } else {
            forget ^= 1 << (x-'a');
        }
        cout << countWords() << '\n';
    }
}