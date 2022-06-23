#include <iostream>
using namespace std;


const int MAX_N = 1e4;
int bit[MAX_N], cnt[1 << 21];
const int trans[26] {
    -1, 0, 1, 2, -1, 3, 4, 5, -1, 6, 7, 8, 9, 10, -1, 11,
    12, 13, 14, 15, -1, 16, 17, 18, 19, 20
};

inline int charToPos(char c) {
    return trans[c-'a'];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m; cin >> n >> m;
    int pos;
    string inp;
    for (int i = 0; i < n; i++) {
        cin >> inp;
        for (char& c : inp) {
            pos = charToPos(c);
            if (pos >= 0) {
                bit[i] |= 1 << pos;
            } 
        }
        cnt[bit[i]]++;
    }

    for (int i = 1; i < (1 << 21); i <<= 1) {
        for (int j = 0; j < (1 << 21); j += i << 1) {
            for (int k = 0; k < i; k++) {
                cnt[i | j | k] += cnt[j | k];
            }
        }
    }
    int remember = (1 << 21) - 1;
    int o; char x;
    for (int i = 0; i < m; i++) {
        cin >> o >> x;

        remember ^= 1 << charToPos(x);

        cout << cnt[remember] << '\n';
    }
}