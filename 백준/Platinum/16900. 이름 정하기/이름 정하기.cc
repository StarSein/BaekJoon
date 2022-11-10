#include <iostream>
#include <vector>
using namespace std;

vector<int> fails;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string S; int K; cin >> S >> K;
    fails.resize(S.size());

    int j = 0;
    for (int i = 1; i < S.size(); i++) {
        while (j && S[i] != S[j]) {
            j = fails[j - 1];
        }
        if (S[i] == S[j]) {
            fails[i] = ++j;
        }
    }
    long long ans = S.size() + (S.size() - fails[S.size() - 1]) * (K - 1);
    cout << ans;
    return 0;
}