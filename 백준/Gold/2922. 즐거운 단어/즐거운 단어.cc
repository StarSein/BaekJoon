#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef long long ll;

const int NUM_CON = 21, NUM_VOW = 5, SZ = 100;
vector<char> vowel {'A', 'E', 'I', 'O', 'U'};

bool isCons[SZ];
bool isL;
vector<int> posUL;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string w; cin >> w;
    fill(isCons, isCons + w.size(), true);
    for (int i = 0; i < w.size(); i++) {
        if (find(vowel.begin(), vowel.end(), w[i]) != vowel.end()) {
            isCons[i] = false;
        } else if (w[i] == 'L') {
            isL = true;
        } else if (w[i] == '_') {
            posUL.push_back(i);
        }
    }
    ll ans = 0;
    for (int bit = 0; bit < (1 << (int)posUL.size()); bit++) {
        ll curNum = 1;
        int cntCons = 0;
        for (int i = 0; i < posUL.size(); i++) {
            if (bit & (1 << i)) {
                isCons[posUL[i]] = true;
                curNum *= NUM_CON;
                cntCons++;
            } else {
                isCons[posUL[i]] = false;
                curNum *= NUM_VOW;
            }
        }
        bool isHappy = true;
        for (int i = 0; i < w.size() - 2; i++) {
            if (isCons[i] == isCons[i+1] && isCons[i+1] == isCons[i+2]) {
                isHappy = false;
                break;
            }
        }
        if (!isHappy) {
            continue;
        }
        if (!isL) {
            if (cntCons) {
                ll minus = 1;
                for (int i = 0; i < posUL.size(); i++) {
                    if (bit & (1 << i)) {
                        isCons[posUL[i]] = true;
                        minus *= (NUM_CON - 1);
                    } else {
                        isCons[posUL[i]] = false;
                        minus *= NUM_VOW;
                    }
                }
                curNum -= minus;
            } else {
                continue;
            }
        }
        ans += curNum;
    }
    cout << ans;
    return 0;
}