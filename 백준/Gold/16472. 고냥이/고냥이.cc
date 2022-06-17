#include <iostream>
#include <algorithm>
using namespace std;

const int MAX_N = 26;
int len[MAX_N+1], bit[MAX_N+1], cnt[MAX_N+1];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n; cin >> n;
    string inp; cin >> inp;

    int answer = 0;
    for (int i = 0; i < inp.size(); i++) {
        int intChar = inp[i] - 'a';
        for (int num = n; num > 0; num--) {
            if (bit[num] & 1 << intChar) {
                len[num]++;
            } else if (cnt[num] < num) {
                len[num]++;
                bit[num] |= 1 << intChar;
                cnt[num]++;
            } else {
                len[num] = len[num-1] + 1;
                bit[num] = bit[num-1] | 1 << intChar;
            }
        }
        answer = max(answer, *max_element(len, len + n + 1));
    }
    cout << answer;
}