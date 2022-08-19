#include <iostream>
#include <unordered_map>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

int val = 9;
unordered_map<char, ll> weights;
vector<ll> vWeight;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);


    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        string word; cin >> word;

        for (int j = 0; j < word.size(); j++) {
            if (!weights.count(word[j])) {
                weights.emplace(word[j], 0);
            }

            weights[word[j]] += (ll) pow(10, word.size() - j - 1);
        }
    }

    vWeight.reserve(10);
    for (auto iter = weights.begin(); iter != weights.end(); iter++) {
        vWeight.push_back(iter->second);
    }

    sort(vWeight.begin(), vWeight.end(), greater<ll>());
    
    ll ans = 0;
    for (auto iter = vWeight.begin(); iter != vWeight.end(); iter++, val--) {
        ans += *iter * val;
    }
    cout << ans;
    return 0;
}