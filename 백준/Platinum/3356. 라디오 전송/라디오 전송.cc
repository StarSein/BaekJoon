#include <iostream>
#include <vector>
using namespace std;

int L;
string S;
vector<int> fails;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> L >> S;

    fails.resize(L);
    for (int i = 1, j = 0; i < L; i++) {
        while (j && S[i] != S[j]) {
            j = fails[j - 1];
        }
        if (S[i] == S[j]) {
            fails[i] = ++j;
        }
    }

    cout << L - fails[L - 1];
    return 0;
}
